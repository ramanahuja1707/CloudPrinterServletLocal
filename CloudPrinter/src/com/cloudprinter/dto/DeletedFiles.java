package com.cloudprinter.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeletedFiles {

	private String loginId;
	private String fileName;
	@Id
	private String fileId;
	private String fileDeletionStatus;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileDeletionStatus() {
		return fileDeletionStatus;
	}

	public void setFileDeletionStatus(String fileDeletionStatus) {
		this.fileDeletionStatus = fileDeletionStatus;
	}

}
