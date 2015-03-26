package com.cloudprinter.api.result;

import java.util.ArrayList;

import javax.persistence.Entity;

import com.cloudprinter.dto.UploadedFilesInfo;

@Entity
public class GetMyFilesResult {

	String status;
	ArrayList<UploadedFilesInfo> myFiles = new ArrayList<>();
	int filesCount;
	Errors errors;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<UploadedFilesInfo> getMyFiles() {
		return myFiles;
	}
	public void setMyFiles(ArrayList<UploadedFilesInfo> myFiles) {
		this.myFiles = myFiles;
	}
	public int getFilesCount() {
		return filesCount;
	}
	public void setFilesCount(int filesCount) {
		this.filesCount = filesCount;
	}
	public Errors getErrors() {
		return errors;
	}
	public void setErrors(Errors errors) {
		this.errors = errors;
	}
	
	

}
