package com.cloudprinter.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class DownloadFile extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String fileSelected = req.getParameter("fileSelected");
		//	String[] fileData = fileSelected.split("!");
			
			if (fileSelected != null) {
				BlobKey blobKey = new BlobKey(fileSelected);
				blobstoreService.serve(blobKey, resp);
			} else {
				req.setAttribute("downloadError", "File Does Not Exist ...:-(");
				RequestDispatcher rd = req.getRequestDispatcher("/getallfiles");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("downloadError", "Some Error Occured..:-(");
			RequestDispatcher rd = req.getRequestDispatcher("/getallfiles");
			rd.forward(req, resp);

		}
	}
}
