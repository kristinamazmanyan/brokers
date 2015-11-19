package com.brokerexam.common.jasperreports;

public enum ReportType {
	PDF("pdf", "PDF", "PDF"), XLS("xls", "XLS", "Excel"), 
	CSV("csv", "CSV", "CSV"), RTF("rtf", "RTF", "RTF")
	, DOCX("docx", "DOCX", "MS Word");

	private String extension;
	private String value;
	private String label;

	private ReportType(String extension, String value, String label) {
		this.extension = extension;
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
}
