package com.cloudprinter.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PrintedFilesInfo {

	@Id
	private String printRequestId; // fileKey+"@"+date of printing
	private String fileKey;
	private String fileName;
	private String loginId; // foreign key
	private Date dateOfPrintRequest;
	private String printCopies;
	private String printStatus;
	private String printError;
	private String paperType;
	private String pageSpecification;
	private String pageNumberCustomization;
	private String startPageNumber; // set if page customization is true
	private String endPageNumber; // set if page customization is true
	private String printingLocation;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPrintingLocation() {
		return printingLocation;
	}

	public void setPrintingLocation(String printingLocation) {
		this.printingLocation = printingLocation;
	}

	public String getPrintRequestId() {
		return printRequestId;
	}

	public void setPrintRequestId(String printRequestId) {
		this.printRequestId = printRequestId;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Date getDateOfPrintRequest() {
		return dateOfPrintRequest;
	}

	public void setDateOfPrintRequest(Date dateOfPrintRequest) {
		this.dateOfPrintRequest = dateOfPrintRequest;
	}

	public String getPrintCopies() {
		return printCopies;
	}

	public void setPrintCopies(String printCopies) {
		this.printCopies = printCopies;
	}

	public String getPrintStatus() {
		return printStatus;
	}

	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	public String getPrintError() {
		return printError;
	}

	public void setPrintError(String printError) {
		this.printError = printError;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getPageSpecification() {
		return pageSpecification;
	}

	public void setPageSpecification(String pageSpecification) {
		this.pageSpecification = pageSpecification;
	}

	public String getPageNumberCustomization() {
		return pageNumberCustomization;
	}

	public void setPageNumberCustomization(String pageNumberCustomization) {
		this.pageNumberCustomization = pageNumberCustomization;
	}

	public String getStartPageNumber() {
		return startPageNumber;
	}

	public void setStartPageNumber(String startPageNumber) {
		this.startPageNumber = startPageNumber;
	}

	public String getEndPageNumber() {
		return endPageNumber;
	}

	public void setEndPageNumber(String endPageNumber) {
		this.endPageNumber = endPageNumber;
	}

}
