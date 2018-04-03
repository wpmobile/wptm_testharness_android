
package com.worldpay.test_harness.utils ;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;



public class SMRequestResponseParser {

	private static final String RECORD_SEPARATOR = "\n" ;
	private String residual = "" ;
	private static final int IN_END = 99 ;

	private static final int OUT_CHIP_STRIPE_KEYED = 1 ;
	private static final int OUT_TXN_TYPE = 2 ;
	private static final int OUT_TXN_RESULT = 3 ;
	private static final int OUT_AUTH_CODE = 4 ;
	private static final int OUT_APPL_PAN = 5 ;
	private static final int OUT_APPL_LABEL = 6 ;
	private static final int OUT_APPL_EFFECTIVE_DATE = 7 ;
	private static final int OUT_TXN_DATE = 8 ;
	private static final int OUT_TXN_TIME = 9 ;
	private static final int OUT_CARD_HOLDER_NAME = 10 ;
	private static final int OUT_CARD_HOLDER_NAME_EXT = 11 ;
	private static final int OUT_MID = 12 ;
	private static final int OUT_TID = 13 ;
	private static final int OUT_CARD_VERIFICATION_METHOD = 14 ;
	private static final int OUT_START_DATE = 15 ;
	private static final int OUT_TOTAL_DEBIT_NO_TRANS = 16 ;
	private static final int OUT_TOTAL_CREDIT_NO_TRANS = 17 ;
	private static final int OUT_TOTAL_DEBITS = 18 ;
	private static final int OUT_TOTAL_CREDITS = 19 ;
	private static final int OUT_RECON_STATUS = 20 ;
	private static final int OUT_TXN_SEQ_NO = 21 ;
	private static final int OUT_MERCHANT_ADDRESS = 22 ;
	private static final int OUT_MERCHANT_NAME = 23 ;
	private static final int OUT_SERVER_DATE_TIME = 24 ;
	private static final int OUT_BATCH_NUMBER = 25 ;
	private static final int OUT_REFERRAL_TELEPHONE1 = 26 ;
	private static final int OUT_REFERRAL_TELEPHONE2 = 27 ;
	private static final int OUT_PGTR = 28 ;
	private static final int OUT_APPL_ID = 29 ;
	private static final int OUT_APPL_PAN_SEQ_NUMBER = 30 ;
	private static final int OUT_TSI = 31 ;
	private static final int OUT_TVR = 32 ;
	private static final int OUT_RETAINTION_REMINDER = 33 ;
	private static final int OUT_DECLARATION = 34 ;
	private static final int OUT_ADDITIONAL_RESPONSE_DATA = 35 ;
	private static final int OUT_RECEIPT_NUMBER = 36 ;
	private static final int OUT_CARD_EXPIRY_DATE = 37 ;
	private static final int OUT_TOTAL_AMOUNT = 38 ;
	private static final int OUT_CASH_AMOUNT = 39 ;
	private static final int OUT_GRATUITY_AMOUNT = 40 ;

	private static final int OUT_CARD_TYPE = 41 ;
	private static final int OUT_PRODS_ALLOWED = 42 ;
	private static final int OUT_VEH_REG_NO_REQD = 43 ;
	private static final int OUT_MILEAGE_REQD = 44 ;
	private static final int OUT_COMPANY_ID_REQD = 45 ;
	private static final int OUT_TRACK2_DATA = 46 ;
	private static final int OUT_FUEL_CARD_TYPE = 47 ;
	private static final int OUT_AUTHORISATION_ID = 49 ;
	private static final int OUT_PAYMENT_GATWAY_URL = 50 ;
	private static final int OUT_ACS_URL = 51 ;
	private static final int OUT_PAREQ = 52 ;
	private static final int OUT_APP_VER = 65 ;
	private static final int OUT_CONTINUE_FLAG = 95 ;
	private static final int OUT_TARGET_REFERENCE = 97 ;
	private static final int OUT_TXN_REFERENCE = 98 ;
	private static final int OUT_END = 99 ;

	private static final String MESSAGE_SEPARATOR = "--"  ;
	private static final String OUT_DELIMITER = MESSAGE_SEPARATOR + RECORD_SEPARATOR ;

	private Vector responseList ;
	private TransactionResponse txnResponse = null ;
	
	Calendar calFromDate = Calendar.getInstance();
	Calendar calToDate = Calendar.getInstance();

	public TransactionResponse [] parseMultipleResponse (String response) throws ParseException {
		StringTokenizer inputTokenizer = new StringTokenizer (response, MESSAGE_SEPARATOR) ;
		if (responseList == null) {
			responseList = new Vector () ;
		}
		boolean parsedOk = true ;
		while (inputTokenizer.hasMoreElements()) {
			String oneResponse = inputTokenizer.nextToken() ;
			//StringUtils.print ("oneResponse: " + oneResponse);
			TransactionResponse txnResponse = parseResponse (oneResponse + "--") ;
			if (txnResponse != null) {
				responseList.addElement(txnResponse) ;
			} else {
				parsedOk = false ;
				break ;
			}
		}
		if (parsedOk) {
			TransactionResponse list[] = new TransactionResponse[responseList.size()] ;
			for (int i = 0; i < list.length; i++) {
				TransactionResponse resp = (TransactionResponse) responseList.elementAt(i) ;
				list[i] = resp ;
			}
			return list ;
		} else {
			return null ;
		}
	}


	
	
	public TransactionResponse parseResponse (String response) throws ParseException {
		StringTokenizer inputTokenizer = new StringTokenizer (residual + response, RECORD_SEPARATOR) ;
		SimpleDateFormat sdf1 = new SimpleDateFormat ("ddMMyyyy") ;
		SimpleDateFormat sdf2 = new SimpleDateFormat ("HHmmss") ;
		SimpleDateFormat sdf3 = new SimpleDateFormat ("MMyy") ;
		Date tempDate = null ;
		if (txnResponse == null) {
			txnResponse = new TransactionResponse () ;
		}
		while (inputTokenizer.hasMoreElements ()) {
			String line = inputTokenizer.nextToken () ;
			residual = line ;
			StringTokenizer fieldTokenizer = new StringTokenizer (line, "=") ;
			/* Tasneem - Modifying to accomodate query string parameters in ACS URL
			if (fieldTokenizer.countTokens() == 2) {
			*/
			if (fieldTokenizer.countTokens() >= 2) {
				try {
					int id = Integer.parseInt (fieldTokenizer.nextToken ()) ;
					//String value = fieldTokenizer.nextToken () ;
					String value = line.substring(line.indexOf("=")+1);
					//StringUtils.print ("parseResponse=>value: " + value);
					switch (id) {
						case OUT_TXN_RESULT:
							txnResponse.setTxnResult (Integer.parseInt(value.trim())) ;
							break ;
						case OUT_ADDITIONAL_RESPONSE_DATA:
							txnResponse.setAdditionalResponseData (value) ;
							break ;
						case OUT_AUTH_CODE:
							txnResponse.setAuthCode (value) ;
							break ;
						case OUT_PGTR:
							txnResponse.setPgtr (value) ;
							break ;
						case OUT_CHIP_STRIPE_KEYED :
							txnResponse.setPosEntryMode(Integer.parseInt(value.trim())) ;
							break ;
						case OUT_TXN_TYPE :
							txnResponse.setTxnType(Integer.parseInt(value.trim())) ;
							break ;
						case OUT_APPL_PAN :
							txnResponse.setPan (value) ;
							break ;
						case OUT_APPL_LABEL :
							txnResponse.setApplLabel(value) ;
							break ;
						case OUT_APPL_EFFECTIVE_DATE :
							tempDate = sdf1.parse (value, new ParsePosition(0)) ;
							txnResponse.setApplEffectiveDate (tempDate) ;
							break ;
						case OUT_TXN_DATE :
							//input date
							tempDate = sdf1.parse (value, new ParsePosition(0)) ;
							if (tempDate != null) {
								//first element
								if (txnResponse.getTxnDate() == null) {
									txnResponse.setTxnDate (tempDate) ;
								} else {
									//time already set
									/*GregorianCalendar gc = new GregorianCalendar () ;
									gc.setTime (tempDate) ;
									GregorianCalendar gc2 = new GregorianCalendar () ;
									gc2.setTime (txnResponse.getTxnDate()) ;
									gc2.set(Calendar.YEAR, gc.get(Calendar.YEAR)) ;
									gc2.set(Calendar.MONTH, gc.get(Calendar.MONTH)) ;
									gc2.set(Calendar.DAY_OF_MONTH, gc.get(Calendar.DAY_OF_MONTH)) ;*/
									
									Calendar calFromDate1 = Calendar.getInstance();
									calFromDate1.setTime(tempDate);
									Calendar calToDate1 = Calendar.getInstance();
									calToDate1.setTime(txnResponse.getTxnDate());
									calToDate1.set(Calendar.YEAR, calFromDate1.get(Calendar.YEAR));
									calToDate1.set(Calendar.MONTH, calFromDate1.get(Calendar.MONTH));
									calToDate1.set(Calendar.DAY_OF_MONTH, calFromDate1.get(Calendar.DAY_OF_MONTH));
									txnResponse.setTxnDate (calToDate1.getTime()) ;
								}
							}
							break ;
						case OUT_TXN_TIME :
							tempDate = sdf2.parse (value, new ParsePosition(0)) ;
							if (tempDate != null) {
								if (txnResponse.getTxnDate() == null) {
									txnResponse.setTxnDate (tempDate) ;
								} else {
									/*GregorianCalendar gc = new GregorianCalendar () ;
									gc.setTime (tempDate) ;
									GregorianCalendar gc2 = new GregorianCalendar () ;
									gc2.setTime (txnResponse.getTxnDate()) ;
									gc2.set(Calendar.HOUR_OF_DAY, gc.get(Calendar.HOUR_OF_DAY)) ;
									gc2.set(Calendar.MINUTE, gc.get(Calendar.MINUTE)) ;
									gc2.set(Calendar.SECOND, gc.get(Calendar.SECOND)) ;
									txnResponse.setTxnDate (gc2.getTime()) ;*/
									
									Calendar calFromDate1 = Calendar.getInstance();
									calFromDate1.setTime(tempDate);
									Calendar calToDate1 = Calendar.getInstance();
									calToDate1.setTime(txnResponse.getTxnDate());
									calToDate1.set(Calendar.HOUR_OF_DAY, calFromDate1.get(Calendar.HOUR_OF_DAY));
									calToDate1.set(Calendar.MINUTE, calFromDate1.get(Calendar.MINUTE));
									calToDate1.set(Calendar.SECOND, calFromDate1.get(Calendar.SECOND));
									txnResponse.setTxnDate (calToDate1.getTime()) ;
								}
							}
							break ;
						case OUT_CARD_HOLDER_NAME :
							txnResponse.setCardHolderName (value) ;
							break ;
						case OUT_CARD_HOLDER_NAME_EXT :
							txnResponse.setCardHolderNameExt (value) ;
							break ;
						case OUT_MID :
							txnResponse.setMid (value) ;
							break ;
						case OUT_TID :
							txnResponse.setTid (value) ;
							break ;
						case OUT_CARD_VERIFICATION_METHOD :
							txnResponse.setCardVerificationMethod (Integer.parseInt(value.trim())) ;
							break ;
						case OUT_START_DATE :
							tempDate = sdf3.parse (value, new ParsePosition(0)) ;
							txnResponse.setStartDate (tempDate) ;
							break ;
						case OUT_TOTAL_DEBIT_NO_TRANS :
							txnResponse.setTotalDebitNumberOfTransaction(Integer.parseInt(value.trim())) ;
							break ;
						case OUT_TOTAL_CREDIT_NO_TRANS :
							txnResponse.setTotalCreditNumberOfTransaction(Integer.parseInt(value.trim())) ;
							break ;
						case OUT_TOTAL_DEBITS :
							int amounDebit = (int)(Float.parseFloat(value.trim())*100);
							System.out.println("OUT_TOTAL_DEBITS::"+amounDebit);
							txnResponse.setTotalDebits (amounDebit) ;
//							txnResponse.setTotalDebits (Integer.parseInt(value.trim())) ;
							break ;
						case OUT_TOTAL_CREDITS :
							int amounCredit = (int)(Float.parseFloat(value.trim())*100);
							txnResponse.setTotalCredits (amounCredit) ;
//							txnResponse.setTotalCredits (Integer.parseInt(value.trim())) ;
							break ;
						case OUT_RECON_STATUS :
							txnResponse.setReconStatus (Integer.parseInt(value.trim())) ;
							break ;
						case OUT_TXN_SEQ_NO :
							txnResponse.setTxnSeqNo(Integer.parseInt(value.trim())) ;
							break ;
						case OUT_MERCHANT_ADDRESS :
							txnResponse.setMerchantAddress(value) ;
							break ;
						case OUT_MERCHANT_NAME :
							txnResponse.setMerchantName (value) ;
							break ;
						case OUT_SERVER_DATE_TIME :
							break ;
						case OUT_BATCH_NUMBER :
							txnResponse.setBatchNumber (Integer.parseInt(value.trim())) ;
							break ;
						case OUT_REFERRAL_TELEPHONE1 :
							txnResponse.setReferralTelephone1(value) ;
							break ;
						case OUT_REFERRAL_TELEPHONE2 :
							txnResponse.setReferralTelephone2(value) ;
							break ;
						case OUT_APPL_ID :
							txnResponse.setApplId(value) ;
							break ;
						case OUT_APPL_PAN_SEQ_NUMBER :
							txnResponse.setApplPanSeqNumber (Integer.parseInt(value.trim())) ;
							break ;
						case OUT_TSI :
							txnResponse.setTsi (value) ;
							break ;
						case OUT_TVR :
							txnResponse.setTvr (value) ;
							break ;
						case OUT_RETAINTION_REMINDER :
							txnResponse.setRetaintionReminder (value) ;
							break ;
						case OUT_DECLARATION :
							txnResponse.setDeclaration (value) ;
							break ;
						case OUT_RECEIPT_NUMBER :
							txnResponse.setReceiptNumber (value) ;
							break ;
						case OUT_CARD_EXPIRY_DATE :
							tempDate = sdf3.parse (value, new ParsePosition(0)) ;
							txnResponse.setCardExpiryDate (tempDate) ;
							break ;
						case OUT_TOTAL_AMOUNT :
							txnResponse.setTotalAmount (Integer.parseInt(value.trim())) ;
							break ;
						case OUT_CASH_AMOUNT :
							txnResponse.setCashAmount (Integer.parseInt(value.trim())) ;
							break ;
						case OUT_GRATUITY_AMOUNT :
							/*txnResponse.setGratuityAmount (Integer.parseInt(value.trim())) ;*/
							int gratuity = StringtoInt(value);
							txnResponse.setGratuityAmount(gratuity) ;
							break ;
						case OUT_CARD_TYPE :
							break ;
						case OUT_PRODS_ALLOWED :
							break ;
						case OUT_VEH_REG_NO_REQD :
							break ;
						case OUT_MILEAGE_REQD :
							break ;
						case OUT_COMPANY_ID_REQD :
							break ;
						case OUT_TRACK2_DATA :
							txnResponse.setTrack2Data (value) ;
							break ;
						case OUT_FUEL_CARD_TYPE :
							break ;
						case OUT_CONTINUE_FLAG :
							if (value.trim().equals("1")) {
								txnResponse.setContinueTxnFlag(true) ;
							} else {
								txnResponse.setContinueTxnFlag(false) ;
							}
							break ;
						case OUT_TARGET_REFERENCE :
							txnResponse.setTargetReference (value) ;
							break ;
						case OUT_TXN_REFERENCE :
							txnResponse.setTxnReference (value) ;
							break ;
						case OUT_AUTHORISATION_ID:
							txnResponse.setAuthorisationId (value);
							break;
						case OUT_PAYMENT_GATWAY_URL:
							txnResponse.setPaymentGatewayURL (value);
							break;
						case OUT_ACS_URL:
							//StringUtils.print("In RequestResponseParser,parseResponse(), ACSURL is : " + value);
							txnResponse.setAcsUrl (value);
							break;
						case OUT_PAREQ:
							//StringUtils.print("In RequestResponseParser,parseResponse(), PAREQ is : " + value);
							txnResponse.setPaReq (value);
							break;
						case OUT_APP_VER:
							//StringUtils.print("In RequestResponseParser,parseResponse(), PAREQ is : " + value);
							txnResponse.setAppVer (value);
							break;
						case IN_END:
							//StringUtils.print ("End reached. Resetting residual") ;
							residual = "" ;
							TransactionResponse returnVal = txnResponse ;
							txnResponse = null ;
							return returnVal ;
					}
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					throw new ParseException ("Invalid input: " + nfe) ;
				}
			} else if (line.equals("--")) {
				residual = "" ;
				TransactionResponse returnVal = txnResponse ;
				txnResponse = null ;
				if (returnVal == null)
					
				return returnVal ;
			}
		}
		return null ;
	}
	
	
	
	
	
	
	
	

	private static String prepareResponseString (TransactionResponse response) {
		
		// Add By Vishal Gupta for REASON_CANCEL_COMPLETE Response 6/9/2008 5:24PM
		String responseMessage = "" ;
		if (response.getStatus() == TransactionResponse.REASON_CHECKSTATUS) {
            responseMessage += OUT_TXN_RESULT + "=" + response.getTxnResult() + RECORD_SEPARATOR;
            return responseMessage;
        }
		
		if (response.getStatus() == TransactionResponse.REASON_CANCEL_COMPLETE) {
			responseMessage += OUT_CHIP_STRIPE_KEYED + "=" + response.getPosEntryMode() + RECORD_SEPARATOR;
			if (response.getTxnResult() != 0) {
				responseMessage += OUT_TXN_RESULT + "=" + response.getTxnResult() + RECORD_SEPARATOR;
			}
			else {
				responseMessage += OUT_TXN_RESULT + "=" + com.worldpay.test_harness.utils.YESEFTConst.TRANSACTION_ABORTED + RECORD_SEPARATOR;
			}
			responseMessage += OUT_TXN_TYPE + "=" + response.getTxnType() + RECORD_SEPARATOR ;
			responseMessage += OUT_CARD_TYPE + "=" + response.getCardType() + RECORD_SEPARATOR ;
			return responseMessage ;
		}
			
			
		if (response.getStatus() != TransactionResponse.STATUS_OK) {
			if (response.getContinueTxnFlag() == true) {
				responseMessage += OUT_CONTINUE_FLAG + "=1" + RECORD_SEPARATOR;
			}
			
			if (response.getTxnResult() != 0) {
				responseMessage += OUT_TXN_RESULT + "=" + response.getTxnResult() + RECORD_SEPARATOR;
			}
			else {
				responseMessage += OUT_TXN_RESULT + "=" + com.worldpay.test_harness.utils.YESEFTConst.TRANSACTION_ABORTED + RECORD_SEPARATOR;
			}
			 
			if (response.getTargetReference() != null && !response.getTargetReference().trim().equals("")) {
				responseMessage += OUT_TARGET_REFERENCE + "=" + response.getTargetReference() + RECORD_SEPARATOR;
			}
			if (response.getTxnReference() != null) {
				responseMessage += OUT_TXN_REFERENCE + "=" + response.getTxnReference() + RECORD_SEPARATOR ;
			}
			return responseMessage ;
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat ("ddMMyyyy") ;
		SimpleDateFormat sdf2 = new SimpleDateFormat ("HHmmss") ;
		SimpleDateFormat sdf3 = new SimpleDateFormat ("MMyy") ;
		//format and set Appleffectivedate
		if (response.getTxnReference() != null) {
			responseMessage = OUT_TXN_REFERENCE + "=" + response.getTxnReference() + RECORD_SEPARATOR;
		}

		if (response.getApplEffectiveDate () != null) {
			responseMessage += OUT_APPL_EFFECTIVE_DATE + "=" + sdf1.format (response.getApplEffectiveDate()) + RECORD_SEPARATOR ;
		}
		if (response.getApplId() != null && !response.getApplId().trim().equals("")) {
			responseMessage += OUT_APPL_ID + "=" + response.getApplId() + RECORD_SEPARATOR;
		}
		if (response.getApplLabel() != null && !response.getApplLabel().trim().equals("")) {
			responseMessage += OUT_APPL_LABEL + "=" + response.getApplLabel() + RECORD_SEPARATOR;
		}
		if (response.getPan() != null && !response.getPan().trim().equals("")) {
			responseMessage += OUT_APPL_PAN + "=" + response.getPan() + RECORD_SEPARATOR;
		}
		if (response.getAuthCode() != null && !response.getAuthCode().trim().equals("")) {
			responseMessage += OUT_AUTH_CODE + "=" + response.getAuthCode() + RECORD_SEPARATOR;
		}
		if (response.getCardHolderName() != null && !response.getCardHolderName().trim().equals("")) {
			responseMessage += OUT_CARD_HOLDER_NAME + "=" + response.getCardHolderName() + RECORD_SEPARATOR;
		}
		if (response.getCardHolderNameExt() != null && !response.getCardHolderNameExt().trim().equals("")) {
			responseMessage += OUT_CARD_HOLDER_NAME_EXT + "=" + response.getCardHolderNameExt() + RECORD_SEPARATOR;
		}
		if (response.getCardVerificationMethod() != 0) {
			responseMessage += OUT_CARD_VERIFICATION_METHOD + "=" + response.getCardVerificationMethod() + RECORD_SEPARATOR;
		}

		responseMessage += OUT_CHIP_STRIPE_KEYED + "=" + response.getPosEntryMode() + RECORD_SEPARATOR;

		if (response.getMerchantAddress() != null && !response.getMerchantAddress().trim().equals("")) {
			responseMessage += OUT_MERCHANT_ADDRESS + "=" + response.getMerchantAddress() + RECORD_SEPARATOR;
		}
		if (response.getMid() != null && !response.getMid().trim().equals("")) {
			responseMessage += OUT_MID + "=" + response.getMid() + RECORD_SEPARATOR;
		}
		if (response.getMerchantName() != null && !response.getMerchantName().trim().equals("")) {
			responseMessage += OUT_MERCHANT_NAME + "=" + response.getMerchantName() + RECORD_SEPARATOR;
		}
		if (response.getPgtr() != null && !response.getPgtr().trim().equals("")) {
			responseMessage += OUT_PGTR + "=" + response.getPgtr() + RECORD_SEPARATOR;
		}
		if (response.getReferralTelephone1() != null && !response.getReferralTelephone1().trim().equals("")) {
			responseMessage += OUT_REFERRAL_TELEPHONE1 + "=" + response.getReferralTelephone1() + RECORD_SEPARATOR;
		}
		if (response.getReferralTelephone2() != null && !response.getReferralTelephone2().trim().equals("")) {
			responseMessage += OUT_REFERRAL_TELEPHONE2 + "=" + response.getReferralTelephone2() + RECORD_SEPARATOR;
		}
		//format and set startDate
		if (response.getStartDate () != null) {
			responseMessage += OUT_START_DATE + "=" + sdf3.format (response.getApplEffectiveDate()) + RECORD_SEPARATOR ;
		}
		if (response.getTid() != null && !response.getTid().trim().equals("")) {
			responseMessage += OUT_TID + "=" + response.getTid() + RECORD_SEPARATOR;
		}
		//format and set transactionDate/transaction time
		if (response.getTxnDate () != null) {
			responseMessage += OUT_TXN_DATE + "=" + sdf1.format (response.getTxnDate()) + RECORD_SEPARATOR ;
			responseMessage += OUT_TXN_TIME + "=" + sdf2.format (response.getTxnDate()) + RECORD_SEPARATOR ;
		}

		if (response.getTxnResult() != 0) {
			responseMessage += OUT_TXN_RESULT + "=" + response.getTxnResult() + RECORD_SEPARATOR;
		} else {
			responseMessage += OUT_TXN_RESULT + "=" + com.worldpay.test_harness.utils.YESEFTConst.TRANSACTION_ABORTED + RECORD_SEPARATOR;
		}
		if (response.getTxnSeqNo() != 0) {
			responseMessage += OUT_TXN_SEQ_NO + "=" + response.getTxnSeqNo() + RECORD_SEPARATOR;
		}

		responseMessage += OUT_TXN_TYPE + "=" + response.getTxnType() + RECORD_SEPARATOR ;

		if (response.getDeclaration() != null && !response.getDeclaration().trim().equals("")) {
			responseMessage += OUT_DECLARATION + "=" + response.getDeclaration() + RECORD_SEPARATOR;
		}
		if (response.getRetaintionReminder() != null && !response.getRetaintionReminder().trim().equals("")) {
			responseMessage += OUT_RETAINTION_REMINDER + "=" + response.getRetaintionReminder() + RECORD_SEPARATOR;
		}
		if (response.getAdditionalResponseData() != null && !response.getAdditionalResponseData().trim().equals("")) {
			responseMessage += OUT_ADDITIONAL_RESPONSE_DATA + "=" + response.getAdditionalResponseData() + RECORD_SEPARATOR;
		}
		if (response.getReceiptNumber() != null && !response.getReceiptNumber().trim().equals("")) {
			responseMessage += OUT_RECEIPT_NUMBER + "=" + response.getReceiptNumber() + RECORD_SEPARATOR;
		}
		//format and set expirydate
		if (response.getCardExpiryDate () != null) {
			responseMessage += OUT_CARD_EXPIRY_DATE + "=" + sdf3.format (response.getCardExpiryDate()) + RECORD_SEPARATOR ;
		}
		if (response.getTotalAmount() != 0) {
			responseMessage += OUT_TOTAL_AMOUNT + "=" + response.getTotalAmount() + RECORD_SEPARATOR;
		}
		/*
		if (response.getCashAmount() != 0) {
			responseMessage += OUT_CASH_AMOUNT + "=" + response.getCashAmount() + RECORD_SEPARATOR;
		}
		*/
		if (response.getGratuityAmount() != 0) {
			responseMessage += OUT_GRATUITY_AMOUNT + "=" + response.getGratuityAmount() + RECORD_SEPARATOR;
		}
		if (response.getTrack2Data() != null && !response.getTrack2Data().trim().equals("")) {
			responseMessage += OUT_TRACK2_DATA + "=" + response.getTrack2Data() + RECORD_SEPARATOR;
		}
		if (response.getApplPanSeqNumber() >=0) {
			responseMessage += OUT_APPL_PAN_SEQ_NUMBER + "=" + response.getApplPanSeqNumber() + RECORD_SEPARATOR;
		}
		if (response.getTsi() != null && !response.getTsi().trim().equals("")) {
			responseMessage += OUT_TSI + "=" + response.getTsi() + RECORD_SEPARATOR;
		}
		if (response.getTvr() != null && !response.getTvr().trim().equals("")) {
			responseMessage += OUT_TVR + "=" + response.getTvr() + RECORD_SEPARATOR;
		}
		if (response.getTargetReference() != null && !response.getTargetReference().trim().equals("")) {
			responseMessage += OUT_TARGET_REFERENCE + "=" + response.getTargetReference() + RECORD_SEPARATOR;
		}
		if (response.getContinueTxnFlag() == true) {
			responseMessage += OUT_CONTINUE_FLAG + "=1" + RECORD_SEPARATOR;
		}
		if (response.getAuthorisationId() != null) {
			responseMessage += OUT_AUTHORISATION_ID + "=" + response.getAuthorisationId() + RECORD_SEPARATOR;
		}
		if (response.getPaymentGatewayURL() != null) {
			responseMessage += OUT_PAYMENT_GATWAY_URL + "=" + response.getPaymentGatewayURL() + RECORD_SEPARATOR;
		}
		if (response.getAcsUrl() != null) {
			responseMessage += OUT_ACS_URL + "=" + response.getAcsUrl() + RECORD_SEPARATOR;
		}
		if (response.getPaReq() != null) {
			responseMessage += OUT_PAREQ + "=" + response.getPaReq() + RECORD_SEPARATOR;
		}

		return responseMessage ;
	}

	public static String prepareResponse(TransactionResponse response) {
		String responseMessage = prepareResponseString (response) ;
		responseMessage += OUT_END + "=0" + RECORD_SEPARATOR;
		return responseMessage ;
	}

	public static String prepareResponse(TransactionResponse []responses) {
		String responseMessage = "" ;
		for (int respCount = 0 ; respCount < responses.length; respCount++) {
			if (respCount != 0) {
				responseMessage += OUT_DELIMITER ;
			}
			responseMessage += prepareResponseString (responses[respCount]) ;
		}
		responseMessage += OUT_END + "=0" + RECORD_SEPARATOR;
		return responseMessage ;
	}	
	
	public static int StringtoInt(String amount){
	    try {
			if(amount.length() > 0 && amount.length() <= 10){
			    String minTxnValue = "";
			    String minimumTransactionValue = amount;
			    int index = minimumTransactionValue.indexOf(".");
			    int length1;         
			    if(index >= 0 && amount.trim().substring(0,index).length() <= 7){
			        length1 = minimumTransactionValue.substring(index+1).length();
			    }else if (index < 0 && (amount.trim().length() > 7)){
			    	return -1;            
			    }else if (index > 0 && (amount.trim().length() > 7)){
			    	return -1;
			    }else{
			    	length1 = -1;
			    }
			    int length = minimumTransactionValue.length();
			    if(length1 == 0)
			    	length1 =-1;
	
			    if(length1 < 2){
			    	StringBuffer temp = new StringBuffer(minimumTransactionValue);
			    if(index < 0 && length <= 7){                   
			    	temp = temp.append("00");
			    }else if(index < 0 && length > 7){
			        temp = temp.append("");
			    }else{
			        for(int i = 0 ; i < ((length - index)-length1) ;i++){
			            temp = temp.append("0");
			        }
			    }
			    minimumTransactionValue = temp.toString();
			    if(index <0)
			    	minTxnValue = minimumTransactionValue;
			    else
			    	minTxnValue = minimumTransactionValue.substring(0,index)+minimumTransactionValue.substring(index+1,minimumTransactionValue.length());
	
			    }else if(length1 == 2){
			        minTxnValue = minimumTransactionValue.substring(0,index)+minimumTransactionValue.substring(index+1,minimumTransactionValue.length());
			    }else{
			        minTxnValue = minimumTransactionValue.substring(0,index)+minimumTransactionValue.substring(index+1,index+3);
			    }               
	
			    int value = Integer.parseInt(minTxnValue);
			    if(value < 0){
			        value = -1;
			    }
			    return value;
			} else{
			    return -1;
			}
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
