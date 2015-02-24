package com.cloudprinter.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserPrintResult {

	@Id
	String loginId;
	String printStatus;
	String fileId;
	String printReport;

}
