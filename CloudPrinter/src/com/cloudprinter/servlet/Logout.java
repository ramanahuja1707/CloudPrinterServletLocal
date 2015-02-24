package com.cloudprinter.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.removeAttribute("loginId");
			session.removeAttribute("password");
			session.removeAttribute("emailId");
			session = null;
			req.setAttribute("logoutStatus", "yes");
			RequestDispatcher rd = req.getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, resp);
		} else {
			RequestDispatcher rd = req
					.getRequestDispatcher("/notInSession.jsp");
			rd.forward(req, resp);
		}
	}

}
