package com.cloudprinter.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Notification {

	@Id
	String loginId;
	String fileId;
	String filePrintStatus;
	String notificationStatus;
	String printingLocation;
	Date printingDateTime;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFilePrintStatus() {
		return filePrintStatus;
	}
	public void setFilePrintStatus(String filePrintStatus) {
		this.filePrintStatus = filePrintStatus;
	}
	public String getNotificationStatus() {
		return notificationStatus;
	}
	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}
	public String getPrintingLocation() {
		return printingLocation;
	}
	public void setPrintingLocation(String printingLocation) {
		this.printingLocation = printingLocation;
	}
	public Date getPrintingDateTime() {
		return printingDateTime;
	}
	public void setPrintingDateTime(Date printingDateTime) {
		this.printingDateTime = printingDateTime;
	}
	
	
}
