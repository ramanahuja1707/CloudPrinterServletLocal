package com.cloudprinter.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.services.AuthenticateUserService;
import com.cloudprinter.services.PasswordMailingService;

@SuppressWarnings("serial")
public class ForgotPassword extends HttpServlet {
	private String emailId;
	private String loginId;
	private String forgotPasswordStatus;

	public String getForgotPasswordStatus() {
		return forgotPasswordStatus;
	}

	public void setForgotPasswordStatus(String forgotPasswordStatus) {
		this.forgotPasswordStatus = forgotPasswordStatus;
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			setEmailId(req.getParameter("emailId"));
			setLoginId(req.getParameter("loginId"));
			if (!((getEmailId().equals("") || getEmailId() == null) || (getLoginId()
					.equals("") || getLoginId() == null))) {

				AuthenticateUserService userAuthentication = new AuthenticateUserService();
				String password = userAuthentication
						.authenticateUserEmailIdAndGetPassword(getLoginId(),
								getEmailId());
				if (!(password.equals(""))) {
					PasswordMailingService pms = new PasswordMailingService();
					if (pms.sendMail(getEmailId(), getLoginId(), password)) {
						setForgotPasswordStatus("Password Sent To Registered Email Id...:-)");
						req.setAttribute("forgotPasswordStatus",
								getForgotPasswordStatus());
						RequestDispatcher rd = req
								.getRequestDispatcher("/forgotPassword.jsp");
						rd.forward(req, resp);
					} else {
						setForgotPasswordStatus("Password Sending Failed , try again after some time...");
						req.setAttribute("forgotPasswordStatus",
								getForgotPasswordStatus());
						RequestDispatcher rd = req
								.getRequestDispatcher("/forgotPassword.jsp");
						rd.forward(req, resp);
					}
				} else {
					setForgotPasswordStatus("User is not registered yet , please register first..");
					req.setAttribute("forgotPasswordStatus",
							getForgotPasswordStatus());
					RequestDispatcher rd = req
							.getRequestDispatcher("/forgotPassword.jsp");
					rd.forward(req, resp);
				}

			} else {
				setForgotPasswordStatus("Please Fill All the fields...");
				req.setAttribute("forgotPasswordStatus",
						getForgotPasswordStatus());
				RequestDispatcher rd = req
						.getRequestDispatcher("/forgotPassword.jsp");
				rd.forward(req, resp);
			}
		} catch (MessagingException | UnsupportedEncodingException e) {
			setForgotPasswordStatus("Password Sending Error occured , try again...");
			req.setAttribute("forgotPasswordStatus", getForgotPasswordStatus());
			RequestDispatcher rd = req
					.getRequestDispatcher("/forgotPassword.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			setForgotPasswordStatus("Password Sending Failed , try again...");
			req.setAttribute("forgotPasswordStatus", getForgotPasswordStatus());
			RequestDispatcher rd = req
					.getRequestDispatcher("/forgotPassword.jsp");
			rd.forward(req, resp);
		}
	}

}
