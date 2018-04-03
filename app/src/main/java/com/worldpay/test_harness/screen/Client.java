package com.worldpay.test_harness.screen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import com.worldpay.test_harness.R;
import com.worldpay.test_harness.utils.AlertDialogScreen;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.util.Log;

public class Client extends Activity {

	static Socket sok;
	String serverip = "localhost";
	int serverport = 1111;
	static ObjectOutputStream dos;
	static ObjectInputStream dis;
	String data;
	Thread cThread;
	AlertDialogScreen alert;
	boolean EVT_FLAG = false;
	final Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);

		alert = new AlertDialogScreen();

		Bundle b = getIntent().getExtras();
		if (b != null) {
			data = b.getString("DATA");
			System.out.println("MSG----->>>  " + data);
		}

		if (isMobileEVTServiceRunning() == false) {
			sok = null;
			System.out.println("-Mobile EVT start Now-");
			Intent in = new Intent();
			in.setClassName("yes.mobileevt",
					"yes.mobileevt.main.MobileEVTServices");
			startService(in);
		}

		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						cThread = new Thread(new ClientThread());
						cThread.start();
					}
				});
			}
		}, 1000);

	}

	public class ClientThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {

				Log.i("AsyncTank", "doInBackgoung: Creating Socket");
				if (sok == null) {
					System.out.println("-New Socket Creaded-");
					dos = null;
					dis = null;
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

			try {
				if (dis == null) {
					dis = new ObjectInputStream(sok.getInputStream());
				}
				String msg = (String) dis.readObject();
				Boolean readflag = true;
				System.out.println("EVT Data------>>>" + msg);
				if (readflag && msg != null) {

					Intent intent = new Intent(Client.this, Receipt.class);
					intent.putExtra("DATA", msg);
					startActivity(intent);
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

	}

	private boolean isMobileEVTServiceRunning() {
		ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager
				.getRunningServices(Integer.MAX_VALUE)) {
			if ("yes.mobileevt.main.MobileEVTServices".equals(service.service
					.getClassName())) {
				return true;
			}
		}
		return false;
	}

}
