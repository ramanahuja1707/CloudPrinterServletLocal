package com.cloudprinter.enums;

public enum PageNumberCustomization {
	TRUE("true"), FALSE("false");
	private  String pagenumberCustomization;

	private PageNumberCustomization(String PageNumberCustomization) {
		this.pagenumberCustomization = PageNumberCustomization;
	}

	public String getPageNumberCustomization() {
		return pagenumberCustomization;
	}
	

}
