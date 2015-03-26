package com.cloudprinter.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.cloudprinter.enums.ClientAuthenticationStatus;
import com.cloudprinter.services.AuthenticateClientService;

@SuppressWarnings("serial")
public class AuthenticateClient extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/json");
		String clientLocation = req.getParameter("clientLocation");
		String clientKey = req.getParameter("clientKey");
		JSONObject result = new JSONObject();
		try {
			if (clientLocation.equals("") || clientKey.equals("")) {
				result.put("clientAuthenticationStatus",
						ClientAuthenticationStatus.UNVERIFIED
								.getClientAuthenticationStatus());
				if (clientKey.equals(""))
					result.put("authenticationError", "client-key-null");
				else
					result.put("authenticationError", "client-location-null");
				result.put("clientExistenceStatus", "not-exist");
			} else {
				AuthenticateClientService as = new AuthenticateClientService();
				String clientExistenceStatus = as.authenticateClient(
						clientLocation, clientKey);
				if (clientExistenceStatus.equals("exist")) {
					result.put("clientAuthenticationStatus",
							ClientAuthenticationStatus.VERIFIED
									.getClientAuthenticationStatus());
					result.put("authenticationError", "no-error");
					result.put("clientExistenceStatus", "exist");
				} else {
					result.put("clientAuthenticationStatus",
							ClientAuthenticationStatus.VERIFIED
									.getClientAuthenticationStatus());
					result.put("authenticationError", "no-error");
					result.put("clientExistenceStatus", "not-exist");
				}
			}
			resp.getWriter().println(result.toJSONString());
		} catch (Exception e) {
			result.put("clientAuthenticationStatus",
					ClientAuthenticationStatus.UNVERIFIED
							.getClientAuthenticationStatus());
			result.put("authenticationError", e);
			result.put("clientExistenceStatus", "not-exist");
			resp.getWriter().println(result.toJSONString());
		}
	}
}
