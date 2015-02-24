package com.cloudprinter.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserLoginInfo {

	@Id
	String userId;// userName+"-"+userNumber +"@"+loginId
	String loginId;
	String emailId;
	String password;//"@"+loginId+"@"

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}
