package com.cloudprinter.enums;

public enum PrintStatus {
	PRINTED("printed"), INPROCESS("in-process"), PRINTFAILURE("print-failure");
	private String printStatus;

	private PrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	public String getPrintStatus() {
		return printStatus;
	}

}
