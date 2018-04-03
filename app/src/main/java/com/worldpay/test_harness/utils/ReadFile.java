package com.worldpay.test_harness.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

public class ReadFile {

	public static String readFile(Context context , String fileName) {
		FileInputStream fIn = null;
		InputStreamReader isr = null;
		String data = null;
		try {
			String filePath = context.getFilesDir().getAbsolutePath();
			File file = new File(filePath, fileName);
			if (!file.exists()) 
			{
				file = new File(filePath, fileName);		
				file.createNewFile();
			} 
			else 
			{
              System.out.println("file already exist::");
			}

			try {
				fIn = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				System.out.println("In Exception:FileNotFoundException::");
			}
			isr = new InputStreamReader(fIn);

			int c, counter = 0;

			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = "";
				while ((c = br.read()) != -1) {
					line += (char) c;
					counter++;
				}
			} catch (IOException e) {
				System.out.println("In Exception:IOException::");
			}
			char[] inputBuffer = new char[counter];
			try {
				isr.read(inputBuffer);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			data = new String(inputBuffer);
		}catch(Exception e){	
		}

		finally {
			try {
				isr.close();
				fIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
}
