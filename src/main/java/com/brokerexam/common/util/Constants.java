package com.brokerexam.common.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Double CURRENT_TOTAL_DEBT_FROM = 0d;
    public static final Double CURRENT_TOTAL_DEBT_TO = 60000d;
    public static final String EQ = "=";
    public static final String PATH = "path";
    public static final String SLASH = "/";



	public static final String ZERO = "0";
	public static final String ZERO_ = "0.0";

	public static final String SESSION_USER_DETAILS = "userDetails";
	public static final String SESSION_LANGUAGE = "language";
	public static final String SESSION_ARMENIAN = "hy";
	public static final String SESSION_ENGLISH = "en";
	public static final String SESSION_LANGUAGE_CODE = "langCode";
	public static final String SESSION_LOCALE = "locale";

	public static final int PAGE_SIZE = 10;
	public static final int PAGING_VISIBLE_PAGE_COUNT = 4;
	public static final int PAGE_AFTER_SHOW = 3;
	public static final int QUERY_GET_COUNT = -1;
	public static final String PAGING_SELECTED_PAGE_STYLE = "color:red; font-weight:bold;";
	public static final String PAGING_NO_SELECTED_PAGE_STYLE = "font-weight:bold;";

	public final static int SORT_BY_EVENT_TYPE = 1;
	public final static int SORT_BY_MESSAGE = 3;
	public final static int SORT_BY_PERFORMED_BY = 5;
	public final static int SORT_BY_PERFORMED_ON = 7;
	public final static int SORT_BY_CREATED_AT = 9;


	
	public static final Map<String, Integer> TABLES_COLUMNS_SORT_PARAMS_MAP = new HashMap<String, Integer>();

	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATE_TIME_FORMAT = "dd/MM/yy HH:mm";

	static {
		TABLES_COLUMNS_SORT_PARAMS_MAP.put("eventTypeColumn",
				SORT_BY_EVENT_TYPE);
		TABLES_COLUMNS_SORT_PARAMS_MAP.put("messageColumn", SORT_BY_MESSAGE);
		TABLES_COLUMNS_SORT_PARAMS_MAP.put("performedByColumn",
				SORT_BY_PERFORMED_BY);
		TABLES_COLUMNS_SORT_PARAMS_MAP.put("performedOnColumn",
				SORT_BY_PERFORMED_ON);
		TABLES_COLUMNS_SORT_PARAMS_MAP.put("createdAtColumn",
				SORT_BY_CREATED_AT);



	}
	
     public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.00");
}
