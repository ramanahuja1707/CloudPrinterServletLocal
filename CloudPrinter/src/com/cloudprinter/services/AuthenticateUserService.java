package com.cloudprinter.services;

import com.cloudprinter.dto.UserLoginInfo;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class AuthenticateUserService {
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
					break;
				}
			}
		}
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
					break;
				}
			}
		}
		return userLoginStatus;
	}
	public String authenticateUserEmailIdAndGetPassword(String loginId, String emailId) {
		String passwordToGet="";
		Objectify ob = ObjectifyRegisterService.registerService();
		Query<UserLoginInfo> userList = (Query<UserLoginInfo>) ob.query(
				UserLoginInfo.class).filter("emailId", emailId);
		if (userList.list().size() > 0) {
			for (UserLoginInfo u : userList) {
				if (u.getEmailId().equals(emailId)
						&& u.getLoginId().equals(loginId)) {
					passwordToGet = u.getPassword();
					break;
				}
			}
		}
		return passwordToGet;
	}
}
