package com.cloudprinter.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.api.result.DownloadFileByApiResult;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class DownloadFileByApi extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String fileKey = req.getParameter("filekey");
			// String[] fileData = fileSelected.split("#");

			if (!(fileKey == null || fileKey.equals(""))) {
				BlobKey blobKey = new BlobKey(fileKey);
				blobstoreService.serve(blobKey, resp);

				// making gson object
				Gson resultantGson = new Gson();

				// making result object
				DownloadFileByApiResult result = new DownloadFileByApiResult();
				result.setDownloadStatus("success");
				result.setFileStatus("success");
				result.setError("no-error");

				resp.getWriter().println(resultantGson.toJson(result));
			} else {
				// making gson object
				Gson resultantGson = new Gson();

				// making result object
				DownloadFileByApiResult result = new DownloadFileByApiResult();
				result.setDownloadStatus("failure");
				result.setFileStatus("failure");
				result.setError("error");

				resp.getWriter().println(resultantGson.toJson(result));
			}
		} catch (Exception e) {
			// making gson object
			Gson resultantGson = new Gson();

			// making result object
			DownloadFileByApiResult result = new DownloadFileByApiResult();
			result.setDownloadStatus("failure");
			result.setFileStatus("failure");
			result.setError("error");

			resp.getWriter().println(resultantGson.toJson(result));
		}
	}
}
