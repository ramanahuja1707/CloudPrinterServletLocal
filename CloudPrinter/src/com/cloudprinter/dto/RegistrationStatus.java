package com.cloudprinter.dto;

import java.util.ArrayList;

public class RegistrationStatus {
	String status;
	ArrayList<String> errors = new ArrayList<>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}

}
