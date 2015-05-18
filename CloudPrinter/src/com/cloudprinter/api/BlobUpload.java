package com.cloudprinter.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.enums.FileType;
import com.cloudprinter.enums.UploadStatus;
import com.cloudprinter.services.UploadFileDataSavingService;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gson.Gson;

public class BlobUpload extends HttpServlet {
	BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	long sizeInBytes;
	private String fileName, contentType;
	UploadFileDataSavingService fileDataSavingService;
	UploadedFilesInfo fileData;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String loginId = req.getParameter("loginId");
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		BlobKey blobKey = blobs.get("photo").get(0);

		BlobInfo blobInfo = new BlobInfoFactory().loadBlobInfo(blobKey);
		sizeInBytes = blobInfo.getSize();
		fileName = blobInfo.getFilename();
		contentType = blobInfo.getContentType();

		fileDataSavingService = new UploadFileDataSavingService();
		fileData = new UploadedFilesInfo();
		Date todayDate = new Date();
		fileData.setContentType(contentType);
		fileData.setDateOfUploading(todayDate);
		fileData.setFileId(fileName + todayDate);
		fileData.setFileKey(blobKey.getKeyString());
		fileData.setFileName(fileName);
		fileData.setFileSize(sizeInBytes + "");

		fileData.setFileType(FileType.USERUPLOADED.getFileType());
		fileData.setIpAddressOfHost("null");
		fileData.setLoginId(loginId);
		fileData.setUploadError("null");
		fileData.setUploadStatus(UploadStatus.UPLOADED.getStatus());

		if (fileDataSavingService.FileDataSavingService(fileData).equals(
				"file_data_saved")) {
			resp.setContentType("text/json");
			// JSONObject j = new JSONObject();
			// j.put("fileData", fileData);

			/*
			 * req.setAttribute("blobKey", blobKey.getKeyString());
			 * RequestDispatcher rd = req.getRequestDispatcher("/blobKey.jsp");
			 * rd.forward(req, resp);
			 */
			Gson g = new Gson();
			String resultGson = g.toJson(fileData);
			resp.getWriter().println(resultGson.toString());

		} else {
			blobstoreService.delete(blobKey);
			/*
			 * req.setAttribute("blobKey", blobKey.getKeyString());
			 * RequestDispatcher rd = req.getRequestDispatcher("/blobKey.jsp");
			 * rd.forward(req, resp);
			 */

			fileData.setUploadError("error");
			resp.setContentType("text/json");
			Gson g = new Gson();
			String resultGson = g.toJson(fileData);
			resp.getWriter().println(resultGson);

		}

	}

}
