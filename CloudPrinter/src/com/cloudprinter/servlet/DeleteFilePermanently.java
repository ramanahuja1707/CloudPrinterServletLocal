package com.cloudprinter.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.dto.DeletedFiles;
import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.enums.DeletedFileStatus;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class DeleteFilePermanently extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	private String blobKeyOfFileToBeDeleted;
	

	public String getBlobKeyOfFileToBeDeleted() {
		return blobKeyOfFileToBeDeleted;
	}


	public void setBlobKeyOfFileToBeDeleted(String blobKeyOfFileToBeDeleted) {
		this.blobKeyOfFileToBeDeleted = blobKeyOfFileToBeDeleted;
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getParameter("fileId") != null) {
			String fileIdAndKey = req.getParameter("fileSelected");
			String[] fileData = fileIdAndKey.split("#");

			Objectify ob = ObjectifyRegisterService.registerService();
			Query<UploadedFilesInfo> files = (Query<UploadedFilesInfo>) ob
					.query(UploadedFilesInfo.class).filter("fileId",
							fileData[0]);
			
			setBlobKeyOfFileToBeDeleted(files.list().get(0).getFileKey());

			Query<DeletedFiles> deletedFile = (Query<DeletedFiles>) ob.query(
					DeletedFiles.class).filter("fileId", fileData[0]);

			DeletedFiles newDeletedFileStatus = new DeletedFiles();
			newDeletedFileStatus = deletedFile.list().get(0);

			newDeletedFileStatus
					.setFileDeletionStatus(DeletedFileStatus.PERMANENT
							.getDeletedFileStatus());

			ob.delete(files.list().get(0));
			ob.delete(deletedFile.list().get(0));
			ob.put(newDeletedFileStatus);

			BlobKey key = new BlobKey(getBlobKeyOfFileToBeDeleted());
			blobstoreService.delete(key);

			req.setAttribute("deleteFilePermanentError",
					"File deleted permanently from database...");
			RequestDispatcher rd = req.getRequestDispatcher("getdeletedfiles");
			rd.forward(req, resp);

		} else {
			req.setAttribute("deleteFilePermanentError",
					"Please select a file...:-(");
			RequestDispatcher rd = req.getRequestDispatcher("getdeletedfiles");
			rd.forward(req, resp);

		}
	}
}
