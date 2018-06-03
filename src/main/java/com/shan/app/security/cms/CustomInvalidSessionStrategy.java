package com.shan.app.security.cms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {
	
	private final Logger logger = LoggerFactory.getLogger(CustomInvalidSessionStrategy.class);
	private final String destinationUrl;
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private final String AJAX_HEADER = "AJAX";

	public CustomInvalidSessionStrategy(String invalidSessionUrl) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");

		this.destinationUrl = invalidSessionUrl;
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		logger.debug("Starting new session (if required) and redirecting to '" + this.destinationUrl + "'");
		
		//Ajax인 경우
		if(request.getHeader(AJAX_HEADER) != null && request.getHeader(AJAX_HEADER).equals(Boolean.TRUE.toString())) {
			logger.debug("session expired during ajax processing");
			
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		} 
		
		this.redirectStrategy.sendRedirect(request, response, this.destinationUrl);
	}

}
