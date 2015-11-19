package com.brokerexam.common.jasperreports;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class JasperReportManagerImpl implements JasperReportManager,
		InitializingBean {
	private static final Logger LOG = LoggerFactory
			.getLogger(JasperReportManagerImpl.class);

	private String reportFontName;
	private String reportFontPath;
	private String reportFontDir;
	private String virtualizerDirectory;
	private String jasperreportsDir;

	@Override
	public void compileReport(String reportPath) {
		try {
			String fullPath = jasperreportsDir + reportPath;
			JasperCompileManager.compileReportToFile(fullPath);
		} catch (JRException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void render(ReportType reportType, OutputStream outputStream) {
	}

	public void render(ReportType reportType, File file) {
	}

	@Override
	public ReportRenderer createReport(String reportPath,
			JasperReportData reportData) throws Exception {
		try {
			String fullPath = jasperreportsDir + reportPath;
			FileInputStream fis = new FileInputStream(new File(fullPath));
			JasperReport report = (JasperReport) JRLoader.loadObject(fis);

			return new JasperReportRendererImpl(reportData, report,
					virtualizerDirectory);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new Exception("Error creating report " + reportPath);
		}
	}

	public void setJasperreportsDir(String jasperreportsDir) {
		this.jasperreportsDir = jasperreportsDir;
	}

	public void setVirtualizerDirectory(String virtualizerDirectory) {
		this.virtualizerDirectory = virtualizerDirectory;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		JRProperties.setProperty("net.sf.jasperreports.export.xls.max.rows.per.sheet",
				"65500");

		JRProperties.setProperty("net.sf.jasperreports.default.font.name",
				reportFontName);
		
		JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding",
				"UTF-8");		
		JRProperties.setProperty("net.sf.jasperreports.export.pdf.fontdir.jasper",
				reportFontDir);
		JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name",
				reportFontName);
		
		JRProperties.setProperty(JRProperties.PDF_FONT_FILES_PREFIX
				+ reportFontName, reportFontPath);
		JRProperties.setProperty(JRProperties.PDF_FONT_FILES_PREFIX
				+ reportFontName + ".Bold", reportFontPath);
	}

	public void setReportFontName(String reportFontName) {
		this.reportFontName = reportFontName;
	}

	public void setReportFontPath(String reportFontPath) {
		this.reportFontPath = reportFontPath;
	}

	public void setReportFontDir(String reportFontDir) {
		this.reportFontDir = reportFontDir;
	}

	public String getJasperreportsDir() {
		return jasperreportsDir;
	}	
}
