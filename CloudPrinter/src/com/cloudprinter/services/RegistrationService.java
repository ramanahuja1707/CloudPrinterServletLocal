package com.cloudprinter.services;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.mail.MessagingException;

import com.cloudprinter.dto.RegistrationStatus;
import com.cloudprinter.dto.UserInfo;
import com.cloudprinter.dto.UserLoginInfo;
import com.cloudprinter.exceptions.RegistrationException;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;

public class RegistrationService {
	private static final Logger log = Logger
			.getLogger(RegistrationService.class.getName());
	private RegistrationStatus rs;
	private UserInfo user;
	private RegistrationService registerUser;
	private UserLoginInfo userLoginInfo;
	static int userNumber = 1;

	@SuppressWarnings("unused")
	public RegistrationStatus registerUser(UserInfo userInformation)
			throws RegistrationException, UnsupportedEncodingException, MessagingException {
		registerUser = new RegistrationService();
		if (userInformation != null) {
			log.info("User Info is not null...");
			Objectify ob = ObjectifyRegisterService.registerService();
			user = new UserInfo();
			userLoginInfo = new UserLoginInfo();
			// adding user data to UserLoginInfo
			userLoginInfo.setEmailId(userInformation.getEmailId());
			userLoginInfo.setLoginId(userInformation.getLoginId());
			userLoginInfo.setPassword("@"
					+ userInformation.getLoginId()+"@");
			userLoginInfo.setUserId(userInformation.getUserName() + "-"
					+ (userNumber) + "@" + userInformation.getLoginId());

			// adding user to UserInfo
			user.setUserId(userInformation.getUserName() + "-" + (userNumber)
					+ "@" + userInformation.getLoginId());
			user.setAge(userInformation.getAge());
			user.setAlternateEmail(userInformation.getAlternateEmail());
			user.setContactNumber(userInformation.getContactNumber());
			user.setEmailId(userInformation.getEmailId());
			user.setGender(userInformation.getGender());
			user.setLoginId(userInformation.getLoginId());
			user.setUserDesignation(userInformation.getUserDesignation());
			user.setUserName(userInformation.getUserName());
			user.setUserWorkLocation(userInformation.getUserWorkLocation());
			user.setContactNumber(userInformation.getContactNumber());

			// saving the data in UserInfo & UserLoginInfo
			ob.put(user);
			ob.put(userLoginInfo);
			log.info("User Data Saved...");

			// sending the mail
			PasswordMailingService password = new PasswordMailingService();
			password.sendMail(userInformation.getEmailId(),
					userInformation.getLoginId(),"@" + userInformation.getLoginId()+"@");

			// updating the service status
			rs = new RegistrationStatus();
			rs.setErrors(null);
			rs.setStatus("registered");
			

			// increementing the user number
			userNumber++;

			return rs;
		} else {
			rs = new RegistrationStatus();
			ArrayList<String> errors = new ArrayList<>();
			errors.add("Empty User Information...");
			rs.setErrors(errors);
			rs.setStatus("not_registered");
			return rs;
		}

	}
}
