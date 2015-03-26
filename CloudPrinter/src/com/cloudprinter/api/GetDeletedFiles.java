package com.cloudprinter.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.api.result.Errors;
import com.cloudprinter.api.result.GetDeletedFilesResult;
import com.cloudprinter.dto.DeletedFiles;
import com.cloudprinter.enums.DeletedFileStatus;
import com.cloudprinter.services.AuthenticateUserService;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.google.gson.Gson;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class GetDeletedFiles extends HttpServlet {

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
				ArrayList<DeletedFiles> myFilesList;
				int filesCount = 0;
				// quering the list from UploadedFilesInfo table
				Objectify ob = ObjectifyRegisterService.registerService();
				Query<DeletedFiles> deletedFilesList = (Query<DeletedFiles>) ob
						.query(DeletedFiles.class).filter("loginId", loginId);
				if (deletedFilesList.list().size() > 0) {
					myFilesList = new ArrayList<>();
					for (DeletedFiles deletedFiles : deletedFilesList) {
						if (deletedFiles.getFileDeletionStatus().equals(
								DeletedFileStatus.TEMPORARY
										.getDeletedFileStatus())) {
							myFilesList.add(deletedFiles);
						}
					}
					filesCount = myFilesList.size();
				} else {
					myFilesList = null;
				}

				// creating object of result of this User login
				GetDeletedFilesResult getDeletedFilesResult = new GetDeletedFilesResult();
				getDeletedFilesResult.setStatus("success");
				getDeletedFilesResult.setErrors(new Errors());
				getDeletedFilesResult.setDeletedFiles(myFilesList);
				getDeletedFilesResult.setFilesCount(filesCount);

				// creating gson object
				gsonResult = new Gson();

				// converting the object to json by gson and printing on console
				String resultantGson = gsonResult.toJson(getDeletedFilesResult);
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
				GetDeletedFilesResult getDeletedFilesResult = new GetDeletedFilesResult();
				getDeletedFilesResult.setStatus("loginError");
				getDeletedFilesResult.setErrors(errorsList);
				getDeletedFilesResult.setFilesCount(0);
				getDeletedFilesResult.setDeletedFiles(null);

				// creating gson object
				gsonResult = new Gson();

				// converting the object to json by gson and printing on console
				String resultantGson = gsonResult.toJson(getDeletedFilesResult);
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
			GetDeletedFilesResult getDeletedFilesResult = new GetDeletedFilesResult();
			getDeletedFilesResult.setStatus("failure");
			getDeletedFilesResult.setErrors(errorsList);
			getDeletedFilesResult.setDeletedFiles(null);
			getDeletedFilesResult.setFilesCount(0);

			// creating gson object
			gsonResult = new Gson();

			// converting the object to json by gson and printing on console
			String resultantGson = gsonResult.toJson(getDeletedFilesResult);
			out.println(resultantGson);
		}
	}
}
