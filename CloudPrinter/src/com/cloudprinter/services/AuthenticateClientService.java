package com.cloudprinter.services;

import com.cloudprinter.dto.PrintingClientLoginInfo;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class AuthenticateClientService {

	private String clientExistenceStatus = "not-exist";

	// private String clientLoginStatus = "not_exist";

	public String authenticateClient(String clientLocation, String clientKey) {

		Objectify ob = ObjectifyRegisterService.registerService();
		Query<PrintingClientLoginInfo> clientList = (Query<PrintingClientLoginInfo>) ob
				.query(PrintingClientLoginInfo.class).filter("clientLocation",
						clientLocation);
		if (clientList.list().size() > 0) {
			for (PrintingClientLoginInfo u : clientList) {
				if (u.getClientKey().matches(clientKey)) {
					clientExistenceStatus = "exist";
					break;
				}
			}
			return clientExistenceStatus;
		} else {
			return clientExistenceStatus;
		}

	}

}
