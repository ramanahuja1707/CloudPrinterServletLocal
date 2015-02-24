package com.cloudprinter.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.cloudprinter.exceptions.RegistrationException;

@Entity
public class UserInfo {
	@Id
	private String userId;// userName+"-"+userNumber +"@"+loginId
	private String userName;
	String loginId; // foreign key
	String age;
	String gender;
	String userDesignation;
	String userWorkLocation;
	String emailId;
	String alternateEmail;
	String contactNumber;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	// userName of 8 characters minimum
	public void setUserName(String userName) throws RegistrationException {
		if (userName.length() >= 8)
		{
			
			this.userName = userName;
		}
		else
		{
			
			throw new RegistrationException("UserName Is Invalid");
		}
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) throws RegistrationException {
		if (loginId.length() >= 8 && loginId != null)
			this.loginId = loginId;
		else
			throw new RegistrationException("LoginId Is Invalid");
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) throws RegistrationException {
		if ((Integer.parseInt(age) > 22) && (Integer.parseInt(age) < 60)
				&& age != "N/A")
			this.age = age;
		else
			throw new RegistrationException("Age Is Invalid");
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) throws RegistrationException {
		if (gender.length() > 0 && gender != null)
			this.gender = gender;
		else
			throw new RegistrationException("Invalid Gender");
	}

	public String getUserDesignation() {
		return userDesignation;
	}

	public void setUserDesignation(String userDesignation)
			throws RegistrationException {
		if (userDesignation.length() > 0 && userDesignation != null)

			this.userDesignation = userDesignation;
		else
			throw new RegistrationException("User Designaton is Invalid");
	}

	public String getUserWorkLocation() {
		return userWorkLocation;
	}

	public void setUserWorkLocation(String userWorkLocation)
			throws RegistrationException {
		if (userWorkLocation.length() > 0 && userWorkLocation != null)
			this.userWorkLocation = userWorkLocation;
		else
			throw new RegistrationException("Work Location Invalid");
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) throws RegistrationException {
		if (emailId.length() > 0
				&& emailId
						.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*[\\.[A-Za-z]{2,}]$")
				&& emailId != null)
			this.emailId = emailId;
		else
			throw new RegistrationException("Invalid EmailId");

	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail)
			throws RegistrationException {
		if (alternateEmail.length() > 0
				&& alternateEmail != null
				&& alternateEmail
						.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*[\\.[A-Za-z]{2,}]$"))
			this.alternateEmail = alternateEmail;
		else
			throw new RegistrationException("Invalid Alternate EmailId");

	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber)
			throws RegistrationException {
		if (contactNumber.length() > 0 && contactNumber.length() <= 10
				&& contactNumber != null)
			this.contactNumber = contactNumber;
		else
			throw new RegistrationException("Invalid Mobile Number");
	}

}
