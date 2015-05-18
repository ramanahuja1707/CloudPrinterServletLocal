package com.cloudprinter.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloudprinter.dto.ValidationErrors;
import com.cloudprinter.services.AuthenticateUserService;
import com.cloudprinter.validations.Validations;

@SuppressWarnings("serial")
public class Login extends HttpServlet {

	private String loginId;
	private String mailId;
	private String password;
	private String loginError;

	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		setLoginId(req.getParameter("loginId"));
		setMailId(req.getParameter("mailId"));
		setPassword(req.getParameter("password"));
		Validations validate = new Validations();
		ValidationErrors errors = validate.validateLogin(getLoginId(),
				getMailId(), getPassword());
		if (errors.getStatus().equals("null")) {
			AuthenticateUserService userLogin = new AuthenticateUserService();
			if (userLogin.authenticateUserLogin(getLoginId(), getMailId(),
					getPassword()).equals("exist")) {
				setLoginError("Login Success ...");
				HttpSession session = req.getSession(true);
				session.putValue("emailId", getMailId());
				session.putValue("loginId", getLoginId());
				session.putValue("password", getPassword());
				RequestDispatcher rd = req
						.getRequestDispatcher("/userHome.jsp");
				rd.forward(req, resp);

			} else {

				setLoginError("INVALID CREDENTIALS");
				req.setAttribute("loginError", getLoginError());
				RequestDispatcher rd = req
						.getRequestDispatcher("/loginPage.jsp");
				rd.forward(req, resp);
			}
		} else {
			for (String e : errors.getErrors()) {
				setLoginError(e + "\n");
			}

			req.setAttribute("loginError", getLoginError());
			RequestDispatcher rd = req.getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, resp);
		}
	}
}
