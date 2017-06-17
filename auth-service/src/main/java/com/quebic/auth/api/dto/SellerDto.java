package com.quebic.auth.api.dto;

import com.quebic.auth.api.model.User;

public class SellerDto extends User{

	private Boolean isExporter;
	
	private Boolean isDealer;
	
	private Boolean isAuctionAgent;
	
	private String bizType;
	
	private Boolean isSupplier;
	
	private Boolean isDistributor;
	
	private Boolean isDealsCar;
	
	private Boolean isDealsMachinery;
	
	private Boolean isDealsSpareParts;
	
	private String bizName;
	
	private String bizEstablishedOn;
	
	private String operationalCountry;
	
	private String currencyId;
	
	private String bizRegCertificatePath;
	
	private String bizPoliceCertificatePath;
	
	private String bizRegCardPath;
	
	private String letterOfCardAccepted;
	
	private String bankTransferAccepted;
	
	private String aboutCompany;
	
	private String notifyLogins;
	
	public Boolean getIsExporter() {
		return isExporter;
	}

	public void setIsExporter(Boolean isExporter) {
		this.isExporter = isExporter;
	}
	
	public Boolean getIsDealer() {
		return isDealer;
	}

	public void setIsDealer(Boolean isDealer) {
		this.isDealer = isDealer;
	}

	public Boolean getIsAuctionAgent() {
		return isAuctionAgent;
	}

	public void setIsAuctionAgent(Boolean isAuctionAgent) {
		this.isAuctionAgent = isAuctionAgent;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public Boolean getIsSupplier() {
		return isSupplier;
	}

	public void setIsSupplier(Boolean isSupplier) {
		this.isSupplier = isSupplier;
	}

	public Boolean getIsDistributor() {
		return isDistributor;
	}

	public void setIsDistributor(Boolean isDistributor) {
		this.isDistributor = isDistributor;
	}

	public Boolean getIsDealsCar() {
		return isDealsCar;
	}

	public void setIsDealsCar(Boolean isDealsCar) {
		this.isDealsCar = isDealsCar;
	}

	public Boolean getIsDealsMachinery() {
		return isDealsMachinery;
	}

	public void setIsDealsMachinery(Boolean isDealsMachinery) {
		this.isDealsMachinery = isDealsMachinery;
	}

	public Boolean getIsDealsSpareParts() {
		return isDealsSpareParts;
	}

	public void setIsDealsSpareParts(Boolean isDealsSpareParts) {
		this.isDealsSpareParts = isDealsSpareParts;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getBizEstablishedOn() {
		return bizEstablishedOn;
	}

	public void setBizEstablishedOn(String bizEstablishedOn) {
		this.bizEstablishedOn = bizEstablishedOn;
	}

	public String getOperationalCountry() {
		return operationalCountry;
	}

	public void setOperationalCountry(String operationalCountry) {
		this.operationalCountry = operationalCountry;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getBizRegCertificatePath() {
		return bizRegCertificatePath;
	}

	public void setBizRegCertificatePath(String bizRegCertificatePath) {
		this.bizRegCertificatePath = bizRegCertificatePath;
	}

	public String getBizPoliceCertificatePath() {
		return bizPoliceCertificatePath;
	}

	public void setBizPoliceCertificatePath(String bizPoliceCertificatePath) {
		this.bizPoliceCertificatePath = bizPoliceCertificatePath;
	}

	public String getBizRegCardPath() {
		return bizRegCardPath;
	}

	public void setBizRegCardPath(String bizRegCardPath) {
		this.bizRegCardPath = bizRegCardPath;
	}

	public String getLetterOfCardAccepted() {
		return letterOfCardAccepted;
	}

	public void setLetterOfCardAccepted(String letterOfCardAccepted) {
		this.letterOfCardAccepted = letterOfCardAccepted;
	}

	public String getBankTransferAccepted() {
		return bankTransferAccepted;
	}

	public void setBankTransferAccepted(String bankTransferAccepted) {
		this.bankTransferAccepted = bankTransferAccepted;
	}

	public String getAboutCompany() {
		return aboutCompany;
	}

	public void setAboutCompany(String aboutCompany) {
		this.aboutCompany = aboutCompany;
	}

	public String getNotifyLogins() {
		return notifyLogins;
	}

	public void setNotifyLogins(String notifyLogins) {
		this.notifyLogins = notifyLogins;
	}

}
