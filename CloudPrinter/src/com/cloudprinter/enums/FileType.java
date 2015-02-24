package com.cloudprinter.enums;

public enum FileType {

	USERUPLOADED("user_uploaded"), USERCREATED("user_created");
	private String fileType;

	private FileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileType() {
		return fileType;
	}

}
