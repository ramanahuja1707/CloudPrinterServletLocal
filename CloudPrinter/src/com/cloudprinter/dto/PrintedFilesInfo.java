package com.cloudprinter.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PrintedFilesInfo {

	@Id
	String fileId;
	String loginId; // foreign key
	String printRequest;
	Date dateOfPrintRequest;
	String printCopies;
	String printStatus;
	String printError;
	String paperType;
	String paperSpecification;
	String printerType;
	String pageNumberCustomization;
	String pageLayout;
	String startPageNumber; // set if page customization is true
	String endPageNumber; // set if page customization is true

}
