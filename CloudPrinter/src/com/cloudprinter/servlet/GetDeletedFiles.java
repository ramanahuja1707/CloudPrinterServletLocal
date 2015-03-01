package com.cloudprinter.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloudprinter.dto.DeletedFiles;
import com.cloudprinter.enums.DeletedFileStatus;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class GetDeletedFiles extends HttpServlet {
	private ArrayList<DeletedFiles> deletedFiles;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Objectify ob = ObjectifyRegisterService.registerService();
		Query<DeletedFiles> deletedFilesList = (Query<DeletedFiles>) ob.query(
				DeletedFiles.class).filter("loginId",
				session.getAttribute("loginId"));
		if (deletedFilesList.list().size() > 0) {
			deletedFiles = new ArrayList<>();
			for (DeletedFiles deletedFile : deletedFilesList) {
				if (deletedFile.getFileDeletionStatus().equals(
						DeletedFileStatus.TEMPORARY.getDeletedFileStatus())) {
					deletedFiles.add(deletedFile);
				}
			}
			req.setAttribute("deletedFiles",deletedFiles);
			RequestDispatcher rd = req
					.getRequestDispatcher("/deletedFilesPage.jsp");
			rd.forward(req, resp);
		} else {
			deletedFiles = null;
			req.setAttribute("deletedFiles", deletedFiles);
			RequestDispatcher rd = req
					.getRequestDispatcher("/deletedFilesPage.jsp");
			rd.forward(req, resp);
		}
	}

}
