package com.brokerexam.common.jasperreports;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JasperReportRendererImpl implements ReportRenderer {
	private static final Logger LOG = LoggerFactory
			.getLogger(JasperReportRendererImpl.class);

	private JasperReportData reportData = null;
	private JasperReport report = null;
	private JRVirtualizer virtualizer = null;
	private JasperPrint print = null;

	public JasperReportRendererImpl(JasperReportData reportData,
			JasperReport report, String virtualizerDirectory) {
		super();
		this.reportData = reportData;
		this.report = report;
		this.virtualizer = new JRFileVirtualizer(5, virtualizerDirectory);
	}

	@Override
	public void render(ReportType reportType, File file) throws Exception {
		JRVirtualizer virtualizer = createVirtualizer();
		JasperPrint print = createPrint();

		JRAbstractExporter exporter = createExporter(reportType, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
		export(exporter);

		virtualizer.cleanup();
	}

	@Override
	public void render(ReportType reportType, OutputStream outputStream)
			throws Exception {
		JasperPrint print = createPrint();

		JRAbstractExporter exporter = createExporter(reportType, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		export(exporter);
	}

	private static JRAbstractExporter createExporter(ReportType reportType,
			JasperPrint print) throws Exception {
		JRAbstractExporter exporter = null;

		if (ReportType.PDF.equals(reportType)) {
			exporter = createPdfExporter(print);
		} else if (ReportType.XLS.equals(reportType)) {
			exporter = createXlsExporter(print);
		} else if (ReportType.RTF.equals(reportType)) {
			exporter = createRtfExporter(print);
		} else if (ReportType.CSV.equals(reportType)) {
			exporter = createCsvExporter(print);
		} else if (ReportType.DOCX.equals(reportType)) {
			exporter = createDocxExporter(print);
		}
		return exporter;
	}

	private JasperPrint createPrint() throws Exception {
		if (print == null) {
			try {
				Map<String, Object> params = reportData.getParameters();
				if (virtualizer != null) {
					params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
				}

				print = JasperFillManager.fillReport(report, params,
						reportData.getData());
			} catch (JRException ex) {
				LOG.error(ex.getMessage(), ex);
				throw new Exception(ex.getMessage());
			}
		}
		return print;
	}

	private static void export(JRAbstractExporter exporter) throws Exception {
		try {
			exporter.exportReport();
		} catch (JRException ex) {
			LOG.error(ex.getMessage(), ex);
			throw new Exception(ex);
		}
	}

	private static JRAbstractExporter createPdfExporter(JasperPrint print) {
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		exporter.setParameter(JRPdfExporterParameter.FORCE_LINEBREAK_POLICY,
				new Boolean(true));
		return exporter;
	}

	private static JRAbstractExporter createXlsExporter(JasperPrint print) {
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		exporter.setParameter(
				JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, true);

		exporter.setParameter(
				JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporter.setParameter(
				JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		return exporter;
	}

	private static JRAbstractExporter createRtfExporter(JasperPrint print) {
		JRRtfExporter exporter = new JRRtfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		return exporter;
	}

	private static JRAbstractExporter createCsvExporter(JasperPrint print) {
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, "\t");
		exporter.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, "\n");
		return exporter;
	}
	
	private static JRAbstractExporter createDocxExporter(JasperPrint print) {
		JRDocxExporter exporter = new JRDocxExporter();		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);		
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		return exporter;
	}

	private JRVirtualizer createVirtualizer() {
		if (virtualizer == null) {
			virtualizer = new JRFileVirtualizer(10);
		}
		return virtualizer;
	}
}
