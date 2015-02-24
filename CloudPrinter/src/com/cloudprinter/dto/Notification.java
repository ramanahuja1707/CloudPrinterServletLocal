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
}
