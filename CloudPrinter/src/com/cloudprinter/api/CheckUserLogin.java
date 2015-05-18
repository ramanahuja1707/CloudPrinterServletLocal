package com.cloudprinter.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.api.result.CheckUserLoginResult;
import com.cloudprinter.api.result.Errors;
import com.cloudprinter.services.AuthenticateUserService;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class CheckUserLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Gson gsonResult;
		resp.setContentType("text/json");
		PrintWriter out = resp.getWriter();
		try {
			String emailId = req.getParameter("emailid");
			String password = req.getParameter("password");
			String loginId = req.getParameter("loginid");

			AuthenticateUserService auth = new AuthenticateUserService();
			String result = auth.authenticateUserLogin(loginId, emailId,
					password);
			if (result.equals("exist")) {
				// creating object of result of this User login
				CheckUserLoginResult loginResult = new CheckUserLoginResult();
				loginResult.setLoginStatus("success");
				loginResult.setErrors(null);

				// creating gson object
				gsonResult = new Gson();

				// converting the object to json by gson and printing on console
				String resultantGson = gsonResult.toJson(loginResult);
				out.println(resultantGson);
			} else {
				// setting of errors
				Errors errorsList = new Errors();
				ArrayList<String> errors = new ArrayList<>();
				errors.add("Invalid Credentials");

				// setting of error code
				ArrayList<Integer> code = new ArrayList<>();
				code.add(49);

				// adding both errors and code list to errors object
				errorsList.setErrors(errors);
				errorsList.setCode(code);

				// creating object of result of this user login
				CheckUserLoginResult loginResult = new CheckUserLoginResult();
				loginResult.setLoginStatus("failure");
				loginResult.setErrors(errorsList);

				// creating gson object
				gsonResult = new Gson();

				// converting the object to json by gson and printing on console
				String resultantGson = gsonResult.toJson(loginResult);
				out.println(resultantGson);
			}
		} catch (Exception e) {
			// setting of errors
			Errors errorsList = new Errors();
			ArrayList<String> errors = new ArrayList<>();
			errors.add(e.getMessage());

			// setting of error code
			ArrayList<Integer> code = new ArrayList<>();
			code.add(0);

			// adding both errors and code list to errors object
			errorsList.setErrors(errors);
			errorsList.setCode(code);

			// creating object of result of this user login
			CheckUserLoginResult loginResult = new CheckUserLoginResult();
			loginResult.setLoginStatus("failure");
			loginResult.setErrors(errorsList);

			// creating gson object
			gsonResult = new Gson();

			// converting the object to json by gson and printing on console
			String resultantGson = gsonResult.toJson(loginResult);
			out.println(resultantGson);

		}
	}

}
