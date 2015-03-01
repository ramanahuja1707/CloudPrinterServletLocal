

package com.cloudprinter.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PrintRequestResult {

	@Id
	private String loginId;
	private String printStatus;
	private String fileId;
	private String printReport;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getPrintReport() {
		return printReport;
	}
	public void setPrintReport(String printReport) {
		this.printReport = printReport;
	}
	
	

}
