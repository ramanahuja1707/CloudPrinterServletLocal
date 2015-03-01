package com.cloudprinter.enums;

public enum PageNumberCustomization {
	TRUE("true"), FALSE("false");
	private  String pagenumberCustomization;

	private PageNumberCustomization(String PageNumberCustomization) {
		this.pagenumberCustomization = pagenumberCustomization;
	}

	public String getPageNumberCustomization() {
		return pagenumberCustomization;
	}
	

}
