package com.cloudprinter.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.dto.UserLoginInfo;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class ForgotLoginId extends HttpServlet {

	private String emailId;
	private String forgotLoginIdStatus;

	public String getForgotLoginIdStatus() {
		return forgotLoginIdStatus;
	}

	public void setForgotLoginIdStatus(String forgotLoginIdStatus) {
		this.forgotLoginIdStatus = forgotLoginIdStatus;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		setEmailId(req.getParameter("emailId"));
		if (!(getEmailId().equals("") || getEmailId() == null)) {
			Objectify ob = ObjectifyRegisterService.registerService();
			Query<UserLoginInfo> userList = (Query<UserLoginInfo>) ob.query(
					UserLoginInfo.class).filter("emailId", getEmailId());
			setForgotLoginIdStatus("Sorry , Your Email Id is not registered...:-( , please register first...");
			if (userList.list().size() > 0) {
				for (UserLoginInfo u : userList) {
					if (u.getEmailId().equals(getEmailId())) {
						setForgotLoginIdStatus("Your Username is : "
								+ u.getLoginId());
						break;
					}
				}
			}
			req.setAttribute("forgotLoginIdStatus", getForgotLoginIdStatus());
			RequestDispatcher rd = req
					.getRequestDispatcher("/forgotLoginId.jsp");
			rd.forward(req, resp);
		} else {
			req.setAttribute("forgotLoginIdStatus", "Invalid Email-Id :-(");
			RequestDispatcher rd = req
					.getRequestDispatcher("/forgotLoginId.jsp");
			rd.forward(req, resp);
		}
	}
}
