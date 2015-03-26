package com.cloudprinter.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.api.result.Errors;
import com.cloudprinter.api.result.GetPrintedFilesResult;
import com.cloudprinter.dto.PrintedFilesInfo;
import com.cloudprinter.services.AuthenticateUserService;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.google.gson.Gson;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class GetPrintedFiles extends HttpServlet {

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
				ArrayList<PrintedFilesInfo> myFilesList;
				int filesCount = 0;
				// quering the list from UploadedFilesInfo table
				Objectify ob = ObjectifyRegisterService.registerService();
				Query<PrintedFilesInfo> printedFilesList = (Query<PrintedFilesInfo>) ob
						.query(PrintedFilesInfo.class).filter("loginId",
								loginId);

				if (printedFilesList.list().size() > 0) {
					myFilesList = (ArrayList<PrintedFilesInfo>) printedFilesList
							.list();
					filesCount = myFilesList.size();
				} else {
					myFilesList = null;
				}

				// creating object of result of this User login
				GetPrintedFilesResult getPrintedFilesResult = new GetPrintedFilesResult();
				getPrintedFilesResult.setStatus("success");
				getPrintedFilesResult.setErrors(new Errors());
				getPrintedFilesResult.setPrintedFiles(myFilesList);
				getPrintedFilesResult.setFilesCount(filesCount);

				// creating gson object
				gsonResult = new Gson();

				// converting the object to json by gson and printing on console
				String resultantGson = gsonResult.toJson(getPrintedFilesResult);
				out.println(resultantGson);
			} else {
				// setting of errors
				Errors errorsList = new Errors();
				ArrayList<String> errors = new ArrayList<>();
				errors.add("Invalid Login Credentials");

				// setting of error code
				ArrayList<Integer> code = new ArrayList<>();
				code.add(49);

				// adding both errors and code list to errors object
				errorsList.setErrors(errors);
				errorsList.setCode(code);

				// creating object of result of this user login
				GetPrintedFilesResult getPrintedFilesResult = new GetPrintedFilesResult();
				getPrintedFilesResult.setStatus("loginError");
				getPrintedFilesResult.setErrors(errorsList);
				getPrintedFilesResult.setFilesCount(0);
				getPrintedFilesResult.setPrintedFiles(null);

				// creating gson object
				gsonResult = new Gson();

				// converting the object to json by gson and printing on console
				String resultantGson = gsonResult.toJson(getPrintedFilesResult);
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
			GetPrintedFilesResult getPrintedFilesResult = new GetPrintedFilesResult();
			getPrintedFilesResult.setStatus("failure");
			getPrintedFilesResult.setErrors(errorsList);
			getPrintedFilesResult.setPrintedFiles(null);
			getPrintedFilesResult.setFilesCount(0);

			// creating gson object
			gsonResult = new Gson();

			// converting the object to json by gson and printing on console
			String resultantGson = gsonResult.toJson(getPrintedFilesResult);
			out.println(resultantGson);
		}
	}

}
