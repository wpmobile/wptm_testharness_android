 package com.worldpay.test_harness.screen;

import com.worldpay.test_harness.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Selection extends Activity{
	
	EditText selectedText;
	Spinner selectedValue, selectedMode;
	Button okBtn;
//	private String[] values = {"Select Any One","15asd7bb6eac5e9c147852a9ec352fb7bae32145","ae91b7bb6eac2e9c973857a9ec352fb7bae8397b","ae91b7bb6eac2e9c973857a9ec352fb7bae8397c", "ae91b7bb6eac2e9c973857a9ec352fb7bae8393s", "ae91b7bb6eac2e9c973857a9ec352fb7bae8396a"};
//	private String[] values = {"Select Any One","ae91b7bb6eac2e9c973857a9ec352fb7bae8397b","ae91b7bb6eac2e9c973857a9ec352fb7bae8397c", "ae91b7bb6eac2e9c973857a9ec352fb7bae8393s", "ae91b7bb6eac2e9c973857a9ec352fb7bae8396a"};
//	private String[] values = {"Select Any One","15asd7bb6eac5e9c147852a9ec352fb7bae32145","ae11b7bb6eac2e9c973857a9ec352fb7bae83976","ae91b7bb6eac2e9c973857a9ec352fb7bae8397b","ae91b7bb6eac2e9c973857a9ec352fb7bae8397c", "ae91b7bb6eac2e9c973857a9ec352fb7bae8393s", "ae91b7bb6eac2e9c973857a9ec352fb7bae8396a"};

	/*private String[] name = {"Select User Name",
			"Ravi.Katiyar",
			"Ashish.Mathur",
			"Kamlesh.Garg",
			"Surabhi.Bagrecha", 
			"Bipul.Jain",
			"Ankita Bakshi",
			"Deepti.Gour",
			"Mayank.Garg",
			"Shailesh.Tiwari"};
				*/
			
			private String[] name = {"Select User Name",
			"Deepti.Gour",
			"Mayank.Garg",
			"Shashikant.Patil.UK",
			"Ashish.Mathur",
			"pritesh.arzare.roi@yes-pay.net",
			"Bipul.Jain",
			"Surabhi.Bagrecha",
			"Ravi.Katiyar",
			"Shailesh_Tiwari",
			"Manoj Chandel",
			"YESPAY_16",
			"Sanjay_Sharma",
			"qawptm",
			"qawptm2",
			"qaroi",
			"qaroi2",
			"qaroi3",
			"Ankita.Bakshi.ROI",
			"kamlesh.garg.roi@yes-pay.net",
			"kamlesh.garg@yes-pay.net",
			"Shashikant.Patil.ROI",
			"Ankita.Bakshi.UK"
			};
	
	/*private String[] name = {"Select User Name",
			"qawptm",
			"qawptm2",
			"qaroi",
			"qaroi2",
			"qaroi3",
			"testm2"};*/
			
			private String[]  appMode= {"Select App Mode",
					"SECURE",
					"NON-SECURE",
					};
			
	/*For PenTest*/
	/*private String[] name = {"Select User Name",
			"mayank.garg@yes-pay.net",
			"shailesh.tiwari@yes-pay.net",
			"pentestt.wptm@yes-pay.net",
			"pentestt1.wptm@yes-pay.net"};*/
	
//	private String[] values = {"Select Any One",
//			"1E52588A43143E3505980669B53D8F02AF376311D56E5C2E6630002ECF3C7DB523CA5E607728D84BD9E412FF21862BBD8795B8036E345034D5052548D64747E",
//			"D9A7AC40B2FBAE4F10BD19AEA3BD5C7BF770A30F295900DBCB5DA5D6B6527A481DBB1E5051EBE1C675E582B444779F6A6CFDCB75C6BADB405E069FECFF0494E",
//			"D3CAB8F4B236056C5AA29DCC86B0C59B12F65E5A21795710FFA0E47842436966589625625BAB1D956481D18B9E48BE5BE2D0820AA39247D4AED506DD27AB358",
//			"B4C182E11C881D5A2A03E64159C40E40F58FDAC562E3EE744C87D08F7B1429E9D71D495646A687F08B462E6B8F108FDC8564EDA4D19255369F47F233449BCC9", 
//			"800EDED51A50A12FB11E80C0767090FC76195519E55140509400F5B167E07E70EC2767E695D3FB13C5283CFB2197000D15B30E49019628BD46850075992D9B3"};
	
	
	
	public static String UUID = "";
	
	public static boolean APPLICATION_MODE = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selection);
		
		selectedText = (EditText) findViewById(R.id.selectedvalue);
		selectedValue = (Spinner) findViewById(R.id.dropdownlist);
		selectedMode = (Spinner) findViewById(R.id.options);
		okBtn = (Button) findViewById(R.id.okBtn);
		
		ArrayAdapter <String> itemAdapter = new ArrayAdapter<String>(Selection.this,android.R.layout.simple_spinner_item, name);
		itemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		selectedValue.setAdapter(itemAdapter);
		
		
		ArrayAdapter <String> modeAdapter = new ArrayAdapter<String>(Selection.this,android.R.layout.simple_spinner_item, appMode);
		modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		selectedMode.setAdapter(modeAdapter);
		
		selectedMode.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if(position == 0){
				}else if(position == 1){
					System.out.println("Enable Secure Mode ");
					APPLICATION_MODE = true;
				}else if(position == 2){
					APPLICATION_MODE = false;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		selectedValue.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String str = "";
				if(position == 0){
					selectedText.setText(str);	
				}else if(position == 1){
					selectedText.setText(R.string.Deepti_Gour);	
				}else if(position == 2){
					selectedText.setText(R.string.Mayank_Garg);	
				}else if(position == 3){
					selectedText.setText(R.string.Shashikant_Patil);	
				}else if(position == 4){
					selectedText.setText(R.string.Ashish_Mathur);	
				}else if(position == 5){
					selectedText.setText(R.string.Pritesh_Arzare_Roi);	
				}else if(position == 6){
					selectedText.setText(R.string.Bipul_Jain);	
				}else if(position == 7){
					selectedText.setText(R.string.Surabhi_Bagrecha);	
				}else if(position == 8){
					selectedText.setText(R.string.Ravi_Katiyar);	
				}else if(position == 9){
					selectedText.setText(R.string.Shailesh_Tiwari_UK);	
				}else if(position == 10){
					selectedText.setText(R.string.Manoj_Chandel);	
				}else if(position == 11){
					selectedText.setText(R.string.YESPAY_16);	
				}else if(position == 12){
					selectedText.setText(R.string.Sanjay_Sharma);	
				}else if(position == 13){
					selectedText.setText(R.string.qawptm);	
				}else if(position == 14){
					selectedText.setText(R.string.qawptm2);	
				}else if(position == 15){
					selectedText.setText(R.string.qaroi);	
				}else if(position == 16){
					selectedText.setText(R.string.qaroi2);	
				}else if(position == 17){
					selectedText.setText(R.string.qaroi3);	
				}else if(position == 18){
					selectedText.setText(R.string.Ankita_Bakshi_roi);	
				}else if(position == 19){
					selectedText.setText(R.string.Kamlesh_Garg_Roi);	
				}else if(position == 20){
					selectedText.setText(R.string.Kamlesh_Garg_UK);	
				}else if(position == 21){
					selectedText.setText(R.string.Shashikant_Patil_Roi);	
				}else if(position == 22){
					selectedText.setText(R.string.Ankita_Bakshi_uk);	
				}else{
					str  = (String)parent.getItemAtPosition(position);
					selectedText.setText(str);
					}
				
				/*if(position == 0){
					selectedText.setText(str);	
				}else if(position == 1){
					selectedText.setText(R.string.qawptm);	
				}else if(position == 2){
					selectedText.setText(R.string.qawptm2);	
				}else if(position == 3){
					selectedText.setText(R.string.qaroi);	
				}else if(position == 4){
					selectedText.setText(R.string.qaroi2);	
				}else if(position == 5){
					selectedText.setText(R.string.qaroi3);	
				}else if(position == 6){
					selectedText.setText(R.string.testm2);	
				}else{
					str  = (String)parent.getItemAtPosition(position);
					selectedText.setText(str);
				}*/
				
				/*For PenTest*/
				/*if(position == 0){
					selectedText.setText(str);	
				}else if(position == 1){
					selectedText.setText(R.string.Mayank_Garg);	
				}else if(position == 2){
					selectedText.setText(R.string.Shailesh_Tiwari);	
				}else if(position == 3){
					selectedText.setText(R.string.Mayank_Garg);	
				}else if(position == 4){
					selectedText.setText(R.string.Shailesh_Tiwari);	
				}*/
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				selectedText.setText("");
			}
			
		});
		
		viewEventHandling();		
	}

	private void viewEventHandling() {
		// TODO Auto-generated method stub
	
		okBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			UUID = selectedText.getText().toString();
            if(UUID.trim().length() > 0){
            	Intent home = new Intent(Selection.this,Home.class);
            	startActivity(home);
            	finish();
            	}else{
            		Toast.makeText(Selection.this, "UUID should not be blank.", Toast.LENGTH_SHORT).show();
            	}
			}
		});
	}

}
