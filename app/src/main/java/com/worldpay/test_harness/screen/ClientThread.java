package com.worldpay.test_harness.screen;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import android.os.Handler;


public class ClientThread implements Runnable {
	static Socket socket = null;
	static Socket intraMsgSocket = null;
	int inputOutputSocketPort = 10000;
	int intraMsgSocketPort = 8000;
	private String serverIpAddress = "localhost";
	public static boolean connected = false;
	Thread cThread;
	Thread intraThread;
	InetAddress serverAddr;
	static ObjectInputStream ois = null;
	static ObjectOutputStream out = null;
	
	static ObjectInputStream intraMsgOis = null;
	static ObjectOutputStream intraMsgOut = null;
	
	public static String message;
	private Object line = null;
	static String data ;
	static Handler mHandler;
	
	public void run() {
        
		try {
			connected = false;
			serverAddr = InetAddress.getByName(serverIpAddress);
			System.out.println("ClientThread Connecting..."+intraMsgSocket+"::"+socket);
			try {
				intraMsgSocket = new Socket(serverAddr, intraMsgSocketPort);
				socket = new Socket(serverAddr, inputOutputSocketPort);
			} catch (ConnectException ce) {		
				System.out.println("In ConnectException::->"+ce);
			}
			connected = true;
				System.out.println("In while connected::->");
				try {
					out = new ObjectOutputStream(socket.getOutputStream());
					out.flush();
					ois = new ObjectInputStream(socket.getInputStream());
					System.out.println("ois::->"+ois);
					
					intraMsgOut = new ObjectOutputStream(intraMsgSocket.getOutputStream());
					intraMsgOut.flush();
					intraMsgOis = new ObjectInputStream(intraMsgSocket.getInputStream());
					System.out.println("intraMsgOis::->"+intraMsgOis);
				System.out.println("out put streem called---->>");
				Object object = (Object) data;
					try {
						out.flush();
						out.writeObject(object);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}catch(NullPointerException ne){
					System.out.println("In NullPointerException::->");
				}catch(SocketException se){
					System.out.println("In SocketException::->");
				}

				try {
					while (connected) {
						System.out.println("reading input..");
						intraThread = new Thread(new IntraMsgThread());
						intraThread.start();
						if((line = ois.readObject()) != null ){
						message = (String) line;
						System.out.println("Message Received by client socket is : \n"+ message);
						connected = false;
						mHandler.sendEmptyMessage(1);
						}
					}
				} catch (EOFException ex) {
					System.out.println("In EOFException::->");
				}catch(NullPointerException ne){
					System.out.println("NullPointerException::::");						
				}
		} catch (Exception e) {
			
		}
	}
	
	public static void setInputData(String input) {
		data = input;
	}
	
	public static void setHandler(Handler handler) {
		mHandler = handler;
	}
	
	/*public static void closeSocket() {
		try {
			if(socket != null) {
				socket.close();
			}
			if(intraMsgSocket != null) {
				intraMsgSocket.close();
			}
			socket = null;
			intraMsgSocket = null;
			
			if(ois != null){
				ois.close();
			}
			if(out != null){
				out.close();
			}
			if(intraMsgOis != null && intraMsgOut != null){
				intraMsgOis.close();
				intraMsgOut.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("in exception closeSocket()::");
		}
	}*/
}



/*public class IntraMsgThread implements Runnable {

	public void run() {
        
		try {
			while (connected) {
				System.out.println("reading input in IntraMsgThread..");
				if((intraMsgline = intraMsgOis.readObject()) != null){
					if(intraMsgOis.available() != -1){
					message = (String) intraMsgOis.readObject();
					System.out.println("intraMsgline::->"+intraMsgline);
					if(isAlert) {
						Hashtable hshTable = (Hashtable)intraMsgline;
						title = (String)hshTable.get("alert_title");
						alertMsg = (String)hshTable.get("alert_message");
						System.out.println("title::"+title);
						System.out.println("message::"+alertMsg);
						if(alertMsg != null && alertMsg.trim().length() > 0) {
							isAlert = false;
							showOkDialog(Amount.this, title, message);
							mHandler.sendEmptyMessage(2);
						}
					} else {
					message = (String) intraMsgline;
					}
					if(message.equalsIgnoreCase("ALERT")) {
						isAlert = true;
					}
					System.out.println("Message Received by client intraMsgsocket is : \n"+ message);
					mHandler.sendEmptyMessage(1);
				}
			}
		  }
		}catch (Exception ex) {
			System.out.println("In Exception IntraMsgThread::->");
		}
	}
}*/
