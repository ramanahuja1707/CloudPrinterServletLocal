package com.cloudprinter.api.result;

import java.util.ArrayList;

import javax.persistence.Entity;

import com.cloudprinter.dto.PrintedFilesInfo;

@Entity
public class GetPrintedFilesResult {
	String status;
	ArrayList<PrintedFilesInfo> printedFiles = new ArrayList<>();
	int filesCount;
	Errors errors;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<PrintedFilesInfo> getPrintedFiles() {
		return printedFiles;
	}

	public void setPrintedFiles(ArrayList<PrintedFilesInfo> printedFiles) {
		this.printedFiles = printedFiles;
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
