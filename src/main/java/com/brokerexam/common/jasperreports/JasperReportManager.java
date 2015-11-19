package com.brokerexam.common.jasperreports;


public interface JasperReportManager {
	String getJasperreportsDir();
	void compileReport(String reportPath);
	ReportRenderer createReport(String reportPath, JasperReportData reportData) throws Exception;
}
