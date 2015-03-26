package com.cloudprinter.api.result;

import java.util.ArrayList;

public class Errors {
	ArrayList<String> errors = new ArrayList<>();
	ArrayList<Integer> code = new ArrayList<>();

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}

	public ArrayList<Integer> getCode() {
		return code;
	}

	public void setCode(ArrayList<Integer> code) {
		this.code = code;
	}

}
