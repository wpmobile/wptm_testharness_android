package com.worldpay.test_harness.screen;



public class IntraMsgThread implements Runnable {
	private Object intraMsgline = null;
	public void run() {
		
		try {
			while (ClientThread.connected) {
				System.out.println("reading input in IntraMsgThread..");
				if((intraMsgline = ClientThread.intraMsgOis.readObject()) != null){
					System.out.println("intraMsgline::->"+intraMsgline);
					ClientThread.message = (String) intraMsgline;
					System.out.println("Message Received by client intraMsgsocket is : \n"+ ClientThread.message);
					ClientThread.mHandler.sendEmptyMessage(1);
			}
		  }
		}catch (Exception ex) {
			System.out.println("In Exception IntraMsgThread::->");
		}
	}
	
}
