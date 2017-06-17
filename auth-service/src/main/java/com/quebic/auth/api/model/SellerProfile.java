package com.quebic.auth.api.model;

import java.util.ArrayList;
import java.util.List;

public class SellerProfile {

	private String storeId;
	
	//temp fields
	private List<String> businessClariifications = new ArrayList<>();
	private List<String> deals = new ArrayList<>();
	
	private String legalBusinessName;
	private String businessAddress;
	private String physicalAddress;
	private String city;
	private String state;
	private String zipCode;
	private String businessLocatedCountry;
	private String businessPhone;
	private String businessWebsite;

	private String contactFirstName;
	private String contactLastName;
	private String contactBusinessAddress;
	private String contactPhysicalAddress;
	private String contactCity;
	private String contactState;
	private String contactZipCode;
	private String contactPhone;
	private String contactEmail;
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public List<String> getBusinessClariifications() {
		return businessClariifications;
	}
	public void setBusinessClariifications(List<String> businessClariifications) {
		this.businessClariifications = businessClariifications;
	}
	public List<String> getDeals() {
		return deals;
	}
	public void setDeals(List<String> deals) {
		this.deals = deals;
	}
	public String getLegalBusinessName() {
		return legalBusinessName;
	}
	public void setLegalBusinessName(String legalBusinessName) {
		this.legalBusinessName = legalBusinessName;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getBusinessLocatedCountry() {
		return businessLocatedCountry;
	}
	public void setBusinessLocatedCountry(String businessLocatedCountry) {
		this.businessLocatedCountry = businessLocatedCountry;
	}
	public String getBusinessPhone() {
		return businessPhone;
	}
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}
	public String getBusinessWebsite() {
		return businessWebsite;
	}
	public void setBusinessWebsite(String businessWebsite) {
		this.businessWebsite = businessWebsite;
	}
	public String getContactFirstName() {
		return contactFirstName;
	}
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	public String getContactLastName() {
		return contactLastName;
	}
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	public String getContactBusinessAddress() {
		return contactBusinessAddress;
	}
	public void setContactBusinessAddress(String contactBusinessAddress) {
		this.contactBusinessAddress = contactBusinessAddress;
	}
	public String getContactPhysicalAddress() {
		return contactPhysicalAddress;
	}
	public void setContactPhysicalAddress(String contactPhysicalAddress) {
		this.contactPhysicalAddress = contactPhysicalAddress;
	}
	public String getContactCity() {
		return contactCity;
	}
	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}
	public String getContactState() {
		return contactState;
	}
	public void setContactState(String contactState) {
		this.contactState = contactState;
	}
	public String getContactZipCode() {
		return contactZipCode;
	}
	public void setContactZipCode(String contactZipCode) {
		this.contactZipCode = contactZipCode;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	
	
}
