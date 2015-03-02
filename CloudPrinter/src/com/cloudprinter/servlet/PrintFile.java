package com.cloudprinter.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.dto.PrintedFilesInfo;
import com.cloudprinter.enums.PageNumberCustomization;
import com.cloudprinter.enums.PrintStatus;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;

@SuppressWarnings("serial")
public class PrintFile extends HttpServlet {
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
		try {
			resp.setContentType("text/plain");
			setPaperType(req.getParameter("paperType"));
			setFileKey(req.getParameter("fileKey"));
			setFileName(req.getParameter("fileName"));
			setPrintCopies(req.getParameter("printCopies"));
			setPrintingLocation(req.getParameter("printingLocation"));
			setPageSpecification(req.getParameter("pageSpecification"));

			Date todayDate = new Date();
			Objectify ob = ObjectifyRegisterService.registerService();

			PrintedFilesInfo printFileInfo = new PrintedFilesInfo();
			printFileInfo.setDateOfPrintRequest(todayDate);
			printFileInfo.setFileName(getFileName());
			printFileInfo.setEndPageNumber("0");
			printFileInfo.setFileKey(getFileKey());
			printFileInfo.setLoginId(req.getSession(false)
					.getAttribute("loginId").toString());
			printFileInfo
					.setPageNumberCustomization(PageNumberCustomization.FALSE
							.getPageNumberCustomization());
			printFileInfo.setPageSpecification(getPageSpecification());
			printFileInfo.setPaperType(getPaperType());
			printFileInfo.setPrintCopies(getPrintCopies());
			printFileInfo.setPrintError("no-error");
			printFileInfo.setPrintRequestId(getFileKey() + "@" + todayDate);
			printFileInfo
					.setPrintStatus(PrintStatus.INPROCESS.getPrintStatus());
			printFileInfo.setStartPageNumber("0");
			printFileInfo.setPrintingLocation(getPrintingLocation());

			ob.put(printFileInfo);
			setFilePrintStatus("File Suucessfully Sent for printing...:-)");
			req.setAttribute("filePrintStatus", getFilePrintStatus());
			RequestDispatcher rd = req.getRequestDispatcher("/printFileSuccess.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			setFilePrintStatus("File Printing Failed , Try Again ..:-(");
			req.setAttribute("filePrintStatus", getFilePrintStatus());
			RequestDispatcher rd = req.getRequestDispatcher("/printFileSuccess.jsp");
			rd.forward(req, resp);
		}

	}

}
