package com.cloudprinter.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.enums.UploadStatus;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class GetAllFiles extends HttpServlet {
	private ArrayList<UploadedFilesInfo> uploadedFiles;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Objectify ob = ObjectifyRegisterService.registerService();
		Query<UploadedFilesInfo> uploadedFilesList = (Query<UploadedFilesInfo>) ob
				.query(UploadedFilesInfo.class).filter("loginId",
						session.getAttribute("loginId"));
		if (uploadedFilesList.list().size() > 0) {
			uploadedFiles = new ArrayList<>();
			for (UploadedFilesInfo file : uploadedFilesList) {
				if (!(file.getUploadStatus().equals(UploadStatus.DELETED
						.getStatus())))
					uploadedFiles.add(file);
			}
			if (uploadedFiles.size() > 0) {
				req.setAttribute("uploadedFiles", uploadedFiles);
				RequestDispatcher rd = req
						.getRequestDispatcher("/allFilesPage.jsp");
				rd.forward(req, resp);
			} else {
				RequestDispatcher rd = req
						.getRequestDispatcher("/noRecordFound.jsp");
				rd.forward(req, resp);
			}
		} else {

			RequestDispatcher rd = req
					.getRequestDispatcher("/noRecordFound.jsp");
			rd.forward(req, resp);
		}
	}
}
