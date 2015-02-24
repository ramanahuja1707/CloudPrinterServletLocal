package com.cloudprinter.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeletedFiles {

	@Id
	String loginId;
	String fileId;
	String fileDeletionStatus;

}
