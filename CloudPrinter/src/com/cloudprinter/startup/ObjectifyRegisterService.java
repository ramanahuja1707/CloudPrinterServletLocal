package com.cloudprinter.startup;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.dto.UserInfo;
import com.cloudprinter.dto.UserLoginInfo;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class ObjectifyRegisterService extends HttpServlet {
	private static final Logger log = Logger
			.getLogger(ObjectifyRegisterService.class.getName());
	static {
		log.info("objectify service started");
		ObjectifyService.register(UserInfo.class);
		ObjectifyService.register(UserLoginInfo.class);
		ObjectifyService.register(UploadedFilesInfo.class);
	}

	public static Objectify registerService() {
		return ObjectifyService.begin();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/loginPage.jsp");
		rd.forward(req, resp);
	}

}
