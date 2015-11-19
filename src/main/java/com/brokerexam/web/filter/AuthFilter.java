package com.brokerexam.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.brokerexam.common.security.UserDetails;

/**
 * Servlet Filter implementation class that checks if user is logged in, and if
 * not redirects him to login page.
 */
@WebFilter(value = "/pages/*", displayName = "AuthFilter", filterName = "AuthFilter")
public class AuthFilter implements Filter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (isLoggedIn((HttpServletRequest) request)) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response)
					.sendRedirect(((HttpServletRequest) request)
							.getContextPath());
		}
	}

	/**
	 * Returns <code>true</code> if user is logged in, otherwise false.
	 * 
	 * @param request
	 *            The {@link HttpServletRequest}.
	 * @return <code>true</code> if user is logged in, otherwise false.
	 */
	private boolean isLoggedIn(HttpServletRequest request) {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());

		if (context != null && context.containsBean("userDetails")) {
			UserDetails userDetails = context.getBean("userDetails",
					UserDetails.class);
			if (userDetails.getId() != 0) {
				return true;
			}
		}
		return false;
	}

}
