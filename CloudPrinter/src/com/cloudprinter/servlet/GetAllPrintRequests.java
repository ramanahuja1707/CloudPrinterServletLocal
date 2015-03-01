package com.cloudprinter.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloudprinter.dto.PrintedFilesInfo;
import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.enums.UploadStatus;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class GetAllPrintRequests extends HttpServlet {
	private ArrayList<PrintedFilesInfo> printFileRequestList;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Objectify ob = ObjectifyRegisterService.registerService();
		Query<PrintedFilesInfo> printFileRequests = (Query<PrintedFilesInfo>) ob
				.query(PrintedFilesInfo.class).filter("loginId",
						session.getAttribute("loginId"));
		if (printFileRequests.list().size() > 0) {
			printFileRequestList = new ArrayList<>();
			for (PrintedFilesInfo file : printFileRequests) {
				printFileRequestList.add(file);
			}
			req.setAttribute("printFileRequestList", printFileRequestList);
			RequestDispatcher rd = req
					.getRequestDispatcher("/showPrintRequests.jsp");
			rd.forward(req, resp);
		} else {
			printFileRequestList = null;
			req.setAttribute("printFileRequestList", printFileRequestList);
			RequestDispatcher rd = req
					.getRequestDispatcher("/showPrintRequests.jsp");
			rd.forward(req, resp);
		}
	}
}
