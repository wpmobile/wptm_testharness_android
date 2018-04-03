package com.worldpay.test_harness.screen;

import com.worldpay.test_harness.R;
import com.worldpay.test_harness.utils.MessageConstant;
import com.worldpay.test_harness.utils.ReadFile;
import com.worldpay.test_harness.utils.SecurityUtil;

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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {

	 //Megha 110315 - Added remoteConfiguration button
	Button saleButton,refundButton,cancelButton,socketButton,checkcardButton,osUpgradeButton,appInfo,remoteConfiguration,tokenSaleButton,tokenRefundButton;
	// Added by huzefa on 29.4.2015 for ROL
	Button rolButton;
	// end
	Button dayFirstButton;
	boolean isSocketTxn = false;
	String data;
	Thread cThread;
	private String callBackUrlInput;
	private static SecurityUtil securityUtil = null;
	
	public static String hash = null;
	public static String salt = null;
	public static String timeStamp = null;
	
	// sample UUID for testm2
	//private static final String UUID = "ae91b7bb6eac2e9c973857a9ec352fb7bae8397b";
//	private static final String UUID = "ae91b7bb6eac2e9c973857a9ec352fb7bae8397a";//UUID for testwpzinc1@gmail.com
//	private static final String UUID = "ae91b7bb6eac2e9c973857a9ec352fb7bae8397b";//UUID for test0012
//	private static final String UUID = "ae91b7bb6eac2e9c973857a9ec352fb7bae8397c";//UUID for test0061
						   
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.home);
         Intent in = getIntent();
         isSocketTxn = in.getBooleanExtra("SOCKET_TXN", false);
         
		 saleButton= (Button) findViewById(R.id.Button1);  
		 refundButton= (Button) findViewById(R.id.Button2); 
		 cancelButton= (Button) findViewById(R.id.Button3); 
		 checkcardButton = (Button) findViewById(R.id.Button4);
		 socketButton= (Button) findViewById(R.id.Button5); 
		 osUpgradeButton= (Button) findViewById(R.id.Button6); 
		 appInfo= (Button) findViewById(R.id.Button7); 
		 //Megha 110315
		 remoteConfiguration= (Button)findViewById(R.id.remoteConfigurationButton);
		 
		 // Added by huzefa on 29.4.2015 for ROL
		 rolButton = (Button)findViewById(R.id.rolButton);
		 // end
		 
		 dayFirstButton = (Button)findViewById(R.id.dayfirstBtn);
		 tokenSaleButton = (Button) findViewById(R.id.tokenSaleBtn);
		 tokenRefundButton = (Button) findViewById(R.id.tokenRefundBtn);
		 
		 if(isSocketTxn) {
			 socketButton.setVisibility(View.GONE);
			 /*cancelButton.setVisibility(View.GONE);*/
		 }

		//Added By Surabhi on 25-08-14
		 callBackUrlInput = "71=" + getPackageName() + "\n";
		 
		 saleButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {		
					Intent intent = new Intent(Home.this,Amount.class);
					if(isSocketTxn){
						intent.putExtra("SOCKET_TXN", true);
					}
					intent.putExtra("SCREEN",1);
					startActivity(intent);  
	            }	 			
	 		});
		        
		 refundButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {		
					Intent intent = new Intent(Home.this,Amount.class); 
					if(isSocketTxn){
						intent.putExtra("SOCKET_TXN", true);
					}
					intent.putExtra("SCREEN",2);
					startActivity(intent); 
	            }	 			
	 		});
		 
		 osUpgradeButton.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View arg0) {	
				 try{
					 /*data = "1=12" + "\n" + "2=71" + "\n" + "99=0";*/
					 /*data = "1=12" + "\n" + "2=71" + "\n" + callBackUrlInput + "99=0";*/
					 String txnRef = ReadFile.readFile(Home.this,"TxnRef");
					int txnReference = new Amount().handleTxnRefrence(txnRef,Home.this);
					 
					//data = "1=" +txnReference + "\n" + "2=71" + "\n" + callBackUrlInput + "99=0";
					 
					generateSecureValues();
					data = "1=" +txnReference + "\n" + "2=71" + "\n" + callBackUrlInput + "74=" + salt + "\n" + "75=" + hash + "\n" + "76=" + timeStamp + "\n" + "99=0";
					
					System.out.println("data =" +data);
					 
					 
					 /*Intent intent = new Intent("mobileevt.intent.action.main");
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("INPUT_REQUEST", data);
						startActivity(intent);
						finish();*/
					 showOkDialog(Home.this, data, true);

				 } catch (Exception e) {
					 e.printStackTrace();
					 Toast.makeText(Home.this, MessageConstant.APP_NOT_FOUND, Toast.LENGTH_SHORT).show();
				 }
			 }	 			
		 });

		 cancelButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {		
					Intent intent = new Intent(Home.this,Cancel.class);
					if(isSocketTxn){
						intent.putExtra("SOCKET_TXN", true);
					}
					startActivity(intent);  
	            }	 			
	 		});
		 
		 socketButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {		
					Intent intent = new Intent(Home.this,Home.class);
					intent.putExtra("SOCKET_TXN", true);
					startActivity(intent);  
					finish();
	            }	 			
	 		});
			
			checkcardButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {		
					Intent intent = new Intent(Home.this,Amount.class); 
					intent.putExtra("SCREEN",3);
					if(isSocketTxn){
						intent.putExtra("SOCKET_TXN", true);
					}
					startActivity(intent); 
	            }	 			
	 		});
			
			 appInfo.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {	
						System.out.println(" Get App Info Pressed ");
						String txnRef = ReadFile.readFile(Home.this,"TxnRef");
						int txnReference = new Amount().handleTxnRefrence(txnRef,Home.this);
						/*String inputReq = "1=" +txnReference + "\n" + "2=28" + "\n" + "99=0";*/
						//String inputReq = "1=" +txnReference + "\n" + "2=28" + "\n"+ callBackUrlInput + "99=0";
						
						generateSecureValues();
						String inputReq = "1=" +txnReference + "\n" + "2=28" + "\n"+ callBackUrlInput + "74=" + salt + "\n" + "75=" + hash + "\n" + "76=" + timeStamp + "\n" + "99=0";
												
						showOkDialog(Home.this, inputReq,false);
		            }	 			
		 		});
			 
			 //Megha 110315 - OnClickListener of Remote Configuration
			 remoteConfiguration.setOnClickListener(new View.OnClickListener() {
				 public void onClick(View arg0) {	
					 try{
					    String txnRef = ReadFile.readFile(Home.this,"TxnRef");
						int txnReference = new Amount().handleTxnRefrence(txnRef,Home.this);
    					generateSecureValues();
						data = "1=" +txnReference + "\n" + "2=72" + "\n" + callBackUrlInput + "74=" + salt + "\n" + "75=" + hash + "\n" + "76=" + timeStamp + "\n" + "99=0";
						System.out.println("data =" +data);
				        showOkDialog(Home.this, data, true);
    				 } catch (Exception e) {
						 e.printStackTrace();
						 Toast.makeText(Home.this, MessageConstant.APP_NOT_FOUND, Toast.LENGTH_SHORT).show();
					 }
				 }	 			
			 });
			 //End of changes
			 
			// Added by huzefa on 29.4.2015 for ROL
			rolButton.setOnClickListener(new View.OnClickListener() {
				 public void onClick(View arg0) {	
					 try{
					    String txnRef = ReadFile.readFile(Home.this,"TxnRef");
						int txnReference = new Amount().handleTxnRefrence(txnRef,Home.this);
    					generateSecureValues();
						data = "1=" +txnReference + "\n" + "2=73" + "\n" + callBackUrlInput + "74=" + salt + "\n" + "75=" + hash + "\n" + "76=" + timeStamp + "\n" + "99=0";
						System.out.println("data =" +data);
				        showOkDialog(Home.this, data, true);
    				 } catch (Exception e) {
						 e.printStackTrace();
						 Toast.makeText(Home.this, MessageConstant.APP_NOT_FOUND, Toast.LENGTH_SHORT).show();
					 }
				 }	 			
			 });
			// end
			
			//SCRB_006
			dayFirstButton.setOnClickListener(new View.OnClickListener() {
				 public void onClick(View arg0) {	
					 try{
					    String txnRef = ReadFile.readFile(Home.this,"TxnRef");
						int txnReference = new Amount().handleTxnRefrence(txnRef,Home.this);
						generateSecureValues();
						data = "1=" +txnReference + "\n" + "2=74" + "\n" + callBackUrlInput + "74=" + salt + "\n" + "75=" + hash + "\n" + "76=" + timeStamp + "\n" + "99=0";
						System.out.println("data =" +data);
				        showOkDialog(Home.this, data, true);
   				 } catch (Exception e) {
						 e.printStackTrace();
						 Toast.makeText(Home.this, MessageConstant.APP_NOT_FOUND, Toast.LENGTH_SHORT).show();
					 }
				 }	 			
			 });
			
			tokenSaleButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {		
					Intent intent = new Intent(Home.this,Amount.class);
					if(isSocketTxn){
						intent.putExtra("SOCKET_TXN", true);
					}
					intent.putExtra("SCREEN",4);
					startActivity(intent);  
	            }	 			
	 		});
		        
			tokenRefundButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {		
					Intent intent = new Intent(Home.this,Amount.class); 
					if(isSocketTxn){
						intent.putExtra("SOCKET_TXN", true);
					}
					intent.putExtra("SCREEN",5);
					startActivity(intent); 
	            }	 			
	 		});
}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if(isSocketTxn){
			Intent intent = new Intent(Home.this,Home.class);
			intent.putExtra("SOCKET_TXN", false);
			startActivity(intent);
			finish();
		}
	}
	
	public void showOkDialog(final Context context, final String msg, final boolean flag) {
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
					/*System.out.println(" Input Request :: "+msg);
					Intent intent = new Intent("mobileevt.intent.action.main");
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					intent.putExtra("INPUT_REQUEST", msg);
					startActivity(intent);*/
					/*if(flag){
						finish();
					}*/
					if(Selection.APPLICATION_MODE){
						Intent intent = new Intent();
						intent.setComponent(new ComponentName("yes.worldpaytotal", "com.worldpay.wptmobile.screens.IntegrationSplashScreen"));
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
						intent.putExtra("INPUT_REQUEST", msg);
						startActivity(intent);
					} else {
						Intent intent = new Intent("mobileevt.intent.action.main");
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
						intent.putExtra("INPUT_REQUEST", msg);
						startActivity(intent);
					}
				} catch (Exception e) {
					e.printStackTrace();
					/*Toast.makeText(Amount.this, MessageConstant.APP_NOT_FOUND, Toast.LENGTH_SHORT).show();*/
					Toast.makeText(context, MessageConstant.APP_NOT_FOUND, Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		//Added by Surabhi on 17-11-14
		Button buttonUpdate = (Button) dialog.findViewById(R.id.update_btn);
		buttonUpdate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Home.this,UpdateData.class);
				/*intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
				intent.putExtra("INPUT_REQUEST", msg);
				System.out.println("intent ==>> "+intent);
				startActivity(intent);
				finish();				
			}
		});
		//End of changes
	}
	
	
	/* Added by Huzefa on 01/10/2014 for third party secure integration*/
	public static void generateSecureValues()
	{
		securityUtil = SecurityUtil.getInstance();
		
		timeStamp = securityUtil.getTimeStamp();
		salt = securityUtil.getSalt();
		
		try{
			hash = securityUtil.generateHmac(Selection.UUID, salt, timeStamp);
		}catch(Exception e){
			 System.out.println("Exception whle preparing Hash:"+e);
		 }
	
		/*timeStamp = "19092014060739";
		salt = "da2325e9a24e4b89ae00a8752d78ac561399888246076";
		hash = "3435C3DA31DB3E0746A63BD20FCC3D8085ED85B8443E3932FE1D46AA4E8C2753B4029DE999622010BCB2EE8773D6D2C4C8B9F247086D21266E65F2FDC0EF69D9";
*/	
		System.out.println("UUID in test harness is : " + Selection.UUID);
		System.out.println("hash in test harness is : " + hash);
		System.out.println("salt in test harness is : " + salt);
		System.out.println("timeStamp in test harness is : " + timeStamp);
	}
	// end
	
	/*public static String getApplictionPackage(Context context){
		String packageName = context.getPackageName();
		System.out.println("package name is "+packageName);
		return packageName;	
	}*/
}
