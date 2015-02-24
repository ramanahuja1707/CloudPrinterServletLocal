package com.cloudprinter.enums;

public enum UploadStatus {

	UPLOADED("uploaded"),
    NOTUPLOADED("not_uploaded");
    
    
	private String status;
	private UploadStatus(String status)
	{
		
	}
	
	public void getStatus(String status) {
		this.status = status;
	}
	
}
