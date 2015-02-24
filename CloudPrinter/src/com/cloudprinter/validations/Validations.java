package com.cloudprinter.validations;

import java.util.ArrayList;

import com.cloudprinter.dto.ValidationErrors;

public class Validations {

	public ValidationErrors validateLogin(String loginId, String emailId,
			String password) {
		ArrayList<String> errors = new ArrayList<>();
		ValidationErrors ve = new ValidationErrors();
		ve.setStatus("null");
		if (!(loginId.length() >= 8 && loginId != null)) {
			errors.add("Login Id Invalid !!");
			ve.setStatus("not_null");
		}
		if (!(emailId.length() > 0
				&& emailId
						.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*[\\.[A-Za-z]{2,}]$") && emailId != null)) {
			errors.add("Email Id Invalid !!");
			ve.setStatus("not_null");
		}
		if (password == null) {
			errors.add("Password Invalid !!");
			ve.setStatus("not_null");
		}
		ve.setErrors(errors);

		return ve;
	}
	
	public ValidationErrors validateUserRegistration()
	{
		return null;
		
	}
	
	
	
	
}
