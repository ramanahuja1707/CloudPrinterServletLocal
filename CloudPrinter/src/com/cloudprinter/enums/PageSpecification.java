package com.cloudprinter.enums;

public enum PageSpecification {
  COLOURED("coloured"),BLACKWHITE("blackwhite");
  private String pageSpecification;

	private PageSpecification(String pageSpecification) {
		this.pageSpecification = pageSpecification;
	}

	public String getPageSpecification() {
		return pageSpecification;
	}
	
	
}
