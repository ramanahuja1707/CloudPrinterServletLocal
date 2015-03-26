package com.cloudprinter.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.dto.PrintingClientLoginInfo;
import com.cloudprinter.dto.UserLoginInfo;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

@SuppressWarnings("serial")
public class ClientRegistrationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (req.getParameter("clientLocation").equals("")
				|| req.getParameter("clientKey").equals("")
				|| req.getParameter("adminKey").equals("")) {
			req.setAttribute("clientRegistrationError",
					"Please Fill all fields...");
			RequestDispatcher rd = req
					.getRequestDispatcher("/clientRegistration.jsp");
			rd.forward(req, resp);

		} else {
			if (req.getParameter("adminKey").equals("raman@13")) {
				Objectify ob = ObjectifyRegisterService.registerService();
				Query<PrintingClientLoginInfo> clientList = (Query<PrintingClientLoginInfo>) ob
						.query(PrintingClientLoginInfo.class).filter(
								"clientLocation",
								req.getParameter("clientLocation"));
				if (clientList.list().size() == 0) {
					PrintingClientLoginInfo clientLogin = new PrintingClientLoginInfo();
					clientLogin.setClientKey(req.getParameter("clientKey"));
					clientLogin.setClientLocation(req
							.getParameter("clientLocation"));
					ob.put(clientLogin);
					req.setAttribute("clientRegistrationError",
							"Successfully Registered...:-)");
					RequestDispatcher rd = req
							.getRequestDispatcher("/clientRegistration.jsp");
					rd.forward(req, resp);
				} else {
					req.setAttribute("clientRegistrationError",
							"Already exists...");
					RequestDispatcher rd = req
							.getRequestDispatcher("/clientRegistration.jsp");
					rd.forward(req, resp);
				}
			} else {
				req.setAttribute("clientRegistrationError",
						"Invalid Admin Key...");
				RequestDispatcher rd = req
						.getRequestDispatcher("/clientRegistration.jsp");
				rd.forward(req, resp);

			}
		}
	}
}
