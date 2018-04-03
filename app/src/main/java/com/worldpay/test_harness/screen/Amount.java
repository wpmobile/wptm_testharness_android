package com.worldpay.test_harness.screen;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.worldpay.test_harness.R;
import com.worldpay.test_harness.utils.AlertDialogScreen;
import com.worldpay.test_harness.utils.MessageConstant;
import com.worldpay.test_harness.utils.ReadFile;
import com.worldpay.test_harness.utils.WriteFile;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Amount extends Activity {

	TextView screenId,PGTR,TextPaymentRef,tokenText, emailText, mobileText;
	Button submitButton;
	EditText amountEditText,pgtrEditText,paymentRefEditText,tokenEditText, emailEditText, mobileEditText;
	String transactionAmount, data;
	int screenID;
	AlertDialogScreen alert;
	static boolean output_flag = true;
    RadioGroup radioGroup;
    RadioButton cardPresent,cardNotPresent;
    String TXN_REFRENCE = null;
    int txnReference;
	int Inc_txnRef;
	String paymentReference = null;
	boolean isSocketTxn = false;
	
	/*************************/
	
	static Socket socket = null;
	static Socket intraMsgSocket = null;
	int inputOutputSocketPort = 10000;
	int intraMsgSocketPort = 8000;
	private String serverIpAddress = "localhost";
	private boolean connected = false;
	Thread cThread;
	Thread intraThread;
	InetAddress serverAddr;
	static ObjectInputStream ois = null;
	static ObjectOutputStream out = null;
	
	static ObjectInputStream intraMsgOis = null;
	static ObjectOutputStream intraMsgOut = null;
	
	String message;
	Handler mHandler;
	private Object line = null;
	private Object intraMsgline = null;
	boolean isAlert = false;
	String title = "";
	String alertMsg = "";
	
	/***************************/
	RadioGroup radioGroupCheckCard, radioGroupReceiptType;
	RadioButton checkcardTrue,checkcardFalse;
	Bundle bundle = null;
	
	EditText editMobile,editEmail;
	RadioButton radioMobile,radioEmail,radioNone;
	private String callBackUrlInput;
	private static String test = null;
	
	Button remoteConfigurationButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bundle = getIntent().getExtras();
		test = null;
		test = "kuldeep";
		//Added By Surabhi on 25-08-14
		callBackUrlInput = "71=" + getPackageName() + "\n";

		if (bundle != null){
			 screenID = bundle.getInt("SCREEN");
			 
			if(screenID == 3){
				setContentView(R.layout.checkcard);
				Intent in = getIntent();
				isSocketTxn = in.getBooleanExtra("SOCKET_TXN", false);
				createCheckCardUI();
			}
		   else{
				setContentView(R.layout.amount);
				Intent in = getIntent();
				isSocketTxn = in.getBooleanExtra("SOCKET_TXN", false);
				createUI();
			}
		}		
		sendHandler();
		/*mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if(msg != null){
					System.out.println("in handler::->");
					if(msg.what == 1) {
						Toast.makeText(Amount.this, "socket msg:"+message, Toast.LENGTH_LONG).show();
					} else if(msg.what == 2) {
						showOkDialog(Amount.this, title, alertMsg);
					}
				}
			}
		};*/
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
	    String responce = intent.getStringExtra("OUTPUT_TXT");
	    System.out.println("responce::"+responce);
	}
		//setContentView(R.layout.amount);
		//Intent in = getIntent();
		//isSocketTxn = in.getBooleanExtra("SOCKET_TXN", false);
		
	private void createCheckCardUI(){
		alert = new AlertDialogScreen();
		
		radioGroupCheckCard = (RadioGroup)findViewById(R.id.rgroupcheckcard);
		checkcardTrue = (RadioButton)findViewById(R.id.checkcardtrue);
		checkcardFalse = (RadioButton)findViewById(R.id.checkcardfalse);
		
		screenId = (TextView) findViewById(R.id.screen_name);
		
		/*Bundle bundle = getIntent().getExtras();*/
		if (bundle != null) {
			screenID = bundle.getInt("SCREEN");
			if (screenID == 3) {
				screenId.setText(R.string.checkcard);
				radioGroupCheckCard.setVisibility(View.VISIBLE);
			} else {
				screenId.setText(R.string.refund);
				radioGroup.setVisibility(View.VISIBLE);
			}
		}
		
		radioGroupCheckCard.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.checkcardfalse){
					radioGroupCheckCard.setVisibility(View.VISIBLE);
				}else if(checkedId == R.id.checkcardtrue){
					radioGroupCheckCard.setVisibility(View.VISIBLE);
				}
			}
		});
		

		submitButton = (Button) findViewById(R.id.button);
		submitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if (screenID == 3) {
					handleTxnRefrence();
					if(checkcardTrue.isChecked())
					{
						/*data = "1=" +txnReference + "\n" + "2=30" + "\n" + "25=" + "true"+ "\n" + "99=0";*/
						//data = "1=" +txnReference + "\n" + "2=30" + "\n" + "25=" + "true"+ "\n" +callBackUrlInput + "99=0";
					
						Home.generateSecureValues();
						data = "1=" +txnReference + "\n" + "2=30" + "\n" + "25=" + "true"+ "\n" +callBackUrlInput + "74=" + Home.salt + "\n" + "75=" + Home.hash + "\n" + "76=" + Home.timeStamp + "\n" + "99=0";
						
					}else
					{
						/*data = "1=" +txnReference + "\n" + "2=30" + "\n" + "25=" + "false"+ "\n" + "99=0";*/
						//data = "1=" +txnReference + "\n" + "2=30" + "\n" + "25=" + "false"+ "\n" +callBackUrlInput + "99=0";
					
						Home.generateSecureValues();
						data = "1=" +txnReference + "\n" + "2=30" + "\n" + "25=" + "false"+ "\n" +callBackUrlInput + "74=" + Home.salt + "\n" + "75=" + Home.hash + "\n" + "76=" + Home.timeStamp + "\n" + "99=0";
					
					}
					System.out.println("-CheckCard-");
				}
				showOkDialog(Amount.this,null,data);
			}
		});
	}
	private void createUI(){
		alert = new AlertDialogScreen();
		radioGroup = (RadioGroup)findViewById(R.id.rgroup);
		cardPresent = (RadioButton)findViewById(R.id.cp);
		cardNotPresent = (RadioButton)findViewById(R.id.cnp);
		screenId = (TextView) findViewById(R.id.screen_name);
		PGTR = (TextView) findViewById(R.id.PGTR);
		TextPaymentRef = (TextView) findViewById(R.id.paymentRef);
		
		amountEditText = (EditText) findViewById(R.id.editAmount);
		pgtrEditText = (EditText) findViewById(R.id.editpgtr);
		TXN_REFRENCE = ReadFile.readFile(Amount.this, "TxnRef");
		paymentRefEditText = (EditText) findViewById(R.id.editPaymentRef);
		radioGroupReceiptType = (RadioGroup)findViewById(R.id.rgroupreceipttype);
		
		tokenText = (TextView) findViewById(R.id.tokenText);
		tokenEditText = (EditText) findViewById(R.id.editToken);
		
		emailText = (TextView) findViewById(R.id.emailText);
		emailEditText = (EditText) (findViewById(R.id.editEmail));
		
		mobileText = (TextView) findViewById(R.id.mobileNumberText);
		mobileEditText = (EditText) findViewById(R.id.editMobileNumber);
		
		/*Bundle bundle = getIntent().getExtras();*/
		if(bundle != null){
			screenID = bundle.getInt("SCREEN");
			if(screenID == 1){
				 screenId.setText(R.string.sale);
				 radioGroup.setVisibility(View.GONE);
			}else if (screenID == 3){
				screenId.setText(R.string.checkcard);
				radioGroupCheckCard.setVisibility(View.VISIBLE);
			}else if (screenID == 4){
				screenId.setText(R.string.token_sale);
				radioGroup.setVisibility(View.GONE);
				tokenText.setVisibility(View.VISIBLE);
				tokenEditText.setVisibility(View.VISIBLE);
			}else if (screenID == 5){
				screenId.setText(R.string.token_refund);
				radioGroup.setVisibility(View.GONE);
				tokenText.setVisibility(View.VISIBLE);
				tokenEditText.setVisibility(View.VISIBLE);
				PGTR.setVisibility(View.VISIBLE);
				pgtrEditText.setVisibility(View.VISIBLE);
			}else{
				screenId.setText(R.string.refund);
				radioGroup.setVisibility(View.VISIBLE);
			}
			
			if(screenID != 3){
				emailText.setVisibility(View.VISIBLE);
				emailEditText.setVisibility(View.VISIBLE);
				mobileText.setVisibility(View.VISIBLE);
				mobileEditText.setVisibility(View.VISIBLE);
			}
		}		

		//Added by Surabhi on 20-05-15, in Refund Currently this functionality not available 
		cardNotPresent.setVisibility(View.GONE);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.cp){
					PGTR.setVisibility(View.GONE);
					pgtrEditText.setVisibility(View.GONE);
					
					TextPaymentRef.setVisibility(View.VISIBLE);
					paymentRefEditText.setVisibility(View.VISIBLE);
				}else if(checkedId == R.id.cnp){
					PGTR.setVisibility(View.VISIBLE);
					pgtrEditText.setVisibility(View.VISIBLE);
					
					TextPaymentRef.setVisibility(View.GONE);
					paymentRefEditText.setVisibility(View.GONE);
				}
			}
		});

		
		radioGroupReceiptType.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				System.out.println("in radioGroupReceiptType OnCheckedChangeListener::");
				handleReceiptType();
			}
		});
		
		amountEditText.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, android.view.MotionEvent event) {
				amountEditText.setFocusableInTouchMode(true);
				pgtrEditText.setFocusableInTouchMode(false);
				paymentRefEditText.setFocusableInTouchMode(false);
				emailEditText.setFocusableInTouchMode(false);
				mobileEditText.setFocusableInTouchMode(false);
				tokenEditText.setFocusableInTouchMode(false);
				return false;
			}
		});
		
		pgtrEditText.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, android.view.MotionEvent event) {
				pgtrEditText.setFocusableInTouchMode(true);
				paymentRefEditText.setFocusableInTouchMode(false);
				emailEditText.setFocusableInTouchMode(false);
				mobileEditText.setFocusableInTouchMode(false);
				tokenEditText.setFocusableInTouchMode(false);
				amountEditText.setFocusableInTouchMode(false);
				return false;
			}
		});
		
		
		paymentRefEditText.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, android.view.MotionEvent event) {
				paymentRefEditText.setFocusableInTouchMode(true);
				amountEditText.setFocusableInTouchMode(false);
				pgtrEditText.setFocusableInTouchMode(false);
				emailEditText.setFocusableInTouchMode(false);
				mobileEditText.setFocusableInTouchMode(false);
				tokenEditText.setFocusableInTouchMode(false);
				return false;
			}
		});
		
		tokenEditText.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, android.view.MotionEvent event) {
				tokenEditText.setFocusableInTouchMode(true);
				pgtrEditText.setFocusableInTouchMode(false);
				paymentRefEditText.setFocusableInTouchMode(false);
				emailEditText.setFocusableInTouchMode(false);
				mobileEditText.setFocusableInTouchMode(false);
				amountEditText.setFocusableInTouchMode(false);
				return false;
			}
		});
		
		emailEditText.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, android.view.MotionEvent event) {
				emailEditText.setFocusableInTouchMode(true);
				mobileEditText.setFocusableInTouchMode(false);
				tokenEditText.setFocusableInTouchMode(false);
				pgtrEditText.setFocusableInTouchMode(false);
				paymentRefEditText.setFocusableInTouchMode(false);
				amountEditText.setFocusableInTouchMode(false);
				return false;
			}
		});
		
		mobileEditText.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, android.view.MotionEvent event) {
				mobileEditText.setFocusableInTouchMode(true);
				emailEditText.setFocusableInTouchMode(false);
				tokenEditText.setFocusableInTouchMode(false);
				pgtrEditText.setFocusableInTouchMode(false);
				paymentRefEditText.setFocusableInTouchMode(false);
				amountEditText.setFocusableInTouchMode(false);
				return false;
			}
		});

		submitButton = (Button) findViewById(R.id.button);
		submitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				transactionAmount = amountEditText.getText().toString();
				/*if (transactionAmount == null || transactionAmount.trim().length() == 0 ) {
					alert.showOkDialog(Amount.this, MessageConstant.AMOUNT);
				} else if(!Check_Amount(transactionAmount) || transactionAmount.trim().equalsIgnoreCase("0") || transactionAmount.trim().equalsIgnoreCase("0.0")){
					alert.showOkDialog(Amount.this, MessageConstant.INVALID_AMOUNT);	
				}
				else if(pgtrEditText.getVisibility() == View.VISIBLE && pgtrEditText.getText().toString().length() == 0){
					alert.showOkDialog(Amount.this,"Please enter PGTR");
				}
				else {*/
				paymentReference = paymentRefEditText.getText().toString();
				if(paymentReference == null)
				{
					paymentReference = "";
				}
				
				String refInput = "";
				if(paymentReference != null && paymentReference.trim().length() > 0)
				{
					refInput = "29=" + paymentReference + "\n";
				}else
				{
					refInput = "";
				}
				
				/*String callBackUrlInput = "71=" + getPackageName() + "\n";*/
				
				
				handleTxnRefrence();
				handleReceiptType();
				EditText editMobile = (EditText)findViewById(R.id.edit_receipttype_mobile);
				EditText editEmail = (EditText)findViewById(R.id.edit_receipttype_email);
				String receiptTypeValue = "";
				String receiptTypeMobile = "";
				String receiptTypeEmail = "";
				System.out.println("editMobile::"+editMobile.getVisibility());
				System.out.println("editEmail::"+editEmail.getVisibility());
				if(editMobile.getVisibility() == View.VISIBLE) {
					receiptTypeMobile = editMobile.getText().toString();
				} else if(editEmail.getVisibility() == View.VISIBLE) {
					receiptTypeEmail = editEmail.getText().toString();
				}
				
				if(receiptTypeMobile != null && receiptTypeMobile.trim().length() > 0) {
					receiptTypeValue = "72=" + receiptTypeMobile + "\n";
				} else if(receiptTypeEmail != null && receiptTypeEmail.trim().length() > 0) {
					receiptTypeValue = "73=" + receiptTypeEmail + "\n";
				} else {
					receiptTypeValue = "";
				}
				System.out.println("receiptTypeValue::"+receiptTypeValue);
				if (screenID == 1) {
					/*data = "1=" +txnReference + "\n" + "2=0" + "\n" + "3=" + transactionAmount+ "\n" + refInput + "99=0";*/
					//data = "1=" +txnReference + "\n" + "2=0" + "\n" + "3=" + transactionAmount+ "\n" + refInput + callBackUrlInput +receiptTypeValue + "99=0";
					
					receiptTypeValue = setReceiptType();
					Home.generateSecureValues();
					data = "1=" +txnReference + "\n" + "2=0" + "\n" + "3=" + transactionAmount+ "\n" + refInput + callBackUrlInput +receiptTypeValue + "74=" + Home.salt + "\n" + "75=" + Home.hash + "\n" + "76=" + Home.timeStamp + "\n" + "99=0";
					
					System.out.println("-SALE-");
				}
				if (screenID == 2) {
					/*if(cardNotPresent.isChecked())
					{
						data = "1=" +txnReference + "\n" + "2=20" + "\n" + "3=" + transactionAmount+ "\n" + "13=" + pgtrEditText.getText().toString().trim()+"\n" + "99=0";
					}else
					{
						data = "1=" +txnReference + "\n" + "2=20" + "\n" + "3=" + transactionAmount+ "\n" + refInput + "99=0";
					}*/
					
					if(cardNotPresent.isChecked())
					{
						/*data = "1=" +txnReference + "\n" + "2=20" + "\n" + "3=" + transactionAmount+ "\n" + "13=" + pgtrEditText.getText().toString().trim() +"\n"+ receiptTypeValue + "99=0";*/
						//data = "1=" +txnReference + "\n" + "2=20" + "\n" + "3=" + transactionAmount+ "\n" + "13=" + pgtrEditText.getText().toString().trim() +"\n"+ receiptTypeValue +callBackUrlInput + "99=0";
					
						Home.generateSecureValues();
						data = "1=" +txnReference + "\n" + "2=20" + "\n" + "3=" + transactionAmount+ "\n" + "13=" + pgtrEditText.getText().toString().trim() +"\n"+ receiptTypeValue +callBackUrlInput + "74=" + Home.salt + "\n" + "75=" + Home.hash + "\n" + "76=" + Home.timeStamp + "\n" + "99=0";
					
					}else
					{
						/*data = "1=" +txnReference + "\n" + "2=20" + "\n" + "3=" + transactionAmount+ "\n" + refInput + receiptTypeValue + "99=0";*/
						//data = "1=" +txnReference + "\n" + "2=20" + "\n" + "3=" + transactionAmount+ "\n" + refInput + receiptTypeValue +callBackUrlInput + "99=0";
						receiptTypeValue = setReceiptType();
						Home.generateSecureValues();
						data = "1=" +txnReference + "\n" + "2=20" + "\n" + "3=" + transactionAmount+ "\n" + refInput + receiptTypeValue +callBackUrlInput + "74=" + Home.salt + "\n" + "75=" + Home.hash + "\n" + "76=" + Home.timeStamp + "\n" + "99=0";
					}
					System.out.println("-REFUND-");
				}
				if(screenID == 4){
					receiptTypeValue = setReceiptType();
					Home.generateSecureValues();
					data = "1=" +txnReference + "\n" + "2=12" + "\n" + "3=" + transactionAmount+ "\n" + "19=" + tokenEditText.getText().toString().trim()+ "\n"+ refInput + callBackUrlInput +receiptTypeValue + "74=" + Home.salt + "\n" + "75=" + Home.hash + "\n" + "76=" + Home.timeStamp + "\n" + "99=0";
					
					System.out.println("-TOKEN SALE-");
					
				}
				if(screenID == 5){
					receiptTypeValue = setReceiptType();
					Home.generateSecureValues();
					data = "1=" +txnReference + "\n" + "2=13" + "\n" + "3=" + transactionAmount+ "\n"+ "19=" + tokenEditText.getText().toString().trim()+ "\n"+ "13=" + pgtrEditText.getText().toString().trim()+ "\n" + refInput + callBackUrlInput +receiptTypeValue + "74=" + Home.salt + "\n" + "75=" + Home.hash + "\n" + "76=" + Home.timeStamp + "\n" + "99=0";
					
					System.out.println("-TOKEN REFUND-");
					
				}
				showOkDialog(Amount.this,null, data);
				//}

			}
		});
		
		
	}
	
	private String setReceiptType(){
		String receiptType = "";
		if(mobileEditText.getText().toString().length() > 0)
			receiptType = "72=" + mobileEditText.getText().toString() + "\n";
		if(emailEditText.getText().toString().length() > 0)
			receiptType = receiptType + "73="+ emailEditText.getText().toString() + "\n";
		return receiptType;
	}


	/*public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		switch (requestCode) {
		case 99:
			 System.out.println("in onActivityResult::"+resultCode);
			if (resultCode == Activity.RESULT_OK)
			{
//			 System.out.println("in onActivityResult::");
			
			}else if(resultCode == 99)
			{
			}
			break;
		}
	}*/
	
	public void showOkDialog(Context context, final String title, String msg) {
		System.out.println("in showOkDialog"+title);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(false);
		final Dialog dialog = new Dialog(context, android.R.style.Theme_Panel);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.drawable.alert_dialog_output);
		if(title != null) {
			dialog.setTitle(title);
		} else {
			dialog.setTitle("Input Request");
		}
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

		mHandler.post(new Runnable() {
			@Override
			public void run() {
				dialog.show();
			}
		});
		/*dialog.show();*/
		dialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				try {
					if(title != null) {
						System.out.println("**********");
					}else
					if(isSocketTxn) {
						/*if(socket != null && socket.isConnected()){
							System.out.println("socket is already connected::->>>>>>");
							Object object = (Object) data;
							try {
								out.flush();
								out.writeObject(object);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("in exception::");
								cThread = new Thread(new ClientThread());
								cThread.start();
							}
						} else {*/
						ClientThread.setInputData(data);
						cThread = new Thread(new ClientThread());
						cThread.start();
						/*}*/
					} else {
						
					if(Selection.APPLICATION_MODE){
						Intent intent = new Intent();
						intent.setComponent(new ComponentName("yes.worldpaytotal", "com.worldpay.wptmobile.screens.IntegrationSplashScreen"));
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
						intent.putExtra("INPUT_REQUEST", data);
						startActivity(intent);
						finish();
					} else{
						Intent intent = new Intent("mobileevt.intent.action.main");
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
						intent.putExtra("INPUT_REQUEST", data);
						System.out.println("intent ==>> "+intent);
						startActivity(intent);
						finish();
					}
					/*Intent intent = new Intent("mobileevt.intent.action.main");
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					intent.putExtra("INPUT_REQUEST", data);
					System.out.println("intent ==>> "+intent);
					startActivity(intent);
					finish();*/
					/*Intent intent = new Intent();
					intent.setComponent(new ComponentName("yes.worldpaytotal", "com.worldpay.wptmobile.screens.IntegrationSplashScreen"));
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					intent.putExtra("INPUT_REQUEST", data);
					startActivity(intent);
					finish();*/
					}
//					finish();
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(Amount.this, MessageConstant.APP_NOT_FOUND, Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		//Added by Surabhi on 17-11-14
		Button buttonUpdate = (Button) dialog.findViewById(R.id.update_btn);
		buttonUpdate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Amount.this,UpdateData.class);
				/*intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
				intent.putExtra("INPUT_REQUEST", data);
				System.out.println("intent ==>> "+intent);
				startActivity(intent);
				finish();				
			}
		});
		//End of changes
	}
	
	public void handleTxnRefrence()
	{
		try {

			if ((TXN_REFRENCE == null) || (TXN_REFRENCE.equalsIgnoreCase(""))) {

				TXN_REFRENCE = "1";
				WriteFile.writeFile(Amount.this, "TxnRef",TXN_REFRENCE);

				txnReference = Integer.parseInt(TXN_REFRENCE);
				Inc_txnRef = txnReference + 1;
			} else {
				TXN_REFRENCE = ReadFile.readFile(Amount.this,"TxnRef");
				txnReference = Integer.parseInt(TXN_REFRENCE);
				if (txnReference < 999999999) {
					Inc_txnRef = txnReference + 1;
				} else {
					Inc_txnRef = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		WriteFile.writeFile(Amount.this, "TxnRef",Integer.toString(Inc_txnRef));
	}
	
	public int handleTxnRefrence(String txnRef,Context context)
	{
		String TXN_REFRENCE = null;
		try {
			TXN_REFRENCE = txnRef;
			if ((TXN_REFRENCE == null) || (TXN_REFRENCE.equalsIgnoreCase(""))) {

				TXN_REFRENCE = "1";
				System.out.println(" Context ::"+context);
				WriteFile.writeFile(context, "TxnRef",TXN_REFRENCE);

				txnReference = Integer.parseInt(TXN_REFRENCE);
				Inc_txnRef = txnReference + 1;
			} else {
				TXN_REFRENCE = ReadFile.readFile(context,"TxnRef");
				txnReference = Integer.parseInt(TXN_REFRENCE);
				if (txnReference < 999999999) {
					Inc_txnRef = txnReference + 1;
				} else {
					Inc_txnRef = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		WriteFile.writeFile(context, "TxnRef",Integer.toString(Inc_txnRef));
		return txnReference;
	}
	
	private boolean Check_Amount(String transactionAmountString)
	{
		boolean isvalidAmount = false;
		if(transactionAmountString.contains("."))
		{
			if((transactionAmountString.length()==1) && (transactionAmountString.contains(".")) )
			{
				System.out.println("Invalid Amount..");
			}else
			{
			String final_Dec_string=null;
		int index=transactionAmountString.indexOf(".");
		int length=transactionAmountString.length();
		String string_befor_Dec=transactionAmountString.substring(0, index);
		String Dec_string=transactionAmountString.substring(index+1, length);
		StringBuffer temp = new StringBuffer(Dec_string);
		if(Dec_string.length()>=2)
		{
		 final_Dec_string=transactionAmountString.substring(index+1, index+3);
		}else
			if(Dec_string.length()==1)
			{
				final_Dec_string=transactionAmountString.substring(index+1);
			}if(Dec_string.length()==0)
			{
				return isvalidAmount;
			}
			
		if(index > 7)
		{
			System.out.println("Index>8========>");
		}
		else
		{
			System.out.println("index======>"+index);
			isvalidAmount=true;
		transactionAmountString=string_befor_Dec+"."+final_Dec_string;
		System.out.println("FINAL_AMOUNT===========>>"+transactionAmountString);
		return isvalidAmount;
		}}}
		else
		if(!transactionAmountString.contains(".") && transactionAmountString.length()>7)
		{		
			System.out.println("Invalid Amount..");
	    }else{
	    	isvalidAmount=true;
	    	return isvalidAmount;
	    }
		return isvalidAmount;
	}
	
		
	private void sendHandler() {
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if(msg != null){
					System.out.println("in handler::->");
					if(msg.what == 1) {
						Toast.makeText(Amount.this, /*"socket msg:"+*/ClientThread.message, Toast.LENGTH_LONG).show();
					} 
				}
			}
		};
		ClientThread.setHandler(mHandler);
	}
	
	/*******************************************/
	private void handleReceiptType() {
		editMobile = (EditText)findViewById(R.id.edit_receipttype_mobile);
		editEmail = (EditText)findViewById(R.id.edit_receipttype_email);
		radioMobile = (RadioButton)findViewById(R.id.rb_mobilenumber);
		radioEmail = (RadioButton)findViewById(R.id.rb_emailaddress);
		radioNone = (RadioButton)findViewById(R.id.rb_none);
		if(radioMobile.isChecked()) {
			editMobile.setVisibility(View.VISIBLE);
			editEmail.setVisibility(View.GONE);
			amountEditText.setFocusable(false);
			paymentRefEditText.setFocusable(false);
			editMobile.setFocusable(true);
		} else if(radioEmail.isChecked()) {
			editMobile.setVisibility(View.GONE);
			editEmail.setVisibility(View.VISIBLE);
			amountEditText.setFocusable(false);
			paymentRefEditText.setFocusable(false);
			editEmail.setFocusable(true);
		} else {
			editMobile.setVisibility(View.GONE);
			editEmail.setVisibility(View.GONE);
			editMobile.setFocusable(false);
			editEmail.setFocusable(false);
		}
		editMobile.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, android.view.MotionEvent event) {
				editMobile.setFocusableInTouchMode(true);
				return false;
			}
		});
		
		editEmail.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, android.view.MotionEvent event) {
				editEmail.setFocusableInTouchMode(true);
				return false;
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("in onResume::"+test);
	}
	
	
}
