
package com.worldpay.test_harness.utils ;

import java.util.Date;

public class TransactionResponse {

	public static int STATUS_OK = 0 ;
	public static int STATUS_ABORTED = 1 ;
	public static int STATUS_BUSY = 2 ;
	public static int STATUS_ERROR = 3 ;
	public static int REASON_CANCEL_COMPLETE = 4 ;
	public static int REASON_CHECKSTATUS = 6 ;
	int status ;
	int posEntryMode ;
	int txnType ;
	int txnResult ;
	String txnReference ;
	String authCode ;
	String pan ;
	String applLabel ;
	Date applEffectiveDate ;
	Date txnDate ;
	String cardHolderName ;
	String cardHolderNameExt ;
	String mid ;
	String tid ;
	int cardVerificationMethod ;
	Date startDate ;
	int totalDebitNumberOfTransaction ;
	int totalCreditNumberOfTransaction ;
	int totalDebits ;
	int totalCredits ;
	int reconStatus ;
	int txnSeqNo ;
	String merchantAddress ;
	String merchantName ;
	Date serverDate ;
	int batchNumber ;
	String referralTelephone1 ;
	String referralTelephone2 ;
	String pgtr ;
	String applId ;
	int applPanSeqNumber ;
	String tsi ;
	String tvr ;
	String retaintionReminder ;
	String declaration ;
	String additionalResponseData ;
	String receiptNumber ;
	Date cardExpiryDate ;
	int totalAmount ;
	int cashAmount ;
	int gratuityAmount ;
	String track2Data ;
	String targetReference ;
	boolean continueTxnFlag ;
	String paymentGatewayURL;
	String authorisationId;
	String acsUrl;
	String paReq;
	String appVer;
	
	
	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	/*Added byte Sumit on 13012012 for MobileEVT*/
	String cardIssuerCode;
	String sha1ForPAN;
	public String getCardIssuerCode() {
		return cardIssuerCode;
	}

	public void setCardIssuerCode(String cardIssuerCode) {
		this.cardIssuerCode = cardIssuerCode;
	}

	public String getSha1ForPAN() {
		return sha1ForPAN;
	}

	public void setSha1ForPAN(String sha1ForPAN) {
		this.sha1ForPAN = sha1ForPAN;
	}

	// Add By Vishal Gupta For Cancel Last Transaction 6/9/2008 5:23PM
	int cardType = -1;
	
	public TransactionResponse () { }

	public TransactionResponse (int status) {
		this.status = status ;
	}
	// Add By Vishal Gupta For Cancel Last Transaction 6/9/2008 5:23PM
	public int getCardType()
	{
	    return cardType;
	}
	// Add By Vishal Gupta For Cancel Last Transaction 6/9/2008 5:23PM
	
	public void setCardType(int card_type)
	{
	    this.cardType = card_type;
	}
	
	/**
	 * Get status.
	 *
	 * @return status as int.
	 */
	public int getStatus()
	{
	    return status;
	}
	
	/**
	 * Set status.
	 *
	 * @param status the value to set.
	 */
	public void setStatus(int status)
	{
	    this.status = status;
	}
	
	/**
	 * Get posEntryMode.
	 *
	 * @return posEntryMode as int.
	 */
	public int getPosEntryMode()
	{
	    return posEntryMode;
	}
	
	/**
	 * Set posEntryMode.
	 *
	 * @param posEntryMode the value to set.
	 */
	public void setPosEntryMode(int posEntryMode)
	{
	    this.posEntryMode = posEntryMode;
	}
	
	/**
	 * Get txnType.
	 *
	 * @return txnType as int.
	 */
	public int getTxnType()
	{
	    return txnType;
	}
	
	/**
	 * Set txnType.
	 *
	 * @param txnType the value to set.
	 */
	public void setTxnType(int txnType)
	{
	    this.txnType = txnType;
	}
	
	/**
	 * Get txnResult.
	 *
	 * @return txnResult as int.
	 */
	public int getTxnResult()
	{
	    return txnResult;
	}
	
	/**
	 * Set txnResult.
	 *
	 * @param txnResult the value to set.
	 */
	public void setTxnResult(int txnResult)
	{
	    this.txnResult = txnResult;
	}
	
	/**
	 * Get authCode.
	 *
	 * @return authCode as String.
	 */
	public String getAuthCode()
	{
	    return authCode;
	}
	
	/**
	 * Set authCode.
	 *
	 * @param authCode the value to set.
	 */
	public void setAuthCode(String authCode)
	{
	    this.authCode = authCode;
	}
	
	/**
	 * Get pan.
	 *
	 * @return pan as String.
	 */
	public String getPan()
	{
	    return pan;
	}
	
	/**
	 * Set pan.
	 *
	 * @param pan the value to set.
	 */
	public void setPan(String pan)
	{
	    this.pan = pan;
	}
	
	/**
	 * Get applLabel.
	 *
	 * @return applLabel as String.
	 */
	public String getApplLabel()
	{
	    return applLabel;
	}
	
	/**
	 * Set applLabel.
	 *
	 * @param applLabel the value to set.
	 */
	public void setApplLabel(String applLabel)
	{
	    this.applLabel = applLabel;
	}
	
	/**
	 * Get applEffectiveDate.
	 *
	 * @return applEffectiveDate as Date.
	 */
	public Date getApplEffectiveDate()
	{
	    return applEffectiveDate;
	}
	
	/**
	 * Set applEffectiveDate.
	 *
	 * @param applEffectiveDate the value to set.
	 */
	public void setApplEffectiveDate(Date applEffectiveDate)
	{
	    this.applEffectiveDate = applEffectiveDate;
	}
	
	/**
	 * Get txnDate.
	 *
	 * @return txnDate as Date.
	 */
	public Date getTxnDate()
	{
	    return txnDate;
	}
	
	/**
	 * Set txnDate.
	 *
	 * @param txnDate the value to set.
	 */
	public void setTxnDate(Date txnDate)
	{
	    this.txnDate = txnDate;
	}
	
	/**
	 * Get cardHolderName.
	 *
	 * @return cardHolderName as String.
	 */
	public String getCardHolderName()
	{
	    return cardHolderName;
	}
	
	/**
	 * Set cardHolderName.
	 *
	 * @param cardHolderName the value to set.
	 */
	public void setCardHolderName(String cardHolderName)
	{
	    this.cardHolderName = cardHolderName;
	}
	
	/**
	 * Get cardHolderNameExt.
	 *
	 * @return cardHolderNameExt as String.
	 */
	public String getCardHolderNameExt()
	{
	    return cardHolderNameExt;
	}
	
	/**
	 * Set cardHolderNameExt.
	 *
	 * @param cardHolderNameExt the value to set.
	 */
	public void setCardHolderNameExt(String cardHolderNameExt)
	{
	    this.cardHolderNameExt = cardHolderNameExt;
	}
	
	/**
	 * Get mid.
	 *
	 * @return mid as String.
	 */
	public String getMid()
	{
	    return mid;
	}
	
	/**
	 * Set mid.
	 *
	 * @param mid the value to set.
	 */
	public void setMid(String mid)
	{
	    this.mid = mid;
	}
	
	/**
	 * Get tid.
	 *
	 * @return tid as String.
	 */
	public String getTid()
	{
	    return tid;
	}
	
	/**
	 * Set tid.
	 *
	 * @param tid the value to set.
	 */
	public void setTid(String tid)
	{
	    this.tid = tid;
	}
	
	/**
	 * Get cardVerificationMethod.
	 *
	 * @return cardVerificationMethod as int.
	 */
	public int getCardVerificationMethod()
	{
	    return cardVerificationMethod;
	}
	
	/**
	 * Set cardVerificationMethod.
	 *
	 * @param cardVerificationMethod the value to set.
	 */
	public void setCardVerificationMethod(int cardVerificationMethod)
	{
	    this.cardVerificationMethod = cardVerificationMethod;
	}
	
	/**
	 * Get startDate.
	 *
	 * @return startDate as Date.
	 */
	public Date getStartDate()
	{
	    return startDate;
	}
	
	/**
	 * Set startDate.
	 *
	 * @param startDate the value to set.
	 */
	public void setStartDate(Date startDate)
	{
	    this.startDate = startDate;
	}
	
	/**
	 * Get totalDebitNumberOfTransaction.
	 *
	 * @return totalDebitNumberOfTransaction as int.
	 */
	public int getTotalDebitNumberOfTransaction()
	{
	    return totalDebitNumberOfTransaction;
	}
	
	/**
	 * Set totalDebitNumberOfTransaction.
	 *
	 * @param totalDebitNumberOfTransaction the value to set.
	 */
	public void setTotalDebitNumberOfTransaction(int totalDebitNumberOfTransaction)
	{
	    this.totalDebitNumberOfTransaction = totalDebitNumberOfTransaction;
	}
	
	/**
	 * Get totalCreditNumberOfTransaction.
	 *
	 * @return totalCreditNumberOfTransaction as int.
	 */
	public int getTotalCreditNumberOfTransaction()
	{
	    return totalCreditNumberOfTransaction;
	}
	
	/**
	 * Set totalCreditNumberOfTransaction.
	 *
	 * @param totalCreditNumberOfTransaction the value to set.
	 */
	public void setTotalCreditNumberOfTransaction(int totalCreditNumberOfTransaction)
	{
	    this.totalCreditNumberOfTransaction = totalCreditNumberOfTransaction;
	}
	
	/**
	 * Get totalDebits.
	 *
	 * @return totalDebits as int.
	 */
	public int getTotalDebits()
	{
	    return totalDebits;
	}
	
	/**
	 * Set totalDebits.
	 *
	 * @param totalDebits the value to set.
	 */
	public void setTotalDebits(int totalDebits)
	{
	    this.totalDebits = totalDebits;
	}
	
	/**
	 * Get totalCredits.
	 *
	 * @return totalCredits as int.
	 */
	public int getTotalCredits()
	{
	    return totalCredits;
	}
	
	/**
	 * Set totalCredits.
	 *
	 * @param totalCredits the value to set.
	 */
	public void setTotalCredits(int totalCredits)
	{
	    this.totalCredits = totalCredits;
	}
	
	/**
	 * Get reconStatus.
	 *
	 * @return reconStatus as int.
	 */
	public int getReconStatus()
	{
	    return reconStatus;
	}
	
	/**
	 * Set reconStatus.
	 *
	 * @param reconStatus the value to set.
	 */
	public void setReconStatus(int reconStatus)
	{
	    this.reconStatus = reconStatus;
	}
	
	/**
	 * Get txnSeqNo.
	 *
	 * @return txnSeqNo as int.
	 */
	public int getTxnSeqNo()
	{
	    return txnSeqNo;
	}
	
	/**
	 * Set txnSeqNo.
	 *
	 * @param txnSeqNo the value to set.
	 */
	public void setTxnSeqNo(int txnSeqNo)
	{
	    this.txnSeqNo = txnSeqNo;
	}
	
	/**
	 * Get merchantAddress.
	 *
	 * @return merchantAddress as String.
	 */
	public String getMerchantAddress()
	{
	    return merchantAddress;
	}
	
	/**
	 * Set merchantAddress.
	 *
	 * @param merchantAddress the value to set.
	 */
	public void setMerchantAddress(String merchantAddress)
	{
	    this.merchantAddress = merchantAddress;
	}
	
	/**
	 * Get merchantName.
	 *
	 * @return merchantName as String.
	 */
	public String getMerchantName()
	{
	    return merchantName;
	}
	
	/**
	 * Set merchantName.
	 *
	 * @param merchantName the value to set.
	 */
	public void setMerchantName(String merchantName)
	{
	    this.merchantName = merchantName;
	}
	
	/**
	 * Get serverDate.
	 *
	 * @return serverDate as Date.
	 */
	public Date getServerDate()
	{
	    return serverDate;
	}
	
	/**
	 * Set serverDate.
	 *
	 * @param serverDate the value to set.
	 */
	public void setServerDate(Date serverDate)
	{
	    this.serverDate = serverDate;
	}
	
	/**
	 * Get batchNumber.
	 *
	 * @return batchNumber as int.
	 */
	public int getBatchNumber()
	{
	    return batchNumber;
	}
	
	/**
	 * Set batchNumber.
	 *
	 * @param batchNumber the value to set.
	 */
	public void setBatchNumber(int batchNumber)
	{
	    this.batchNumber = batchNumber;
	}
	
	/**
	 * Get referralTelephone1.
	 *
	 * @return referralTelephone1 as String.
	 */
	public String getReferralTelephone1()
	{
	    return referralTelephone1;
	}
	
	/**
	 * Set referralTelephone1.
	 *
	 * @param referralTelephone1 the value to set.
	 */
	public void setReferralTelephone1(String referralTelephone1)
	{
	    this.referralTelephone1 = referralTelephone1;
	}
	
	/**
	 * Get referralTelephone2.
	 *
	 * @return referralTelephone2 as String.
	 */
	public String getReferralTelephone2()
	{
	    return referralTelephone2;
	}
	
	/**
	 * Set referralTelephone2.
	 *
	 * @param referralTelephone2 the value to set.
	 */
	public void setReferralTelephone2(String referralTelephone2)
	{
	    this.referralTelephone2 = referralTelephone2;
	}
	
	/**
	 * Get pgtr.
	 *
	 * @return pgtr as String.
	 */
	public String getPgtr()
	{
	    return pgtr;
	}
	
	/**
	 * Set pgtr.
	 *
	 * @param pgtr the value to set.
	 */
	public void setPgtr(String pgtr)
	{
	    this.pgtr = pgtr;
	}
	
	/**
	 * Get applId.
	 *
	 * @return applId as String.
	 */
	public String getApplId()
	{
	    return applId;
	}
	
	/**
	 * Set applId.
	 *
	 * @param applId the value to set.
	 */
	public void setApplId(String applId)
	{
	    this.applId = applId;
	}
	
	/**
	 * Get tsi.
	 *
	 * @return tsi as String.
	 */
	public String getTsi()
	{
	    return tsi;
	}
	
	/**
	 * Set tsi.
	 *
	 * @param tsi the value to set.
	 */
	public void setTsi(String tsi)
	{
	    this.tsi = tsi;
	}
	
	/**
	 * Get tvr.
	 *
	 * @return tvr as String.
	 */
	public String getTvr()
	{
	    return tvr;
	}
	
	/**
	 * Set tvr.
	 *
	 * @param tvr the value to set.
	 */
	public void setTvr(String tvr)
	{
	    this.tvr = tvr;
	}
	
	/**
	 * Get retaintionReminder.
	 *
	 * @return retaintionReminder as String.
	 */
	public String getRetaintionReminder()
	{
	    return retaintionReminder;
	}
	
	/**
	 * Set retaintionReminder.
	 *
	 * @param retaintionReminder the value to set.
	 */
	public void setRetaintionReminder(String retaintionReminder)
	{
	    this.retaintionReminder = retaintionReminder;
	}
	
	/**
	 * Get declaration.
	 *
	 * @return declaration as String.
	 */
	public String getDeclaration()
	{
	    return declaration;
	}
	
	/**
	 * Set declaration.
	 *
	 * @param declaration the value to set.
	 */
	public void setDeclaration(String declaration)
	{
	    this.declaration = declaration;
	}
	
	/**
	 * Get additionalResponseData.
	 *
	 * @return additionalResponseData as String.
	 */
	public String getAdditionalResponseData()
	{
	    return additionalResponseData;
	}
	
	/**
	 * Set additionalResponseData.
	 *
	 * @param additionalResponseData the value to set.
	 */
	public void setAdditionalResponseData(String additionalResponseData)
	{
	    this.additionalResponseData = additionalResponseData;
	}
	
	/**
	 * Get receiptNumber.
	 *
	 * @return receiptNumber as String.
	 */
	public String getReceiptNumber()
	{
	    return receiptNumber;
	}
	
	/**
	 * Set receiptNumber.
	 *
	 * @param receiptNumber the value to set.
	 */
	public void setReceiptNumber(String receiptNumber)
	{
	    this.receiptNumber = receiptNumber;
	}
	
	/**
	 * Get cardExpiryDate.
	 *
	 * @return cardExpiryDate as Date.
	 */
	public Date getCardExpiryDate()
	{
	    return cardExpiryDate;
	}
	
	/**
	 * Set cardExpiryDate.
	 *
	 * @param cardExpiryDate the value to set.
	 */
	public void setCardExpiryDate(Date cardExpiryDate)
	{
	    this.cardExpiryDate = cardExpiryDate;
	}
	
	/**
	 * Get totalAmount.
	 *
	 * @return totalAmount as int.
	 */
	public int getTotalAmount()
	{
	    return totalAmount;
	}
	
	/**
	 * Set totalAmount.
	 *
	 * @param totalAmount the value to set.
	 */
	public void setTotalAmount(int totalAmount)
	{
	    this.totalAmount = totalAmount;
	}
	
	/**
	 * Get cashAmount.
	 *
	 * @return cashAmount as int.
	 */
	public int getCashAmount()
	{
	    return cashAmount;
	}
	
	/**
	 * Set cashAmount.
	 *
	 * @param cashAmount the value to set.
	 */
	public void setCashAmount(int cashAmount)
	{
	    this.cashAmount = cashAmount;
	}
	
	/**
	 * Get gratuityAmount.
	 *
	 * @return gratuityAmount as int.
	 */
	public int getGratuityAmount()
	{
	    return gratuityAmount;
	}
	
	/**
	 * Set gratuityAmount.
	 *
	 * @param gratuityAmount the value to set.
	 */
	public void setGratuityAmount(int gratuityAmount)
	{
	    this.gratuityAmount = gratuityAmount;
	}
	
	/**
	 * Get applPanSeqNumber.
	 *
	 * @return applPanSeqNumber as int.
	 */
	public int getApplPanSeqNumber()
	{
	    return applPanSeqNumber;
	}
	
	/**
	 * Set applPanSeqNumber.
	 *
	 * @param applPanSeqNumber the value to set.
	 */
	public void setApplPanSeqNumber(int applPanSeqNumber)
	{
	    this.applPanSeqNumber = applPanSeqNumber;
	}
	
	/**
	 * Get track2Data.
	 *
	 * @return track2Data as String.
	 */
	public String getTrack2Data()
	{
	    return track2Data;
	}
	
	/**
	 * Set track2Data.
	 *
	 * @param track2Data the value to set.
	 */
	public void setTrack2Data(String track2Data)
	{
	    this.track2Data = track2Data;
	}
	
	/**
	 * Get txnReference.
	 *
	 * @return txnReference as String.
	 */
	public String getTxnReference()
	{
	    return txnReference;
	}
	
	/**
	 * Set txnReference.
	 *
	 * @param txnReference the value to set.
	 */
	public void setTxnReference(String txnReference)
	{
	    this.txnReference = txnReference;
	}
	
	/**
	 * Get targetReference.
	 *
	 * @return targetReference as String.
	 */
	public String getTargetReference()
	{
	    return targetReference;
	}
	
	/**
	 * Set targetReference.
	 *
	 * @param targetReference the value to set.
	 */
	public void setTargetReference(String targetReference)
	{
	    this.targetReference = targetReference;
	}
	
	/**
	 * Get continueTxnFlag.
	 *
	 * @return continueTxnFlag as boolean.
	 */
	public boolean getContinueTxnFlag()
	{
	    return continueTxnFlag;
	}
	
	/**
	 * Set continueTxnFlag.
	 *
	 * @param continueTxnFlag the value to set.
	 */
	public void setContinueTxnFlag(boolean continueTxnFlag)
	{
	    this.continueTxnFlag = continueTxnFlag;
	}
	
	/**
	 * Get paymentGatewayURL.
	 *
	 * @return paymentGatewayURL as String.
	 */
	public String getPaymentGatewayURL()
	{
	    return paymentGatewayURL;
	}
	
	/**
	 * Set paymentGatewayURL.
	 *
	 * @param paymentGatewayURL the value to set.
	 */
	public void setPaymentGatewayURL(String paymentGatewayURL)
	{
	    this.paymentGatewayURL = paymentGatewayURL;
	}
	
	/**
	 * Get authorisationId.
	 *
	 * @return authorisationId as String.
	 */
	public String getAuthorisationId()
	{
	    return authorisationId;
	}
	
	/**
	 * Set authorisationId.
	 *
	 * @param authorisationId the value to set.
	 */
	public void setAuthorisationId(String authorisationId)
	{
	    this.authorisationId = authorisationId;
	}
	
	/**
	 * Get acsUrl.
	 *
	 * @return acsUrl as String.
	 */
	public String getAcsUrl()
	{
	    return acsUrl;
	}
	
	/**
	 * Set acsUrl.
	 *
	 * @param acsUrl the value to set.
	 */
	public void setAcsUrl(String acsUrl)
	{
	    this.acsUrl = acsUrl;
	}

	/**
	 * Get paReq.
	 *
	 * @return paReq as String.
	 */
	public String getPaReq()
	{
	    return paReq;
	}
	
	/**
	 * Set paReq.
	 *
	 * @param paReq the value to set.
	 */
	public void setPaReq(String paReq)
	{
	    this.paReq = paReq;
	}

}
