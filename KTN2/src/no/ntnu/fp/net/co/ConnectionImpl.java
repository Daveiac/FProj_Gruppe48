/*
 * Created on Oct 27, 2004
 */
package no.ntnu.fp.net.co;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import no.ntnu.fp.net.cl.ClException;
import no.ntnu.fp.net.cl.ClSocket;
import no.ntnu.fp.net.cl.KtnDatagram;
import no.ntnu.fp.net.cl.KtnDatagram.Flag;

/**
 * Implementation of the Connection-interface. <br>
 * <br>
 * This class implements the behaviour in the methods specified in the interface
 * {@link Connection} over the unreliable, connectionless network realised in
 * {@link ClSocket}. The base class, {@link AbstractConnection} implements some
 * of the functionality, leaving message passing and error handling to this
 * implementation.
 * 
 * @author Sebjørn Birkeland and Stein Jakob Nordbø
 * @see no.ntnu.fp.net.co.Connection
 * @see no.ntnu.fp.net.cl.ClSocket
 */
public class ConnectionImpl extends AbstractConnection {

    /** Keeps track of the used ports for each server port. */
    private static Map<Integer, Boolean> usedPorts = Collections.synchronizedMap(new HashMap<Integer, Boolean>());

    /**
     * Initialise initial sequence number and setup state machine.
     * 
     * @param myPort
     *            - the local port to associate with this connection
     */
    public ConnectionImpl(int myPort) {
        myAddress = getIPv4Address();
        this.myPort = myPort;
    }

    private String getIPv4Address() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

    /**
     * Establish a connection to a remote location.
     * 
     * @param remoteAddress
     *            - the remote IP-address to connect to
     * @param remotePort
     *            - the remote portnumber to connect to
     * @throws IOException
     *             If there's an I/O error.
     * @throws java.net.SocketTimeoutException
     *             If timeout expires before connection is completed.
     * @see Connection#connect(InetAddress, int)
     */
    public void connect(InetAddress remoteAddress, int remotePort) throws IOException,
            SocketTimeoutException {
    	if(state != State.CLOSED){
    		throw new ConnectException("not closed");
    	}
    		try {
    			this.remoteAddress = remoteAddress.getHostAddress();
    			this.remotePort = remotePort;
    			KtnDatagram packet = constructInternalPacket(KtnDatagram.Flag.SYN);
				simplySendPacket(packet);
				state = State.SYN_SENT;
				KtnDatagram receiveAck = receiveAck();
				if(receiveAck != null){
					state = State.ESTABLISHED;
					sendAck(receiveAck, false);
				}
			} catch (ClException e) {
				e.printStackTrace();
			}
    }

    /**
     * Listen for, and accept, incoming connections.
     * 
     * @return A new ConnectionImpl-object representing the new connection.
     * @see Connection#accept()
     */
    public Connection accept() throws IOException, SocketTimeoutException {
    	state = State.LISTEN;
    	KtnDatagram packet = null;
		while (packet == null || packet.getFlag() != Flag.SYN) {
			packet = receivePacket(true);
		}
		ConnectionImpl conn = new ConnectionImpl(myPort);
		conn.lastValidPacketReceived = packet;
		conn.remoteAddress = packet.getSrc_addr();
		conn.remotePort = packet.getSrc_port();

		conn.sendAck(packet, true);
		conn.state = State.SYN_RCVD;
		packet = null;
		while (packet == null)
			packet = receiveAck();
		conn.lastValidPacketReceived = packet;

		conn.state = State.ESTABLISHED;

		return conn;
    }

    /**
     * Send a message from the application.
     * 
     * @param msg
     *            - the String to be sent.
     * @throws ConnectException
     *             If no connection exists.
     * @throws IOException
     *             If no ACK was received.
     * @see AbstractConnection#sendDataPacketWithRetransmit(KtnDatagram)
     * @see no.ntnu.fp.net.co.Connection#send(String)
     */
	public void send(String msg) throws ConnectException, IOException {
		KtnDatagram packet = constructDataPacket(msg);
		KtnDatagram ack = sendDataPacketWithRetransmit(packet);
	}

    /**
     * Wait for incoming data.
     * 
     * @return The received data's payload as a String.
     * @see Connection#receive()
     * @see AbstractConnection#receivePacket(boolean)
     * @see AbstractConnection#sendAck(KtnDatagram, boolean)
     */
    public String receive() throws ConnectException, IOException {
    	KtnDatagram packet = receivePacket(false);
//    	while(!isValid(packet)) {
//    		packet = receivePacket(false);
//    		System.out.println("trying to receive");
//    	}
    	sendAck(packet, false);
    	lastValidPacketReceived = packet;
    	return (String) packet.getPayload();
    }

    /**
     * Close the connection.
     * 
     * @see Connection#close()
     */
    public void close() throws IOException {
    	//    	throw new RuntimeException("not Implemented");

    	/* Two cases:
    	 * Case 1: Receiving a disconnect request.
    	 * Case 2: Sending a disconnect request.
    	 */

    	try {
    		state = State.FIN_WAIT_1;

    		// If Case 1 (receiving FIN first), then send ACK
    		if (disconnectRequest != null) {
    			sendAck(disconnectRequest, false);
    			state = State.FIN_WAIT_2;
    		}

    		KtnDatagram finToSend = constructInternalPacket(Flag.FIN);
    		KtnDatagram ackToReceive = null;

    		// Case 1: Answer the disconnection with sending a FIN
    		// Case 2: Send FIN to close the connection
    		sendDataPacketWithRetransmit(finToSend);

    		// Receive ACK
    		for (int tries = 3; tries > 0; tries--) {
    			if (!isValid(ackToReceive)) {
    				ackToReceive = receiveAck();
    			}
    		}

    		KtnDatagram finToReceive = null;

    		// If Case 2 (sending FIN first), then receive the other connections FIN
    		if (state != State.FIN_WAIT_2) {
    			for (int tries = 3; tries > 0; tries--) {
    				if (isValid(finToReceive)) {
    					finToReceive = receivePacket(true);
    				}
    			}
				sendAck(finToReceive, false);
    		}
    	}
    	catch (Exception e) {
    		// TODO something something something...
    	}
    	state = State.CLOSED;
    }

    /**
     * Test a packet for transmission errors. This function should only called
     * with data or ACK packets in the ESTABLISHED state.
     * 
     * @param packet
     *            Packet to test.
     * @return true if packet is free of errors, false otherwise.
     */
    protected boolean isValid(KtnDatagram packet) {
    	if(packet != null){
    		System.out.println("det er ikke noe i pakken");
    		if(packet.getChecksum() == packet.calculateChecksum()){
    			if(lastDataPacketSent != null && packet.getSeq_nr() == lastDataPacketSent.getSeq_nr()+1){
    				if(packet.getSrc_port() == remotePort && packet.getSrc_addr() == remoteAddress){
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
}
