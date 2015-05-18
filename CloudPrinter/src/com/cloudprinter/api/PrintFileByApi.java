package com.cloudprinter.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.api.result.Errors;
import com.cloudprinter.api.result.PrintFileByApiResult;
import com.cloudprinter.dto.PrintedFilesInfo;
import com.cloudprinter.enums.PageNumberCustomization;
import com.cloudprinter.enums.PrintStatus;
import com.cloudprinter.services.AuthenticateUserService;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.google.gson.Gson;
import com.googlecode.objectify.Objectify;

@SuppressWarnings("serial")
public class PrintFileByApi extends HttpServlet {
	private String printCopies, paperType, printingLocation, fileName, fileKey,
			pageSpecification, filePrintStatus;

	public String getFilePrintStatus() {
		return filePrintStatus;
	}

	public void setFilePrintStatus(String filePrintStatus) {
		this.filePrintStatus = filePrintStatus;
	}

	public String getPrintCopies() {
		return printCopies;
	}

	public void setPrintCopies(String printCopies) {
		this.printCopies = printCopies;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getPrintingLocation() {
		return printingLocation;
	}

	public void setPrintingLocation(String printingLocation) {
		this.printingLocation = printingLocation;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getPageSpecification() {
		return pageSpecification;
	}

	public void setPageSpecification(String pageSpecification) {
		this.pageSpecification = pageSpecification;
	}

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
				setPaperType(req.getParameter("papertype"));
				setFileKey(req.getParameter("filekey"));
				setFileName(req.getParameter("filename"));
				setPrintCopies(req.getParameter("printcopies"));
				setPrintingLocation(req.getParameter("printinglocation"));
				setPageSpecification(req.getParameter("pagespecification"));

				Date todayDate = new Date();
				Objectify ob = ObjectifyRegisterService.registerService();

				PrintedFilesInfo printFileInfo = new PrintedFilesInfo();
				printFileInfo.setDateOfPrintRequest(todayDate);
				printFileInfo.setFileName(getFileName());
				printFileInfo.setEndPageNumber("0");
				printFileInfo.setFileKey(getFileKey());
				printFileInfo.setLoginId(loginId);
				printFileInfo
						.setPageNumberCustomization(PageNumberCustomization.FALSE
								.getPageNumberCustomization());
				printFileInfo.setPageSpecification(getPageSpecification());
				printFileInfo.setPaperType(getPaperType());
				printFileInfo.setPrintCopies(getPrintCopies());
				printFileInfo.setPrintError("no-error");
				printFileInfo.setPrintRequestId(getFileKey() + "@" + todayDate);
				printFileInfo.setPrintStatus(PrintStatus.SENT.getPrintStatus());
				printFileInfo.setStartPageNumber("0");
				printFileInfo.setPrintingLocation(getPrintingLocation());

				ob.put(printFileInfo);

				// creating object of result of this User login
				PrintFileByApiResult printFileByApiResult = new PrintFileByApiResult();
				printFileByApiResult.setPrintStatus("success");
				printFileByApiResult.setErrors(null);

				// creating gson object
				gsonResult = new Gson();

				// converting the object to json by gson and printing on console
				String resultantGson = gsonResult.toJson(printFileByApiResult);
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
				// creating object of result of this User login
				PrintFileByApiResult printFileByApiResult = new PrintFileByApiResult();
				printFileByApiResult.setPrintStatus("failure");
				printFileByApiResult.setErrors(errorsList);

				// creating gson object
				gsonResult = new Gson();

				// converting the object to json by gson and printing on console
				String resultantGson = gsonResult.toJson(printFileByApiResult);
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

			// creating object of result of this User login
			PrintFileByApiResult printFileByApiResult = new PrintFileByApiResult();
			printFileByApiResult.setPrintStatus("failure");
			printFileByApiResult.setErrors(errorsList);

			// creating gson object
			gsonResult = new Gson();

			// converting the object to json by gson and printing on console
			String resultantGson = gsonResult.toJson(printFileByApiResult);
			out.println(resultantGson);
		}

	}

}
