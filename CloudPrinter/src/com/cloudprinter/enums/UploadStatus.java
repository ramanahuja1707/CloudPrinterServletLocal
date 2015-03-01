package com.cloudprinter.enums;

public enum UploadStatus {

	UPLOADED("uploaded"), NOTUPLOADED("not_uploaded"), DELETED("deleted");

	private String status;

	private UploadStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	
}
