package com.cloudprinter.enums;

public enum DeletedFileStatus {
	TEMPORARY("temporary"), PERMANENT("permanent");
	private String deletedfileStatus;

	private DeletedFileStatus(String deletedFileStatus) {
		this.deletedfileStatus = deletedFileStatus;
	}

	public String getDeletedFileStatus() {
		return deletedfileStatus;
	}

}
