package com.cloudprinter.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.cloudprinter.dto.PrintedFilesInfo;
import com.cloudprinter.enums.PrintStatus;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class UpdateFilePrintStatus extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JSONObject result;
		resp.setContentType("text/json");
		String fileKey = req.getParameter("fileKey");
		String printError = req.getParameter("printError");
		String printStatus = req.getParameter("printStatus");
		if (printError.equals("") || printStatus.equals("")
				|| fileKey.equals("") ) {
			result = new JSONObject();
			result.put("status", "not-updated");
			result.put("error", "invalid-input");
			resp.getWriter().println(result.toJSONString());
		} else {
			Objectify ob = ObjectifyRegisterService.registerService();
			Query<PrintedFilesInfo> filesList = (Query<PrintedFilesInfo>) ob
					.query(PrintedFilesInfo.class).filter("fileKey", fileKey);
			if (filesList.list().size() > 0) {
				PrintedFilesInfo file = filesList.list().get(0);
				if (printStatus.equals("printed"))
					file.setPrintStatus(PrintStatus.PRINTED.getPrintStatus());
				else
					file.setPrintStatus(PrintStatus.PRINTFAILURE
							.getPrintStatus());
				file.setPrintError(printError);
				ob.delete(filesList.list().get(0));
				ob.put(file);
				result = new JSONObject();
				result.put("status", "updated");
				result.put("error", "no-error");
				resp.getWriter().println(result.toJSONString());
			} else {
				result = new JSONObject();
				result.put("status", "not-updated");
				result.put("error", "file-not-found");
				resp.getWriter().println(result.toJSONString());
			}
		}

	}

}
