package com.cloudprinter.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.api.result.DeleteFileByApiResult;
import com.cloudprinter.api.result.Errors;
import com.cloudprinter.dto.DeletedFiles;
import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.enums.DeletedFileStatus;
import com.cloudprinter.enums.UploadStatus;
import com.cloudprinter.services.AuthenticateUserService;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.google.gson.Gson;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class DeleteFileByApi extends HttpServlet {

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
			String fileId = req.getParameter("fileid");

			AuthenticateUserService auth = new AuthenticateUserService();
			String result = auth.authenticateUserLogin(loginId, emailId,
					password);
			if (result.equals("exist")) {

				Objectify ob = ObjectifyRegisterService.registerService();
				Query<UploadedFilesInfo> files = (Query<UploadedFilesInfo>) ob
						.query(UploadedFilesInfo.class)
						.filter("fileId", fileId);
				if (files.list().size() == 1
						&& files.list().get(0).getUploadStatus()
								.equals(UploadStatus.UPLOADED.getStatus())) {
					UploadedFilesInfo fileInfo = files.list().get(0);
					fileInfo.setUploadStatus(UploadStatus.DELETED.getStatus());

					ob.delete(files.list().get(0));
					ob.put(fileInfo);

					DeletedFiles deletedFile = new DeletedFiles();
					deletedFile
							.setFileDeletionStatus(DeletedFileStatus.TEMPORARY
									.getDeletedFileStatus());
					deletedFile.setFileId(fileId);
					deletedFile.setFileName(fileInfo.getFileName());
					deletedFile.setLoginId(fileInfo.getLoginId());
					ob.put(deletedFile);

					// return Gson object of DeleteFileByApiResult
					DeleteFileByApiResult resultGson = new DeleteFileByApiResult();
					resultGson.setDeletionStatus("success");
					resultGson.setErrors(new Errors());
					resultGson.setErrorStatus("no-error");
					resultGson.setFileStatus("exist");

					// creating gson object
					gsonResult = new Gson();

					// converting the object to json by gson and printing on
					// console
					String resultantGson = gsonResult.toJson(resultGson);
					out.println(resultantGson);

				} else {
					// return Gson object of DeleteFileByApiResult
					DeleteFileByApiResult resultGson = new DeleteFileByApiResult();
					resultGson.setDeletionStatus("failure");
					resultGson.setErrors(new Errors());
					resultGson.setErrorStatus("error");
					resultGson.setFileStatus("not-exist");

					// creating gson object
					gsonResult = new Gson();

					// converting the object to json by gson and printing on
					// console
					String resultantGson = gsonResult.toJson(resultGson);
					out.println(resultantGson);
				}
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

				// return Gson object of DeleteFileByApiResult
				DeleteFileByApiResult resultGson = new DeleteFileByApiResult();
				resultGson.setDeletionStatus("failure");
				resultGson.setErrors(errorsList);
				resultGson.setErrorStatus("loginerror");
				resultGson.setFileStatus("not-exist");

				// creating gson object
				gsonResult = new Gson();

				// converting the object to json by gson and printing on
				// console
				String resultantGson = gsonResult.toJson(resultGson);
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

			// return Gson object of DeleteFileByApiResult
			DeleteFileByApiResult resultGson = new DeleteFileByApiResult();
			resultGson.setDeletionStatus("failure");
			resultGson.setErrors(errorsList);
			resultGson.setErrorStatus("error");
			resultGson.setFileStatus("not-exist");

			// creating gson object
			gsonResult = new Gson();

			// converting the object to json by gson and printing on
			// console
			String resultantGson = gsonResult.toJson(resultGson);
			out.println(resultantGson);
		}

	}
}
