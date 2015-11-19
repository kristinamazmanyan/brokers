package com.brokerexam.common.jasperreports;

import java.io.File;
import java.io.OutputStream;

public interface ReportRenderer {
	void render(ReportType reportType, File file) throws Exception;
	void render(ReportType reportType, OutputStream outputStream) throws Exception;
}
