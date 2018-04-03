package com.worldpay.test_harness.screen;

import com.worldpay.test_harness.R;
import com.worldpay.test_harness.utils.AlertDialogScreen;
import com.worldpay.test_harness.utils.MessageConstant;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cancel extends Activity {
	
	Button submitButton;
	EditText mtrEditText,cardNoEditText,pgtrEditText,expiryDateEditText;
	String mtr,cardNo,pgtr,expiryDate;
	String data;
	AlertDialogScreen alert;
	boolean isSocketTxn = false;
	Handler mHandler;
	Thread cThread;
	private String callBackUrlInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cancel);
		alert = new AlertDialogScreen();
	
		mtrEditText=(EditText)findViewById(R.id.editMTR);
		cardNoEditText=(EditText)findViewById(R.id.editCN);
		pgtrEditText=(EditText)findViewById(R.id.editPGTR);
		expiryDateEditText=(EditText)findViewById(R.id.editDate);

		Intent in = getIntent();
        isSocketTxn = in.getBooleanExtra("SOCKET_TXN", false);
		
        //Added By Surabhi on 25-08-14
        callBackUrlInput = "71=" + getPackageName() + "\n";
        
        sendHandler();
		mtrEditText.setOnTouchListener(new OnTouchListener() {

   			public boolean onTouch(View v, android.view.MotionEvent event) {
   				mtrEditText.setFocusableInTouchMode(true);	
   				cardNoEditText.setFocusableInTouchMode(true);
   				pgtrEditText.setFocusableInTouchMode(true);
   				expiryDateEditText.setFocusableInTouchMode(true);
   				return false;
   			}
   		});
		
		cardNoEditText.setOnTouchListener(new OnTouchListener() {

   			public boolean onTouch(View v, android.view.MotionEvent event) {
   				cardNoEditText.setFocusableInTouchMode(true);
   				pgtrEditText.setFocusableInTouchMode(true);
   				expiryDateEditText.setFocusableInTouchMode(true);
   				return false;
   			}
   		});
		
		pgtrEditText.setOnTouchListener(new OnTouchListener() {

   			public boolean onTouch(View v, android.view.MotionEvent event) {
   				pgtrEditText.setFocusableInTouchMode(true);
   				expiryDateEditText.setFocusableInTouchMode(true);
   				return false;
   			}
   		});
		expiryDateEditText.setOnTouchListener(new OnTouchListener() {

   			public boolean onTouch(View v, android.view.MotionEvent event) {
   				expiryDateEditText.setFocusableInTouchMode(true);
   				return false;
   			}
   		});
		
		submitButton= (Button) findViewById(R.id.button);           
		submitButton.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View arg0) 
        {		
	    	mtr = mtrEditText.getText().toString();
	    	cardNo = cardNoEditText.getText().toString();
	    	pgtr = pgtrEditText.getText().toString();
	    	expiryDate = expiryDateEditText.getText().toString();
			

	    	if(mtr.equalsIgnoreCase(""))
			{
	    		alert.showOkDialog(Cancel.this,MessageConstant.MTR);			
			}
	    	else if(cardNo.equalsIgnoreCase(""))
			{
	    		alert.showOkDialog(Cancel.this,MessageConstant.CARD_NUMBER);			
			}
	    	
	    	else if(pgtr.equalsIgnoreCase(""))
			{
	    		alert.showOkDialog(Cancel.this,MessageConstant.PGTR);			
			}	    	
	    	else if(expiryDate.equalsIgnoreCase(""))
			{
	    		alert.showOkDialog(Cancel.this,MessageConstant.EXPIRY_DATE);			
			}	    	
	    	else 
	    	{	 
	    	 /*data = "1="+mtr+"\n"+ "2=3"+"\n"+"5="+cardNo+"\n"+"6="+expiryDate+"\n"+"13="+pgtr+"\n"+"99=0";*/
	    	// data = "1="+mtr+"\n"+ "2=3"+"\n"+"5="+cardNo+"\n"+"6="+expiryDate+"\n"+"13="+pgtr+"\n"+callBackUrlInput+"99=0";
	    		
	    		
	    		
	    		Home.generateSecureValues();
	    	       data = "1="+mtr+"\n"+ "2=3"+"\n"+"5="+cardNo+"\n"+"6="+expiryDate+"\n"+"13="+pgtr+"\n"+callBackUrlInput+ "74=" + Home.salt + "\n" + "75=" + Home.hash + "\n" + "76=" + Home.timeStamp + "\n" + "99=0";
	    	showOkDialog(Cancel.this, data);
	    	}
			
        }	 			
	 });
		
		
	}

	public void showOkDialog(Context context, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(false);
		final Dialog dialog = new Dialog(context, android.R.style.Theme_Panel);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.drawable.alert_dialog_output);
		dialog.setTitle("Input Request");
		dialog.setCancelable(false);
		dialog.setOnKeyListener(new OnKeyListener() {

			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_MENU && event.isLongPress())
					return true;
				return false;
			}
		});

		TextView textViewMsg = (TextView) dialog.findViewById(R.id.title_txt);
		textViewMsg.setText(msg);

		Button buttonOk = (Button) dialog.findViewById(R.id.ok_btn);
		buttonOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
		dialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				try {
					if(isSocketTxn) {
						ClientThread.setInputData(data);
						cThread = new Thread(new ClientThread());
						cThread.start();
					} else {
						/*Intent intent = new Intent("mobileevt.intent.action.main");
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("INPUT_REQUEST", data);
						startActivity(intent);
						finish();*/
						if(Selection.APPLICATION_MODE){
							Intent intent = new Intent();
							intent.setComponent(new ComponentName("yes.worldpaytotal", "com.worldpay.wptmobile.screens.IntegrationSplashScreen"));
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
							intent.putExtra("INPUT_REQUEST", data);
							startActivity(intent);
							finish();
						} else {
							Intent intent = new Intent("mobileevt.intent.action.main");
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
							intent.putExtra("INPUT_REQUEST", data);
							startActivity(intent);
							finish();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(Cancel.this, MessageConstant.APP_NOT_FOUND, Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		//Added by Surabhi on 17-11-14
				Button buttonUpdate = (Button) dialog.findViewById(R.id.update_btn);
				buttonUpdate.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(Cancel.this,UpdateData.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("INPUT_REQUEST", data);
						System.out.println("intent ==>> "+intent);
						startActivity(intent);
						finish();				
					}
				});
				//End of changes
	}
	
	private void sendHandler() {
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if(msg != null){
					System.out.println("in handler::->");
					if(msg.what == 1) {
						Toast.makeText(Cancel.this, /*"socket msg:"+*/ClientThread.message, Toast.LENGTH_LONG).show();
					} 
				}
			}
		};
		ClientThread.setHandler(mHandler);
	}
}
