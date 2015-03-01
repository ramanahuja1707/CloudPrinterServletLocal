package com.cloudprinter.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.dto.DeletedFiles;
import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.dto.UserLoginInfo;
import com.cloudprinter.enums.DeletedFileStatus;
import com.cloudprinter.enums.UploadStatus;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class DeleteFile extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			if (req.getParameter("fileSelected") == null) {
				req.setAttribute("deleteError", "Please select a file...:-(");
				RequestDispatcher rd = req.getRequestDispatcher("/getallfiles");
				rd.forward(req, resp);
			} else {
				String fileId = req.getParameter("fileId");
				String[] fileData = req.getParameter("fileSelected").split("#");
				Objectify ob = ObjectifyRegisterService.registerService();
				Query<UploadedFilesInfo> files = (Query<UploadedFilesInfo>) ob
						.query(UploadedFilesInfo.class)
						.filter("fileId", fileId);
				if (files.list().size() == 1) {
					UploadedFilesInfo fileInfo = files.list().get(0);
					fileInfo.setUploadStatus(UploadStatus.DELETED.getStatus());

					ob.delete(files.list().get(0));
					ob.put(fileInfo);

					DeletedFiles deletedFile = new DeletedFiles();
					deletedFile.setFileDeletionStatus(DeletedFileStatus.TEMPORARY
							.getDeletedFileStatus());
					deletedFile.setFileId(fileId);
					deletedFile.setFileName(fileInfo.getFileName());
					deletedFile.setLoginId(fileInfo.getLoginId());
					ob.put(deletedFile);

					req.setAttribute("deleteError",
							"File Deleted Successfully ...:-)");
					RequestDispatcher rd = req
							.getRequestDispatcher("/getallfiles");
					rd.forward(req, resp);
				} else {
					req.setAttribute("deleteError", "Some error occured..:-(");
					RequestDispatcher rd = req
							.getRequestDispatcher("/getallfiles");
					rd.forward(req, resp);
				}
			}

		} catch (Exception e) {
			req.setAttribute("deleteError", "Some error occured..:-(");
			RequestDispatcher rd = req.getRequestDispatcher("/getallfiles");
			rd.forward(req, resp);
		}
	}

}
