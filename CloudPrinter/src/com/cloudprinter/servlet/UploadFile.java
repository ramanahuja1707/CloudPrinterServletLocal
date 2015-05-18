package com.cloudprinter.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.enums.FileType;
import com.cloudprinter.enums.UploadStatus;
import com.cloudprinter.services.UploadFileDataSavingService;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class UploadFile extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	private File myFile;
	private String fieldName, fileName, contentType;
	long sizeInBytes;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head><title></title></head>");
		out.println("<body>");

		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("myFile");

		BlobInfo blobInfo = new BlobInfoFactory().loadBlobInfo(blobKeys.get(0));
		sizeInBytes = blobInfo.getSize();
		fileName = blobInfo.getFilename();
		contentType = blobInfo.getContentType();

		if (blobKeys == null || blobKeys.isEmpty()) {
			req.setAttribute("fileUploadStatus",
					"Error occured , try again after sometime...:-(");
			resp.getWriter().println(req.getAttribute("fileUploadStatus"));
			out.println("<a href='/userHome.jsp'>Go back</a>");
			// resp.getWriter().println(req.getAttribute("fileUploadStatus"));
			/*
			 * RequestDispatcher rd = req
			 * .getRequestDispatcher("/serve?fileUploadSuccess=" +
			 * req.getAttribute("fileUploadSuccess")); rd.forward(req, resp);
			 */

		} else {
			UploadFileDataSavingService fileDataSavingService = new UploadFileDataSavingService();
			UploadedFilesInfo fileData = new UploadedFilesInfo();
			Date todayDate = new Date();
			HttpSession session = req.getSession(false);
			fileData.setContentType(contentType);
			fileData.setDateOfUploading(todayDate);
			fileData.setFileId(fileName + todayDate);
			fileData.setFileKey(blobKeys.get(0).getKeyString());
			fileData.setFileName(fileName);
			fileData.setFileSize(sizeInBytes + "");

			fileData.setFileType(FileType.USERUPLOADED.getFileType());
			fileData.setIpAddressOfHost("null");
			fileData.setLoginId(session.getAttribute("loginId").toString());
			fileData.setUploadError("null");
			fileData.setUploadStatus(UploadStatus.UPLOADED.getStatus());

			if (fileDataSavingService.FileDataSavingService(fileData).equals(
					"file_data_saved")) {
				req.setAttribute("fileUploadStatus",
						"File Uploaded Successfully...:-)");
				resp.getWriter().println(req.getAttribute("fileUploadStatus"));
				out.println("<a href='/userHome.jsp'>Go back</a>");
				// resp.sendRedirect("/uploadFiles.jsp");
				/*
				 * RequestDispatcher rd = req
				 * .getRequestDispatcher("/serve?fileUploadSuccess=" +
				 * req.getAttribute("fileUploadSuccess")); rd.forward(req,
				 * resp);
				 */

			} else {
				req.setAttribute("fileUploadStatus", "File Uploading Error :-("

				);

				blobstoreService.delete(blobKeys.get(0));
				resp.getWriter().println(req.getAttribute("fileUploadStatus"));
				out.println("<a href='/userHome.jsp'>Go back</a>");
				// resp.sendRedirect("/uploadFiles.jsp");
				/*
				 * RequestDispatcher rd = req
				 * .getRequestDispatcher("/serve?fileUploadSuccess=" +
				 * req.getAttribute("fileUploadSuccess")); rd.forward(req,
				 * resp);
				 */

			}

			out.print("</body></html>\r\n");
		}
	}
}
