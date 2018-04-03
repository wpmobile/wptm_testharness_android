package com.worldpay.test_harness.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.worldpay.test_harness.screen.Receipt;

public class ReadWrite extends Activity {
	
	public static Socket sok;
	String serverip = "localhost";
	int serverport = 1111;
	static ObjectOutputStream dos;
	static ObjectInputStream dis;
	
	public void write(String data) {
		// TODO Auto-generated method stub
		
		try {
						
			Log.i("AsyncTank", "doInBackgoung: Creating Socket");
			if(sok==null){
				System.out.println("-New Socket Creaded-");
				dos=null;
				dis=null;
				sok = new Socket(serverip, serverport);
				System.out.println("Socket---> " + sok);
			}
			System.out.println("Socket---> " + sok);
			if (dos == null) {
				dos = new ObjectOutputStream(sok.getOutputStream());
			}
			Object obj = (Object) data;
			dos.writeObject(obj);
			System.out.println("Data----->>>>" + obj);

		} catch (Exception e) {
			Log.i("AsyncTank", "doInBackgoung: Cannot create Socket " + e);
		}
	}
	
	
	public void read(final Context context) {
		// TODO Auto-generated method stub
		try {
			if (dis == null) {
				dis = new ObjectInputStream(sok.getInputStream());
			}
			String msg = (String) dis.readObject();
			Boolean readflag = true;
			System.out.println("EVT Data------>>>" + msg);
			if (readflag && msg != null) {

				Intent intent = new Intent(context, Receipt.class);
				intent.putExtra("DATA", msg);
				context.startActivity(intent);
				finish();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void service_check() {
		// TODO Auto-generated method stub
		if(isMobileEVTServiceRunning() == false)
		{	
			ReadWrite.sok= null;
			System.out.println("-Mobile EVT start Now-");
			Intent in = new Intent();
			in.setClassName("yes.mobileevt", "yes.mobileevt.main.MobileEVTServices");
			startService(in);			
		}

	}
	

	private boolean isMobileEVTServiceRunning() {
	    ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if ("yes.mobileevt.main.MobileEVTServices".equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
}
