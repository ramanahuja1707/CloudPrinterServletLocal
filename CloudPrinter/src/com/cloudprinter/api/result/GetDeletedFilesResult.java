package com.cloudprinter.api.result;

import java.util.ArrayList;

import javax.persistence.Entity;

import com.cloudprinter.dto.DeletedFiles;

@Entity
public class GetDeletedFilesResult {

	String status;
	ArrayList<DeletedFiles> deletedFiles = new ArrayList<>();
	int filesCount;
	Errors errors;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<DeletedFiles> getDeletedFiles() {
		return deletedFiles;
	}

	public void setDeletedFiles(ArrayList<DeletedFiles> deletedFiles) {
		this.deletedFiles = deletedFiles;
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
