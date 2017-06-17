package com.quebic.auth.api.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class EmailVerification {
	
	private static final int EXPIRATION = 60 * 24;

	private String token;
	
	private Date expiryDate;
	
	//true => if user verify from email
	private boolean isVerify;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isVerify() {
		return isVerify;
	}

	public void setVerify(boolean isVerify) {
		this.isVerify = isVerify;
	}
	
	public EmailVerification calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRATION);
        this.expiryDate = new Date(cal.getTime().getTime());
        return this;
    }
}
