package com.worldpay.test_harness.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.content.Context;
public class WriteFile {
	public  static void writeFile(Context context,String fileName ,String data) {

		FileOutputStream fOut = null;
		OutputStreamWriter osw = null;
		String filePath = context.getFilesDir().getAbsolutePath();
		File file = new File(filePath, fileName);
		try {
			fOut = new FileOutputStream(file);
			osw = new OutputStreamWriter(fOut);
			osw.write(data);
			osw.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("In Exception in writeFile:Exception::");
		}
		finally {
			try {
				osw.close();
				fOut.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("In IOException in writeFile:IOException::");
			}
		}
	}

}
