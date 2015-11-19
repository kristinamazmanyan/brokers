package com.brokerexam.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CustomCharacterEncodingFilter implements Filter {
	private static final String UTF8 = "UTF-8";

	public void init(FilterConfig config) throws ServletException {
		// No-op
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(UTF8);
		response.setCharacterEncoding(UTF8);
		chain.doFilter(request, response);
	}

	public void destroy() {
		// No-op
	}

}
