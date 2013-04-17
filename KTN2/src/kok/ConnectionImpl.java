package kok;

import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import no.ntnu.fp.net.cl.ClException;
import no.ntnu.fp.net.cl.KtnDatagram;
import no.ntnu.fp.net.cl.KtnDatagram.Flag;
import no.ntnu.fp.net.co.AbstractConnection;
import no.ntnu.fp.net.co.Connection;

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
	 * Initialize initial sequence number and setup state machine.
	 * 
	 * @param myPort
	 *            - the local port to associate with this connection
	 * @throws UnknownHostException 
	 */
	public ConnectionImpl(int myPort)  {
		this.myAddress = "192.168.0.1";//getIPv4Address();
		this.myPort = myPort;
	}

	private String getIPv4Address() throws UnknownHostException {
		try {
			return InetAddress.getLocalHost().getHostAddress();
			//return ""; // TODO - MÅtte ta en spansk en siden JAVA bruker feil nettverkskort
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
	 * @throws ClException 
	 * @see Connection#connect(InetAddress, int)
	 */
	public void connect(InetAddress remoteAddress, int remotePort)throws java.io.IOException,
	java.net.SocketTimeoutException{
		//Sets remote address and port

		this.remoteAddress = remoteAddress.getHostAddress();
		this.remotePort = remotePort;
		//Constructs a SYN packet and sends to the server
		KtnDatagram synPacket = constructInternalPacket(Flag.SYN);

		try {
			simplySendPacket(synPacket);
		} catch (ClException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		state = State.SYN_SENT;

		//Waiting for ACK from server
		KtnDatagram recievedSynAck = null;
		while (recievedSynAck == null)
			recievedSynAck = receiveAck();

		lastValidPacketReceived = recievedSynAck;

		if (recievedSynAck.getFlag() == Flag.SYN_ACK)
			sendAck(recievedSynAck, false);

		//Experimental
		//synPacket = recievedSynAck = null;
		state = State.ESTABLISHED;

	}

	/**
	 * Listen for, and accept, incoming connections.
	 * 
	 * @return A new ConnectionImpl-object representing the new connection.
	 * @throws InterruptedException 
	 * @see Connection#accept()
	 */
	public Connection accept() throws IOException, SocketTimeoutException {
		state = State.LISTEN;
		//Fetches a incoming packet and creating a new connection
		KtnDatagram packet = null;
		while (packet == null) {
			packet = receivePacket(true); //TODO - her skal ikke receievepacket kalles???
		}
		ConnectionImpl cImpl = new ConnectionImpl(myPort);
		cImpl.lastValidPacketReceived = packet;
		//Set properties
		cImpl.remoteAddress = packet.getSrc_addr();
		cImpl.remotePort = packet.getSrc_port();
		//System.out.println(cImpl.myAddress);
		//System.out.println(cImpl.myPort);
		//Send SYNACK to client

		sleepz();

		cImpl.sendAck(packet, true);
		cImpl.state = State.SYN_RCVD;
		packet = null;
		while (packet == null)
			packet = receiveAck();

		cImpl.lastValidPacketReceived = packet;

		cImpl.state = State.ESTABLISHED;

		return cImpl;
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
		KtnDatagram sendPacket = constructDataPacket(msg);
		KtnDatagram ack = null;
		while (ack == null)
			sendDataPacketWithRetransmit(sendPacket);
	}

	/**
	 * Wait for incoming data.
	 * 
	 * @return The received data's payload as a String.
	 * @throws ClException 
	 * @see Connection#receive()
	 * @see AbstractConnection#receivePacket(boolean)
	 * @see AbstractConnection#sendAck(KtnDatagram, boolean)
	 */
	public String receive() throws ConnectException, IOException, EOFException {
		sleepz();
		KtnDatagram recievedPacket;
		while (true) {
			try {
				recievedPacket = receivePacket(false);
			} catch (EOFException e) {
				try {
					handleClose();
				} catch (ClException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				state = State.CLOSED;
				throw e;
			}
			if (isValid(recievedPacket)) {
				System.err.println("Sending ack on recievedpacket");
				lastValidPacketReceived = recievedPacket;
				sleepz();
				sendAck(recievedPacket, false);
				return new String(recievedPacket.getPayloadAsBytes()); //TODO - 7 random tegn på starten! :/((#(&"/
			}
		}
	}

	private void handleClose( ) throws ConnectException, IOException, ClException {
		sleepz();
		System.err.println("HANDLE CLOSE");
		//Acks the FIN-packet
		sendAck(disconnectRequest, false);
		state = State.CLOSE_WAIT;

		//Creates a FIN packet and sends it
		sleepz();
		lastValidPacketReceived = disconnectRequest;
		KtnDatagram closingPacket = constructInternalPacket(Flag.FIN);
		state = State.LAST_ACK;

		while (true) {
			sleepz();
			simplySendPacket(closingPacket);
			KtnDatagram ack = null;
			ack = receiveAck();
			if (isValid(ack))
				break;
		}

	}

	/**
	 * Close the connection.
	 * @throws ClException 
	 * 
	 * @see Connection#close()
	 */
	public void close() throws IOException {
		sleepz();
		//Make packet with FIN-flag set
		KtnDatagram closingPacket = constructInternalPacket(Flag.FIN), finAck = null;
		//Send and go to state FIN_WAIT_1
		try {
			simplySendPacket(closingPacket);
		} catch (ClException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.err.println("SENDING CLOSE");
		state = State.FIN_WAIT_1;

		//Get ack and enter FIN_WAIT_2
		sleepz();

		while (finAck == null)
			finAck = receiveAck();
		System.err.println("RECIEVED FIN_ACK");
		state = State.FIN_WAIT_2;

		//Waits for FIN-packt, isValid checks for FIN and
		sleepz();
		while (true) {
			try {
				receivePacket(false);
			} catch (EOFException e) {
				if (isValid(disconnectRequest)) {
					System.err.println("RECIEVING FIN FROM SERVER");
					sleepz();
					sendAck(disconnectRequest, false);
					System.err.println("RECIEVING FIN FROM SERVER");
					state = State.TIME_WAIT;
				}
			}
			//TODO - Hvordan skal vi vente? Skjer dette automatisk?
		}
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
		if (packet == null) {
			System.err.println("Pakke er ugyldig, null");
			return false;
		}

		if (packet.getChecksum() == packet.calculateChecksum()) {
			System.err.println("Sjekksum riktig");
			if (packet.getSeq_nr() == (lastValidPacketReceived.getSeq_nr() + 1)) {
				System.err.println("Sekvensnummer riktig");
				if (packet.getSrc_port() == remotePort && packet.getSrc_addr().equals(remoteAddress)) {
					System.err.println("Port og addresse er riktig");
					return true;
				}
			}
		}
		return false;
	}
	private void sleepz() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
