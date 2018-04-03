package com.worldpay.test_harness.screen;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.worldpay.test_harness.R;
import com.worldpay.test_harness.utils.ParseException;
import com.worldpay.test_harness.utils.SMRequestResponseParser;
import com.worldpay.test_harness.utils.TransactionResponse;

public class Receipt extends Activity {

	TextView pgtr,autCode,responseMsg,textPGTR,textAuth,textResponse;
	String responce,authCode,capturePGTR,responseMessage,message;
	int txnResult,txtType;
	static TransactionResponse transactionResponse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recept);
	    Intent intent = getIntent();
	    String responce = intent.getStringExtra("OUTPUT_TXT");
	    
	    System.out.println("responce----->>>  "+responce);
		SMRequestResponseParser requestResponseParser = new SMRequestResponseParser();
		try {
			transactionResponse = requestResponseParser.parseResponse(responce);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		pgtr=(TextView)findViewById(R.id.pgtr);
		autCode=(TextView)findViewById(R.id.ac);
		responseMsg=(TextView)findViewById(R.id.rm);
		textPGTR=(TextView)findViewById(R.id.PGTR);
		textAuth=(TextView)findViewById(R.id.AC);
		textResponse=(TextView)findViewById(R.id.RM);
		
		capturePGTR = transactionResponse.getPgtr();
		authCode = transactionResponse.getAuthCode();
		txnResult = transactionResponse.getTxnResult();
		txtType = transactionResponse.getTxnType();
		
		if (capturePGTR==null)
		{
		  pgtr.setVisibility(View.GONE);
		  textPGTR.setVisibility(View.GONE);	
		}else
		{
			pgtr.setText(capturePGTR);
		}
		if (authCode==null)
		{
			autCode.setVisibility(View.GONE);			
			textAuth.setVisibility(View.GONE);
		}else
		{		
			autCode.setText(authCode);
		}
		
		if(txnResult == 42)
		{
			responseMessage = "Already a running transaction is exist.";
		}else
		{
		
			if (txtType == 3) {
				responseMessage = getCancelTransactionStatus(txnResult);
			}
			else if (txnResult == 1 || txnResult == 2 || (txtType == 20 && txnResult == 0)) {
					responseMessage = "Transaction Approved";
			} else if (txnResult == 9) {
					responseMessage = "Transaction Cancelled";
			} else if (txnResult == 5) {
					responseMessage = "Declined Offline";
			} else if(txnResult == 19) {
					responseMessage = "Transaction Aborted";
			} else if(txnResult == 46) {
					responseMessage = "Update Successful";
			} else if(txnResult == 47) {
					responseMessage = "Update failure";
			} else if(txnResult == 48) {
				responseMessage = "Error logs send successfully";
			} else if(txnResult == 49) {
				responseMessage = "Unable to send error logs";
			}
			else if(txtType == 28) {
				String appVer = transactionResponse.getAppVer();
				if(appVer != null) {
					responseMessage = appVer;
				}
				
			}
			  else {
					responseMessage = "Transaction Declined";
			}
		}
		
		responseMsg.setText(responseMessage);
		if(responce != null){
			showOkDialog(Receipt.this, responce);
		}
	}

	
	public String getCancelTransactionStatus(int result) {
		try {

			switch (result) {
			case 0:
				message = "Cancel Approved";
				break;

			case 1:
				message = "Cancel Rejected: Batch Closed";
				break;
			case 3:
				message = "Cancel Rejected: Data Mismatch";
				break;
			case 4:
				message = "Cancel Rejected: Invalid Transaction State";
				break;
			case 5:
				message = "Cancel Rejected: Invalid Transaction Type";
				break;
			case 6:
				message = "Cancel Rejected: PAN Mismatch";
				break;
			case 7:
				message = "Cancel Rejected: Expiry Date Mismatch";
				break;
			case 99:
				message = "Cancel Rejected: API Failed";
				break;
			case 2:
				message = "Cancel Rejected: Invalid PGTR";
				break;
			default:
				message = "Cancel Rejected";
			}
			return message;
		} catch (Exception e) {
			return "Cancel Rejected";
		}
	}
	
	public void showOkDialog(Context context, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(false);
		final Dialog dialog = new Dialog(context, android.R.style.Theme_Panel);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.drawable.alert_dialog_output);
		dialog.setTitle("Output Request");
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
		//Added By Surabhi on 18-11-14 for handling editable mode
		Button updateButton = (Button) dialog.findViewById(R.id.update_btn);
		updateButton.setVisibility(View.GONE);
		//End of changes
		buttonOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(Receipt.this,Home.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		System.out.println("receipt 1 finishes");
		//finish();
	}
}
