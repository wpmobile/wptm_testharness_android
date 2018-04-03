package com.worldpay.test_harness.utils;

/**
 * <p>Title: yespayv2</p>
 * <p>Description: YESpay Version 2</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: YESpay International Limited</p>
 * @author Dhiraj Dutta
 * @version 1.0
 */


public interface YESEFTConst {	//extends STSConstants {

        // ISO 8583 Transaction Codes
        public static final int YESEFT_TRANSACTION_SALES = 0;
        public static final int YESEFT_TRANSACTION_CASH = 1;
        public static final int YESEFT_TRANSACTION_ADJUSTMENT = 2;
        public static final int YESEFT_TRANSACTION_CHEQUE_GUARENTEE = 3;
        public static final int YESEFT_TRANSACTION_CHEQUE_VERIFICATION = 4;
        public static final int YESEFT_TRANSACTION_EUROCHEQUE = 5;
        public static final int YESEFT_TRANSACTION_TRAVELLER_CHEQUE = 6;
        public static final int YESEFT_TRANSACTION_CASHBACK = 9;
        public static final int YESEFT_TRANSACTION_CASH_ADVANCE = 12;
        //public static final int YESEFT_TRANSACTION_VOID = 20;
        public static final int YESEFT_TRANSACTION_REFUND = 20;
        public static final int YESEFT_TRANSACTION_VOIDPRESALES = 29;
        public static final int YESEFT_TRANSACTION_COMPLETION = 30;
        public static final int YESEFT_TRANSACTION_INTERNET_AUTH = 31;
        public static final int YESEFT_TRANSACTION_INTERNET_REFUND = 32;
        public static final int YESEFT_TRANSACTION_INTERNET_COMPLETION = 33;
        public static final int YESEFT_TRANSACTION_CHECK_STATUS = 34;
        public static final int YESEFT_TRANSACTION_PRESALES = 40;
        public static final int YESEFT_TRANSACTION_CHECKCARD = 41;
        
		public static final int YESEFT_TRANSACTION_INTERNET_BEGIN = 38;//        

        // ISO 8583 POS Entry Modes
        public static final int POS_ENTRY_MODE_KEYED = 1;
        public static final int POS_ENTRY_MODE_STRIPE = 2;
        public static final int POS_ENTRY_MODE_CHIP = 5;
        public static final int POS_ENTRY_MODE_KEYED_CNP = 81;
        public static final int POS_ENTRY_MODE_ECOMM = 82;

        // Transaction Results
        //
        
        public static final int ICC_FALLBACK = 13;
        public static final int STRIPE_FALLBACK = 14;
        public static final int PROMPT_FOR_ICC = 15;
        public static final int CAPTURE_CARD = 16;
        public static final int WAITING_CONFIRMATION = 18;
	public static final int TRANSACTION_ABORTED = 19;
        public static final int PRE_SALES_COMPLETED = 20;
        public static final int PRE_SALES_REJECTED = 21;
        public static final int CARDNUMBER_MISMATCH = 22;
        public static final int EXPIRYDATE_MISMATCH = 23;
        public static final int INVALID_PROCESSING_CODE = 24;
        public static final int INVALID_TXN_TYPE = 25;
        public static final int INVALID_TXN_REFERENCE = 26;
	public static final int INVALID_MERCHANT = 27;
	public static final int INVALID_TERMINAL = 28;
	public static final int INVALID_MERCHANT_STATUS = 29;
	public static final int INVALID_CARD_NUMBER = 30;
	public static final int CARD_EXPIRED = 31;
	public static final int CARD_PREVALID = 32;
	public static final int INVALID_ISSUE_NUMBER = 33;
	public static final int INVALID_CARD_EXPIRY_DATE = 34;
	public static final int INVALID_START_DATE = 35;
	public static final int CARD_REJECTED = 36;
	public static final int TRANSACTION_BARRED = 37;
	public static final int CASHBACK_NOT_ALLOWED = 38;
	public static final int REFUND_AMOUNT_EXCEEDS_ORIGINAL = 39;
	public static final int CURRENCY_NOT_SUPPORTED = 40;
	public static final int CURRENCY_NOT_MENTIONED = 41;
	public static final int STATUS_BUSY = 42;
	public static final int STATUS_OK = 43;
	public static final int INVALID_AUTH = 44;
	public static final int TRANSACTION_NOT_AUTHORISED = 45;
	
        public static final int CVV_NOT_PRESENT = 46;
        public static final int AUTHORISATION_IN_PROGRESS = 47;
        public static final int AUTHORISED_ALREADY = 48;
        public static final int AVS_DETAILS_NOT_PRESENT = 49;//          
/*        public static final int CARDHOLDER_NAME_NOT_PRESENT = 50;//                    
        public static final int EMAIL_ADDRESS_NOT_PRESENT = 51;//                              
*/		public static final int SUCCESS_URL_NOT_PRESENT = 50;//                    
	    public static final int FAILURE_URL_NOT_PRESENT = 51;//                              


        // Error Code
        public static final int INVALID_KEYED_DETAILS = 34001;
        public static final int INVALID_EXPIRY_DATE_FORMAT = 34002;
        public static final int NO_START_SENTINEL = 34003;
        public static final int INVALID_TRACK2_DATA = 34004;
        public static final int INVALID_CARD_DETAILS = 34005;
        public static final int CARD_DATA_SET_ERROR = 34006;
        public static final int CARD_NOT_ACCEPTED = 34007;
        public static final int PWCB_NOT_ALLOWED = 34008;
        public static final int ATM_USE_ONLY = 34009;
        public static final int CASH_ADVANCE_ONLY = 34010;
        public static final int INVALID_ISSUE_NO = 34011;
        public static final int INVALID_EXPIRY_DATE = 34012;
        public static final int CASH_BACK_AMOUNT_EXCEEDS = 34013;
        public static final int EXPIRED_CARD = 34014;
        public static final int KEYED_TRANSACTION_BARRED = 34015;
        public static final int INVALID_PAN_LENGTH = 34016;
        public static final int LUHN_CHECK_FAILED = 34017;
        public static final int CARD_DATA_PROC_ERROR = 34018;
        public static final int NOT_FALLBACK_TRANSACTION = 34019;

/**
        // Card Holder Verification Methods
        public static final int NO_CVM = 8;
        public static final int CVM_SIGNATURE = 1;
        public static final int CVM_PIN = 2;
        public static final int CVM_ALTERNATE = 3;
        public static final int CARD_HOLDER_NOT_PRESENT = 7;
**/
        // Maximum Days in a month
        public static final int[] LAST_DAY_OF_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // APACS50 POS Entry Modes Digit 1
        public static final int AP50_POSEM_SWIPE = 1;
        public static final int AP50_POSEM_KEYED = 2;
        public static final int AP50_POSEM_ICC = 3;
        public static final int AP50_POSEM_FAIL_ICC = 8;

        // APACS50 POS Entry Modes Digit 2 (CVM Methods)
        public static final int CVM_CP_SIGNATURE = 1;
        public static final int CVM_CP_PIN = 2;
        public static final int CVM_CP_ALTERNATE = 3;
        public static final int NO_CVM_UPT = 4;
        public static final int CVM_UPT_PIN = 5;
        public static final int CVM_CNP = 7;
        public static final int NO_CVM = 8;
        public static final int UNKNOWN_CVM = 9;
        public static final int CVM_PIN_AND_SIGNATURE = 10;

        // CVM Result Codes
        public static final byte CMV_UNKNOWN = 0x00;
        public static final byte CVM_FAILED = 0x01;
        public static final byte CVM_SUCCESSFUL = 0x02;

        // CVM Codes
        public static final byte CVM_PLAINTEXT_PIN_BY_ICC = 0x01;
        public static final byte CVM_PLAINTEXT_PIN_BY_ICC_1 = 0x41;
        public static final byte CVM_ENCIPHERED_PIN_ONLINE = 0x02;
        public static final byte CVM_ENCIPHERED_PIN_ONLINE_1 = 0x42;
        public static final byte CVM_PLAINTEXT_PIN_BY_ICC_AND_SIG = 0x03;
        public static final byte CVM_PLAINTEXT_PIN_BY_ICC_AND_SIG_1 = 0x43;
        public static final byte CVM_ENCIPHERED_PIN_BY_ICC = 0x04;
        public static final byte CVM_ENCIPHERED_PIN_BY_ICC_1 = 0x44;
        public static final byte CVM_ENCIPHERED_PIN_BY_ICC_AND_SIG = 0x05;
        public static final byte CVM_ENCIPHERED_PIN_BY_ICC_AND_SIG_1 = 0x45;
        public static final byte CVM_SIGNATURE = 0x1E;
        public static final byte CVM_SIGNATURE_1 = 0x5E;
        public static final byte NO_CVM_PERFORMED = 0x1F;
        public static final byte NO_CVM_PERFORMED_1 = 0x5F;
        public static final byte NO_CVM_PERFORMED_2 = 0x3F;
        public static final byte NO_CVM_WAIVER = 0x00 ;

        // CVM Condition Codes
        public static final byte CVM_PIN_VERIFICATION = 0X03;

        // Tag for last read failure
        public static final String LAST_READ_FAILURE = "LASTREADFAILURE";

        // Reconciliation Status
        public static final int RECONCILIATION_SUCCESSFUL = 0;
        public static final int RECON_CREDIT_TOTALS_FAIL = 1;
        public static final int RECON_DEBIT_TOTALS_FAIL = 2;
        public static final int RECON_TRANS_NO_FAIL = 3;

        // Display Message
        public static final String SIGN_PROMPT = "51";

	// EMV Terminal Types
	public static final int TERMINAL_TYPE_ATTENDED = 22 ;
	public static final int TERMINAL_TYPE_UPT = 25 ;


	// TERMINALDATA Keys
	public static final String AVS_DETAILS = "AVSDetails" ;
	public static final String RECEIPT_NUMBER = "ReceiptNumber" ;
	public static final String CONFIG_DIR = "ConfigDir" ;
	public static final String TERMINAL_ID = "YesPayTerminalID" ;
	public static final String MERCHANT_ID = "YesPayMerchantID" ;

	// ICCDATA Keys
	public static final String ADDITIONAL_RESPONSE = "AdditionalResponse" ;
	public static final String AVS_DETAILS_FLAG = "AVSDetailsFlag" ;
	public static final String FACTORY_ERROR_CODE = "FactoryErrorCode" ;

	/* Added by Tasneem */
	public static final String FUEL_DETAILS_FLAG = "FuelDetailsFlag" ;
	public static final String FUEL_DETAILS = "FuelDetails" ;

	/* fuelCardType = 1-Products Restricted, 2-Products Allowable, 3-Overdrive/Dialcard	*/
	public static final int PRODUCTS_RESTRICTED = 1;
	public static final int PRODUCTS_ALLOWABLE = 2;
	public static final int OVERDRIVE_DIAL = 3;

	// APACS 50 EFT Product codes
	public static final int AP50_PROD_OIL = 0;
	public static final int AP50_PROD_UNLEADED = 1;
	public static final int AP50_PROD_4STAR = 4;
	public static final int AP50_PROD_DERV = 5;
	public static final int AP50_PROD_LPG = 6;
	public static final int AP50_PROD_REPAIRS = 7;
	public static final int AP50_PROD_SERVICE = 8;
	public static final int AP50_PROD_PARTS = 9;
	public static final int AP50_PROD_CASH = 27;
	public static final int AP50_PROD_ACCESSORIES = 40;
	public static final int AP50_PROD_TYRES = 41;
	public static final int AP50_PROD_BATTERIES = 42;
	public static final int AP50_PROD_EXHAUSTS = 43;
	public static final int AP50_PROD_ANTIFREEZE = 44;
	public static final int AP50_PROD_CARWASH = 45;
	public static final int AP50_PROD_CARHIRE = 46;
	public static final int AP50_PROD_JETWASH = 47;
	public static final int AP50_PROD_VACUUM = 48;
	public static final int AP50_PROD_LPGMEGASALE = 98;
	public static final int AP50_PROD_MISCFUEL = 99;

	//Card type
	public static final int NON_FUEL_CARD_TYPE = 0;
	public static final int FUEL_CARD_TYPE = 1;
	public static final int NOT_SUPPORTED_CARD_TYPE = 2;

	//Transaction progress status
	public static final int PROGRESS_STATUS_CONNECTING = 1 ;
	public static final int PROGRESS_STATUS_AUTHORISING = 2 ;
	public static final int PROGRESS_STATUS_FINALISING = 3 ;
	
	//Internet Transaction
	public static final String AUTH_ID = "AuthId";
	public static final String PAYMENT_GATEWAY_URL = "PaymentGatewayURL";
	public static final String RETURN_SUCCESS_URL = "ReturnSuccessURL";
	public static final String RETURN_FAILURE_URL = "ReturnFailureURL";
	
	//For FDMS MAC 
		public static final String ACCOUNT_NUMBER = "0000000000000000000";
		public static final String PROCESSING_CODE = "000000";
		public static final String TRANSACTION_AMOUNT = "000000000000";
		public static final String TERMINAL_RRN = "000000000000";
	//	public static final String FDMS_SEPERATOR = "20";
		public static final String FDMS_SEPERATOR = " ";
	//For FDMS AUTH MAC
		public static final String SALE_SAVINGS_PROCESSING_CODE = "001000";
		public static final String SALE_CHECKING_PROCESSING_CODE = "002000";
		public static final String REFUND_SAVINGS_PROCESSING_CODE = "200010";
		public static final String REFUND_CHECKING_PROCESSING_CODE = "200020";
		
	// For PatmentTech MAC
		public static final String RID_INTERAC = "A000000277" ;
		public static final String AID_MASTERCARD = "A0000000043060";
		public static final String LABEL_VISA = "VISA DEBIT";
		public static final String ACCOUNT_TYPE = "*D";
		public static final String TRACE_NUMBER_DEFAULT  = "00000000";
		 //Added for XPI
        public static final int YESEFT_TRANSACTION_VOID = 10;
        public static final int YESEFT_TRANSACTION_CLOSE_EASYVTERMINAL = 11;
        public static final String CANADA_APPROVED_AMOUNT = "CandaApprovedAmount";
        // Transaction result for Void Sale and Void refund trnsaction, Vishal G,6/21/2010 11:49AM     
        public static final int YESEFT_TRANSACTION_VOID_SALE_APPROVED = 29;
        public static final int YESEFT_TRANSACTION_VOID_REFUND_APPROVED = 30;  
        
        public static final int TRANS_TYPE_CLOSE_EVT_WINDOW=99; 
}
