package com.cloudprinter.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cloudprinter.enums.FileType;
import com.cloudprinter.enums.UploadStatus;

@Entity
public class UploadedFilesInfo {

	String fileKey;
	@Id
	String fileId; // filename+date
	String loginId; // foreign key
	String fileName;
	String fileSize;
	@Temporal(TemporalType.DATE)
	Date dateOfUploading;
	String ipAddressOfHost;
	UploadStatus uploadStatus;
	String uploadError;
	FileType fileType;
	

	private String contentType;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

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

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Date getDateOfUploading() {
		return dateOfUploading;
	}

	public void setDateOfUploading(Date dateOfUploading) {
		this.dateOfUploading = dateOfUploading;
	}

	public String getIpAddressOfHost() {
		return ipAddressOfHost;
	}

	public void setIpAddressOfHost(String ipAddressOfHost) {
		this.ipAddressOfHost = ipAddressOfHost;
	}

	public UploadStatus getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(UploadStatus uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public String getUploadError() {
		return uploadError;
	}

	public void setUploadError(String uploadError) {
		this.uploadError = uploadError;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

}
