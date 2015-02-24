package com.cloudprinter.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.dto.RegistrationStatus;
import com.cloudprinter.dto.UserInfo;
import com.cloudprinter.dto.ValidationErrors;
import com.cloudprinter.exceptions.RegistrationException;
import com.cloudprinter.services.AuthenticateUserService;
import com.cloudprinter.services.RegistrationService;
import com.cloudprinter.validations.Validations;

@SuppressWarnings("serial")
public class RegisterUser extends HttpServlet {
	private String registrationError;
	private String userName, loginId, age, gender, userDesignation,
			userWorkLocation, emailId, alternateEmailId, contactNo;
	private String registrationStatus;
	private static final Logger log = Logger.getLogger(RegisterUser.class
			.getName());

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserDesignation() {
		return userDesignation;
	}

	public void setUserDesignation(String userDesignation) {
		this.userDesignation = userDesignation;
	}

	public String getUserWorkLocation() {
		return userWorkLocation;
	}

	public void setUserWorkLocation(String userWorkLocation) {
		this.userWorkLocation = userWorkLocation;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAlternateEmailId() {
		return alternateEmailId;
	}

	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getRegistrationError() {
		return registrationError;
	}

	public void setRegistrationError(String registrationError) {
		this.registrationError = registrationError;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		setAge(req.getParameter("age"));
		setAlternateEmailId(req.getParameter("alternateEmailId"));
		setContactNo(req.getParameter("contactNo"));
		setEmailId(req.getParameter("emailId"));
		setGender(req.getParameter("gender"));
		setLoginId(req.getParameter("loginId"));
		setUserDesignation(req.getParameter("userDesignation"));
		setUserName(req.getParameter("userName"));
		setUserWorkLocation(req.getParameter("userWorkLocation"));
		Validations v = new Validations();
		ValidationErrors validationErrors = v.validateUserRegistration(
				getLoginId(), getEmailId(), getUserName(), getAge(),
				getGender(), getUserDesignation(), getUserWorkLocation(),
				getContactNo(), getAlternateEmailId());
		if (validationErrors.getStatus().equals("null"))

		{
			AuthenticateUserService authenticateUser = new AuthenticateUserService();
			String userStatus = authenticateUser.authenticateUserEmailId(
					getLoginId(), getEmailId());
			if (userStatus.matches("not_exist")) {
				try {
					RegistrationService register = new RegistrationService();
					log.info("Registration Service Created...");
					UserInfo userInfo = new UserInfo();
					userInfo.setAge(getAge());
					userInfo.setAlternateEmail(getAlternateEmailId());
					userInfo.setContactNumber(getContactNo());
					userInfo.setEmailId(getEmailId());
					userInfo.setGender(getGender());
					userInfo.setLoginId(getLoginId());
					userInfo.setUserDesignation(getUserDesignation());
					userInfo.setUserName(getUserName());
					userInfo.setUserWorkLocation(getUserWorkLocation());

					RegistrationStatus registrationStatus = register
							.registerUser(userInfo);
					if (registrationStatus.getStatus().equals("registered")) {
						log.info("User Registered Successfully");
						setRegistrationError("User Successfully registered...");
						setRegistrationStatus("success");
						req.setAttribute("registrationError",
								getRegistrationError());
						req.setAttribute("registrationStatus",
								getRegistrationStatus());
						RequestDispatcher rd = req
								.getRequestDispatcher("/registerUser.jsp");
						rd.forward(req, resp);
					} else {
						setRegistrationStatus("failure");
						setRegistrationError("User Not Registered...due to some error :"
								+ registrationStatus.getErrors().get(0));
						log.info("User Registration Error...");
						req.setAttribute("registrationError",
								getRegistrationError());
						req.setAttribute("registrationStatus",
								getRegistrationStatus());
						RequestDispatcher rd = req
								.getRequestDispatcher("/registerUser.jsp");
						rd.forward(req, resp);
					}
				} catch (RegistrationException e) {
					log.info("User Registration Error due to registration exception..");
					setRegistrationError("INVALID CREDENTIALS..."
							+ e.getMessage());
					setRegistrationStatus("failure");
					req.setAttribute("registrationError",
							getRegistrationError());
					req.setAttribute("registrationStatus",
							getRegistrationStatus());
					RequestDispatcher rd = req
							.getRequestDispatcher("/registerUser.jsp");
					rd.forward(req, resp);
				} catch (UnsupportedEncodingException e) {
					log.info("Unsupported Encoding Exception supported..");
					setRegistrationError("Unsupported Encoding Exception supported..");
					setRegistrationStatus("failure");
					req.setAttribute("registrationError",
							getRegistrationError());
					req.setAttribute("registrationStatus",
							getRegistrationStatus());
					RequestDispatcher rd = req
							.getRequestDispatcher("/registerUser.jsp");
					rd.forward(req, resp);
				} catch (MessagingException e) {
					log.info("Messaging Exception..");
					setRegistrationError("Messaging Exception..");
					setRegistrationStatus("failure");
					req.setAttribute("registrationError",
							getRegistrationError());
					req.setAttribute("registrationStatus",
							getRegistrationStatus());
					RequestDispatcher rd = req
							.getRequestDispatcher("/registerUser.jsp");
					rd.forward(req, resp);
				} catch (Exception e) {
					log.info("Exception occured while registering :" + e);
					setRegistrationError("Some Error occured , try after some time :-(");
					setRegistrationStatus("failure");
					req.setAttribute("registrationError",
							getRegistrationError());
					req.setAttribute("registrationStatus",
							getRegistrationStatus());
					RequestDispatcher rd = req
							.getRequestDispatcher("/registerUser.jsp");
					rd.forward(req, resp);
				}

			} else {
				setRegistrationError("USER ALREADY EXIST WITH CORRESPONDING EMAIL-ID...");
				setRegistrationStatus("failure");
				req.setAttribute("registrationError", getRegistrationError());
				req.setAttribute("registrationStatus", getRegistrationStatus());
				RequestDispatcher rd = req
						.getRequestDispatcher("/registerUser.jsp");
				rd.forward(req, resp);
			}
		} else {
			for (String e : validationErrors.getErrors()) {
				setRegistrationError(e + "\n");
			}
			setRegistrationStatus("failure");
			req.setAttribute("registrationError", getRegistrationError());
			req.setAttribute("registrationStatus", getRegistrationStatus());
			RequestDispatcher rd = req
					.getRequestDispatcher("/registerUser.jsp");
			rd.forward(req, resp);
		}
	}
}
