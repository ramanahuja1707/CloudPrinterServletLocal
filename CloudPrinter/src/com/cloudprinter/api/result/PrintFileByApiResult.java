package com.cloudprinter.api.result;

import javax.persistence.Entity;

@Entity
public class PrintFileByApiResult {

	String printStatus;
	Errors errors;

	public String getPrintStatus() {
		return printStatus;
	}

	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

}
