package com.cloudprinter.enums;

public enum PaperType {
	LETTER("letter"), A4("A4"),A5("A5"),LEGAL("legal"),EXECUTIVE("executive"),ENVELOPE10("envelope10"),A6("A6");
	private String paperType;

	private PaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getPaperType() {
		return paperType;
	}


}
