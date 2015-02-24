package com.cloudprinter.validations;

import java.util.ArrayList;

import com.cloudprinter.dto.UserInfo;
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

	public ValidationErrors validateUserRegistration(String loginId,
			String emailId, String userName, String age, String gender,
			String userDesignation, String userWorkLocation, String contactNo,
			String alternateEmailId) {
		ArrayList<String> errors = new ArrayList<>();
		ValidationErrors ve = new ValidationErrors();
		ve.setStatus("null");
		if (!(userName.length() >= 8)) {
			errors.add("User Name Invalid !!");
			ve.setStatus("not_null");
		}
		if (!(loginId.length() >= 8 && loginId != null)) {
			errors.add("Login Id Invalid !!");
			ve.setStatus("not_null");
		}
		if (age == null || age.length() == 0) {
			errors.add("Age Invalid !!");
			ve.setStatus("not_null");

		}

		if (gender == null || gender.length() == 0) {
			errors.add("Gender Invalid !!");
			ve.setStatus("not_null");
		}
		if (userDesignation.length() == 0 || userDesignation == null) {
			errors.add("User Designation Invalid !!");
			ve.setStatus("not_null");
		}

		if (userWorkLocation.length() == 0 || userWorkLocation == null) {
			errors.add("User Work Location Invalid !!");
			ve.setStatus("not_null");
		}
		if (!(emailId.length() > 0
				&& emailId
						.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*[\\.[A-Za-z]{2,}]$") && emailId != null)) {
			errors.add("Email Id Invalid !!");
			ve.setStatus("not_null");
		}

		if (!(alternateEmailId.length() > 0 && alternateEmailId != null && alternateEmailId
				.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*[\\.[A-Za-z]{2,}]$"))) {
			errors.add("Alternate Email Id Invalid !!");
			ve.setStatus("not_null");
		}

		if (contactNo == null || contactNo.length() == 0) {
			errors.add("Contact Number Invalid !!");
			ve.setStatus("not_null");
		}
		ve.setErrors(errors);
		return ve;

	}
}
