package com.cloudprinter.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class ServeFileToClient extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String fileKey = req.getParameter("fileKey");

			if (fileKey != null) {
				BlobKey blobKey = new BlobKey(fileKey);
				blobstoreService.serve(blobKey, resp);
			} else {
				JSONObject j = new JSONObject();
				j.put("downloadError","not-exist");
			}
		} catch (Exception e) {
			JSONObject j = new JSONObject();
			j.put("downloadError","not-exist");
		}
	}

}
