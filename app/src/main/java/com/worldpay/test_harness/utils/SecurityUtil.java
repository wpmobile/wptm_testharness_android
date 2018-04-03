package com.worldpay.test_harness.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/***
 * 
 * @author Huzefa Putliwala
 * Date : 23-09-2014
 */

public class SecurityUtil{
	
	/**
	 * Algorithm type used for generating encrypted hash
	 */
	private final String ALGO_TYPE = "HmacSHA512";	
	/**
	 * Byte encoding used for calculating hash
	 */
	private final String BYTE_ENCODING = "ASCII";
	/**
	 * Time zone used for generating salt
	 */
	private final String TIME_ZONE = "UTC";
		
	/**
	 * UUID seperator
	 */
	private static final String UUID_SEP = "-";

	public static SecurityUtil securityUtil = null;
	
	public static SecurityUtil getInstance(){
		if(!(securityUtil instanceof SecurityUtil))
			securityUtil = new SecurityUtil();
		
		return securityUtil;
	}
	
	
	public String generateHmac(String UUID,String salt,String timeStamp) throws Exception{
		return getEncryptHash(getPaymentSecretString(UUID,salt,timeStamp),UUID);
	}
	
	/**
	 * 
	 * @return Random number generated using java's UUID and timeStamp
	 */
	public String getSalt(){
		GregorianCalendar gc2 = new GregorianCalendar();
		gc2.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
		return UUID.randomUUID().toString().replaceAll(UUID_SEP, "")+ gc2.getTimeInMillis();
	}
	/**
	 * 
	 * @param Customer's UUID
	 * @param salt
	 * @param timeStamp
	 * @return (Customer's UUID + salt + timeStamp)
	 * @throws Exception
	 */
	private String getPaymentSecretString(String UUID,String salt,String timeStamp) throws Exception{
		if((UUID == null || UUID.trim().length() == 0) || (salt == null || salt.trim().length() == 0)
				|| (timeStamp == null ||  timeStamp.trim().length() == 0)){
			throw new Exception("Mandatory fields not present");
		}
		
		return (UUID + salt + timeStamp);	
	}
	/**
	 * 			
	 * @param secret : Secret string 
	 * @param secretKey : It will be security context
	 * @return  HMAC Digest
	 * @throws Exception 
	 */
	private String getEncryptHash(String secret, String secretKey) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(BYTE_ENCODING),ALGO_TYPE);
		Mac mac = Mac.getInstance(ALGO_TYPE);
		mac.init(keySpec);
		
		byte[] result = mac.doFinal(secret.getBytes(BYTE_ENCODING));		
		String secureHash = toHexString(result);		
		return secureHash;
	}
		
	public String getTimeStamp()
	{
		return (getCurrentDate()+getCurrentTime());
		
	
	}
	
	public String getCurrentDate()
	{
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("ddMMyyyy");
		return dateFormat.format(new Date());
	}
	
	public String getCurrentTime(){
		java.text.SimpleDateFormat obDateFormat = new java.text.SimpleDateFormat("HHmmss");
		Calendar time = Calendar.getInstance();
		return obDateFormat.format(time.getTime());
	}
	
	
	
	/*
     * Converts a byte to hex digit and writes to the supplied buffer
     */
    private void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

    /*
     * Converts a byte array to hex string
     */
    private String toHexString(byte[] block) {
        StringBuffer buf = new StringBuffer();

        int len = block.length;

        for (int i = 0; i < len; i++) {
             byte2hex(block[i], buf);            
        } 
        return buf.toString();
    }
}