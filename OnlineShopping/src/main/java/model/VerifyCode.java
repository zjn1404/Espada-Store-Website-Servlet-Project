package model;

import java.time.LocalDateTime;

public class VerifyCode {
	
	String customerId;
	String verifyCode;
	LocalDateTime expiryDate;
	boolean isVerified;
	
	public VerifyCode() {}
	
	public VerifyCode(String customerId, String verifyCode, LocalDateTime expiryDate, boolean isVerified) {
		this.customerId = customerId;
		this.verifyCode = verifyCode;
		this.expiryDate = expiryDate;
		this.isVerified = isVerified;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	
}
