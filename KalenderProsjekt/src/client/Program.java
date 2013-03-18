package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;

import data.CalendarModel;

import framePackage.DefaultView;

import networking.Constants;
import networking.packages.Response;

public class Program {
	private static InetAddress serverAddress;
	public static Socket server;
	private static BlockingQueue<Response> queueForHandlingResponses;
	public final static CalendarModel calendarModel = new CalendarModel();
	public static Client client;
	public static RequestHandler reqHandler;

	public static void main(String[] args) throws UnknownHostException {
		queueForHandlingResponses = new LinkedBlockingQueue<Response>();
		serverAddress = InetAddress.getByName(Constants.serverIP);
		try {
			server = new Socket(serverAddress, Constants.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			client = new Client(InetAddress.getByName(Constants.serverIP));
		} catch (IOException e) {
			e.printStackTrace();
		}
		reqHandler = new RequestHandler();
		DefaultView dw = new DefaultView();
		JFrame frame = dw.getFrame();
		frame.setBounds(0, 0, 1260, 768);
		frame.setVisible(true);
		startThreads();
		
	}
	

	
	public static void startThreads(){
		Thread t = new Thread(new ResponseListener(server, queueForHandlingResponses));
		t.start();
		Thread t2 = new Thread(new ResponseHandler(queueForHandlingResponses));
		t2.start();
	}
}
