package com.cloudprinter.services;

import java.util.logging.Logger;

import com.cloudprinter.dto.UserLoginInfo;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class AuthenticateUserService {
	private static final Logger log = Logger
			.getLogger(AuthenticateUserService.class.getName());
	private String emailExistenceStatus = "not_exist";
	private String userLoginStatus = "not_exist";

	public String authenticateUserEmailId(String loginId, String emailId) {

		Objectify ob = ObjectifyRegisterService.registerService();
		Query<UserLoginInfo> userList = (Query<UserLoginInfo>) ob.query(
				UserLoginInfo.class).filter("emailId", emailId);
		if (userList.list().size() > 0) {

			for (UserLoginInfo u : userList) {
				if (u.getEmailId().equals(emailId)
						|| u.getLoginId().equals(loginId)) {
					emailExistenceStatus = "exist";
					log.info("authenticate user email id :exists");
					break;
				}
			}
		}
		log.info("authenticate user email id :do not exists");
		return emailExistenceStatus;
	}

	public String authenticateUserLogin(String loginId, String emailId,
			String password) {
		Objectify ob = ObjectifyRegisterService.registerService();
		Query<UserLoginInfo> userList = (Query<UserLoginInfo>) ob.query(
				UserLoginInfo.class).filter("emailId", emailId);
		if (userList.list().size() > 0) {
			for (UserLoginInfo u : userList) {
				if (u.getEmailId().equals(emailId)
						&& u.getLoginId().equals(loginId)
						&& u.getPassword().equals(password)) {
					userLoginStatus = "exist";
					log.info("authenticate user Login :success");
					break;
				}
			}
		}
		
		log.info("authenticate user Login :failed");
		return userLoginStatus;
	}

	public String authenticateUserEmailIdAndGetPassword(String loginId,
			String emailId) {
		String passwordToGet = "";
		Objectify ob = ObjectifyRegisterService.registerService();
		Query<UserLoginInfo> userList = (Query<UserLoginInfo>) ob.query(
				UserLoginInfo.class).filter("emailId", emailId);
		if (userList.list().size() > 0) {
			for (UserLoginInfo u : userList) {
				if (u.getEmailId().equals(emailId)
						&& u.getLoginId().equals(loginId)) {
					passwordToGet = u.getPassword();
					log.info("authenticate user email id and get password :success");
					break;
				}
			}
		}
		log.info("authenticate user email id and get password :failed");
		return passwordToGet;
	}
}
