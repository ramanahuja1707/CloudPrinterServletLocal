package com.cloudprinter.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.enums.UploadStatus;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class BlobUploadSaveData extends HttpServlet {
	UploadedFilesInfo fileObject = null;
	JSONObject resultJson = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/json");
		PrintWriter out = resp.getWriter();
		try {
			String fileKey = req.getParameter("filekey");
			String loginId = req.getParameter("loginid");
			Objectify ob = ObjectifyRegisterService.registerService();
			Query<UploadedFilesInfo> files = (Query<UploadedFilesInfo>) ob
					.query(UploadedFilesInfo.class).filter("fileKey", fileKey);
			if (files.list().size() == 1) {
				for (UploadedFilesInfo u : files) {
					if (u.getFileKey().equals(fileKey)
							&& u.getUploadStatus().equals(
									UploadStatus.UPLOADED.getStatus())) {
						fileObject = u;
					}
				}

				if (fileObject != null) {
					ob.delete(fileObject);
					fileObject.setLoginId(loginId);
					ob.put(fileObject);
					resultJson = new JSONObject();
					resultJson.put("fileStatus", "exist");
					resultJson.put("fileSaveStatus", "ok");
					out.println(resultJson.toString());
				} else {
					resultJson = new JSONObject();
					resultJson.put("fileStatus", "not_exist");
					resultJson.put("fileSaveStatus", "not_ok");
					out.println(resultJson.toString());
				}
			} else {
				resultJson = new JSONObject();
				resultJson.put("fileStatus", "not_exist");
				resultJson.put("fileSaveStatus", "not_ok");
				out.println(resultJson.toString());

			}
		} catch (Exception e) {
			resultJson = new JSONObject();
			resultJson.put("fileStatus", "not_exist");
			resultJson.put("fileSaveStatus", "not_ok");
			out.println(resultJson.toString());
		}
	}
}
