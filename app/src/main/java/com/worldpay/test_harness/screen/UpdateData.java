package com.worldpay.test_harness.screen;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.worldpay.test_harness.R;
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
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
/*
 * Created By Surabhi Bagrecha for updating data
 */
public class UpdateData extends Activity{

	String dataReceived = null;
	LinearLayout childLayout, subChildLayout;
	LayoutParams setDimensions, btnDimensions;
	
	EditText parameterName;
	EditText parameterValue;
	Button submitBtn;
	List<EditText> allEditableText = new ArrayList<EditText>();
	List<EditText> allEditableValue = new ArrayList<EditText>();
	LinkedHashMap<String, String> linkedHMap = new LinkedHashMap<String,String>();
	Set keyset = null;
	Iterator iteratedString;
	Map.Entry mapEntry;
	
	String updatedData = "";
	Handler mHandler;
	Thread cThread;
	Thread intraThread;
	InetAddress serverAddr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatedata);
		
		Intent data = getIntent();
		if(data != null){
		dataReceived = data.getStringExtra("INPUT_REQUEST");
		stringSplit(dataReceived);
		}		
		drawLayoutDynamically();
		viewEventListener();
		sendHandler();
	}	
	
	public void stringSplit(String str){	
		String initialString = str;
		String[] splitString = initialString.split("\n");
		String[] finalSplittedString = null;
		String key = "", value = "";
		int j = 0;
		
		for(int i = 0; i < splitString.length; i++ ){
			finalSplittedString = splitString[i].split("=");
			if(finalSplittedString != null){
				key = finalSplittedString[j];
				if((j+1) < finalSplittedString.length){
					value = finalSplittedString[j+1];
				}else{
					value = "";
				}
				linkedHMap.put(key, value);
				j = 0;
			}
		}
		keyset=linkedHMap.entrySet();
		iteratedString = keyset.iterator();
	}
	
	public void drawLayoutDynamically(){
		childLayout = (LinearLayout) findViewById(R.id.mainLayout);
		childLayout.setGravity(Gravity.CENTER);
		
		for(int i= 0; i < linkedHMap.size(); i++ ){
			subChildLayout = new LinearLayout(this);
			subChildLayout.setOrientation(LinearLayout.HORIZONTAL);
			setDimensions = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
			setDimensions.setMargins(10, 30, 10, 0);
			
			mapEntry = (Map.Entry)iteratedString.next();
			parameterName = new EditText(this);
			parameterName.setLayoutParams(setDimensions);
			allEditableText.add(parameterName);
			parameterName.setPadding(8, 8, 8, 8);
			parameterName.setText(mapEntry.getKey().toString());
			parameterName.setEnabled(false);
			subChildLayout.addView(parameterName);
			  
			parameterValue = new EditText(this);
			parameterValue.setLayoutParams(setDimensions);
			allEditableValue.add(parameterValue);
			parameterValue.setText(mapEntry.getValue().toString());
			/*if(parameterName.getText().toString().trim().equals("2") || parameterName.getText().toString().trim().equals("71") || parameterName.getText().toString().trim().equals("25") || parameterName.getText().toString().trim().equals("99") ){
			    parameterValue.setEnabled(false);
			   }
			else if(parameterName.getText().toString().trim().equals("3")){
			     parameterValue.setInputType(InputType.TYPE_CLASS_NUMBER);
			   }*/
			parameterValue.setPadding(8, 8, 8, 8);
			subChildLayout.addView(parameterValue);
			childLayout.addView(subChildLayout);
		}
		
		submitBtn = new Button(this);
		btnDimensions = new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		btnDimensions.setMargins(180, 50, 180, 50);
		submitBtn.setLayoutParams(btnDimensions);
		submitBtn.setText(R.string.ok_btn);
		submitBtn.setPadding(8, 8, 8, 8);
		childLayout.addView(submitBtn);
	}
	
	public void viewEventListener(){
		submitBtn.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				for(int j= 0; j < linkedHMap.size(); j++ ){
					String index = allEditableText.get(j).getText().toString();
					String value = allEditableValue.get(j).getText().toString();
					updatedData = updatedData + index + "=" + value + "\n";
				}
				showOkDialog(UpdateData.this,null,updatedData);
			}
		});
	}
	
	public void showOkDialog(Context context, final String title, String msg) {
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
					}else{
					/*Intent intent = new Intent("mobileevt.intent.action.main");
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					intent.putExtra("INPUT_REQUEST", updatedData);
					startActivity(intent);
					finish();*/
						if(Selection.APPLICATION_MODE){
							Intent intent = new Intent();
							intent.setComponent(new ComponentName("yes.worldpaytotal", "com.worldpay.wptmobile.screens.IntegrationSplashScreen"));
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
							intent.putExtra("INPUT_REQUEST", updatedData);
							startActivity(intent);
							finish();
						} else {
							Intent intent = new Intent("mobileevt.intent.action.main");
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
							intent.putExtra("INPUT_REQUEST", updatedData);
							startActivity(intent);
							finish();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(UpdateData.this, MessageConstant.APP_NOT_FOUND, Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		Button buttonUpdate = (Button) dialog.findViewById(R.id.update_btn);
		buttonUpdate.setVisibility(View.GONE);
	}

	private void sendHandler() {
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if(msg != null){
					System.out.println("in handler::->");
					if(msg.what == 1) {
						Toast.makeText(UpdateData.this, /*"socket msg:"+*/ClientThread.message, Toast.LENGTH_LONG).show();
					} 
				}
			}
		};
		ClientThread.setHandler(mHandler);
	}
}
