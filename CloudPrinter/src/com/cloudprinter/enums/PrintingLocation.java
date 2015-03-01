package com.cloudprinter.enums;

public enum PrintingLocation {
	DELHI("delhi"), MUMBAI("mumbai"), HYDERABAD("hyderabad"), CHENNAI("chennai"), BANGLORE(
			"banglore");
	private String printingLocation;

	private PrintingLocation(String printingLocation) {
		this.printingLocation = printingLocation;
	}

	public String getPrintingLocation() {
		return printingLocation;
	}

}
