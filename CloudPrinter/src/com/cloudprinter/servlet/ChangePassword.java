package com.cloudprinter.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.dto.UserInfo;
import com.cloudprinter.dto.UserLoginInfo;
import com.cloudprinter.dto.ValidationErrors;
import com.cloudprinter.services.AuthenticateUserService;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.cloudprinter.validations.Validations;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class ChangePassword extends HttpServlet {
	private String emailId, loginId, oldPassword , newPassword;
	private String changePasswordError;

	public String getChangePasswordError() {
		return changePasswordError;
	}

	public void setChangePasswordError(String changePasswordError) {
		this.changePasswordError = changePasswordError;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}



	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		setEmailId(req.getParameter("emailId"));
		setLoginId(req.getParameter("loginId"));
		setOldPassword(req.getParameter("oldPassword"));
		setNewPassword(req.getParameter("newPassword"));

		Validations validate = new Validations();
		ValidationErrors errors = validate.validateLogin(getLoginId(),
				getEmailId(), getOldPassword());
		if (errors.getStatus().equals("null")) {
			AuthenticateUserService userLogin = new AuthenticateUserService();
			if (userLogin.authenticateUserLogin(getLoginId(), getEmailId(),
					getOldPassword()).equals("exist")) {
				Objectify ob = ObjectifyRegisterService.registerService();
				Query<UserLoginInfo> user = (Query<UserLoginInfo>) ob.query(
						UserLoginInfo.class).filter("loginId", getLoginId());

				UserLoginInfo newUserData = new UserLoginInfo();
				newUserData.setEmailId(getEmailId());
				newUserData.setLoginId(getLoginId());
				newUserData.setPassword(getNewPassword());
				newUserData.setUserId(user.list().get(0).getUserId());

				// deletion of old record
				ob.delete(user.list().get(0));

				// addition of new record
				ob.put(newUserData);
				
				setChangePasswordError("User Password Changed...:-)");
				req.setAttribute("changePasswordError",
						getChangePasswordError());
				RequestDispatcher rd = req
						.getRequestDispatcher("/changePassword.jsp");
				rd.forward(req, resp);

			} else {
				setChangePasswordError("INVALID CREDENTIALS");
				req.setAttribute("changePasswordError",
						getChangePasswordError());
				RequestDispatcher rd = req
						.getRequestDispatcher("/changePassword.jsp");
				rd.forward(req, resp);
			}
		} else {
			for (String e : errors.getErrors()) {
				setChangePasswordError(e + "\n");
			}

			req.setAttribute("changePasswordError", getChangePasswordError());
			RequestDispatcher rd = req.getRequestDispatcher("/changePassword.jsp");
			rd.forward(req, resp);
		}

	}
}
