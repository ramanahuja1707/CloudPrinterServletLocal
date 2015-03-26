package com.cloudprinter.api.result;

import javax.persistence.Entity;

@Entity
public class CheckUserLoginResult {

	String loginStatus;
	Errors errors;

	public CheckUserLoginResult() {
		loginStatus = "";
		errors = new Errors();
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors error) {
		this.errors = error;
	}

}
