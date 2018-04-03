package com.worldpay.test_harness.utils;

import com.worldpay.test_harness.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialogScreen extends Activity {

	AlertDialog.Builder alertbox;
	Dialog dialog;
	ProgressDialog Progress;
	boolean f = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public void showOkDialog(Context context, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(false);
		final Dialog dialog = new Dialog(context, android.R.style.Theme_Panel);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.drawable.alert_dialog_ok);
//		dialog.setCancelable(false);
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
	}
}
