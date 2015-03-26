package com.cloudprinter.client;

import javax.servlet.http.HttpServlet;

import org.json.simple.JSONObject;

import com.cloudprinter.dto.PrintedFilesInfo;
import com.cloudprinter.enums.PrintStatus;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class GetAllPrintRequest extends HttpServlet {

	@SuppressWarnings("unchecked")
	protected void doGet(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		JSONObject result = new JSONObject();
		if (req.getParameter("printingLocation").equals("")
				|| req.getParameter("printingLocation") == null) {
			result.put("status", "not-ok");
			result.put("printRequestList", null);
		} else {
			Objectify ob = ObjectifyRegisterService.registerService();
			Query<PrintedFilesInfo> printRequestList = (Query<PrintedFilesInfo>) ob
					.query(PrintedFilesInfo.class).filter("printingLocation",
							req.getParameter("printingLocation"));
			if (printRequestList.list().size() > 0) {
				JSONArray printRequestArray = new JSONArray();
				for (PrintedFilesInfo p : printRequestList) {
					if (p.getPrintStatus().equals(
							PrintStatus.SENT.getPrintStatus())) {
						JSONObject j = new JSONObject();
						j.put("dateOfPrintRequest", p.getDateOfPrintRequest());
						j.put("endPageNumber", p.getEndPageNumber());
						j.put("fileKey", p.getFileKey());
						j.put("fileName", p.getFileName());
						j.put("loginId", p.getLoginId());
						j.put("pageNumberCustomization",
								p.getPageNumberCustomization());
						j.put("pageSpecification", p.getPageSpecification());
						j.put("paperType", p.getPaperType());
						j.put("printCopies", p.getPrintCopies());
						j.put("printError", p.getPrintError());
						j.put("printingLocation", p.getPrintingLocation());
						j.put("printRequestId", p.getPrintRequestId());
						j.put("printStatus", p.getPrintStatus());
						j.put("startPageNumber", p.getStartPageNumber());
						printRequestArray.put(j);

						PrintedFilesInfo printFileNewStatus = p;
						printFileNewStatus.setPrintStatus(PrintStatus.INPROCESS
								.getPrintStatus());
						ob.put(printFileNewStatus);
					}

				}
				if (printRequestArray.length() > 0) {
					result.put("status", "ok");
					result.put("printRequestList", printRequestArray);
				} else {
					result.put("status", "not-ok");
					result.put("printRequestList", null);
				}
			} else {
				result.put("status", "not-ok");
				result.put("printRequestList", null);
			}
		}
		resp.setContentType("text/json");
		resp.getWriter().println(result.toJSONString());
	}

}
