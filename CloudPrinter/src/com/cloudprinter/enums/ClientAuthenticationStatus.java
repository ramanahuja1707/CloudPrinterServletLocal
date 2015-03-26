package com.cloudprinter.enums;

public enum ClientAuthenticationStatus {
	VERIFIED("verified"), UNVERIFIED("unverified");
	private String clientAuthenticationStatus;

	private ClientAuthenticationStatus(String clientAuthenticationStatus) {
		this.clientAuthenticationStatus = clientAuthenticationStatus;
	}

	public String getClientAuthenticationStatus() {
		return clientAuthenticationStatus;
	}
	
	
}
