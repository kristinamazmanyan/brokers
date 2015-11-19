package com.brokerexam.common.jasperreports;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

public class JasperReportData implements Serializable {

	private static final long serialVersionUID = 1756658318001270790L;
	
	private Map<String, Object> parameters = new HashMap<String, Object>();

	private JRDataSource data = null;
	
	public JasperReportData() {
		super();
		this.data = new JREmptyDataSource();
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameter(String key, Object value) {
		this.parameters.put(key, value);
	}

	public JRDataSource getData() {
		return data;
	}

	public void setData(JRDataSource data) {
		this.data = data;
	}
}
