package com.nab.ms.test.application.filter;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.nab.ms.test.application.common.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Filter to limit the API calls per hour using Googles Guava library 
 * 
 * @author praveendas
 *
 */
@Slf4j
@Component
public class ApiKeyRequestThrottle implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiKeyRequestThrottle.class);

	@Value("${rate.limit.hourly.limit}")
	private int MAX_REQUESTS_PER_HOUR;

	private LoadingCache<String, Integer> requestCountsApiKey;

	public ApiKeyRequestThrottle() {
		super();
		requestCountsApiKey = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		String apiKey = getClientApiKey((HttpServletRequest) servletRequest);
		
		logger.info("client apiKey - {}",apiKey);
		
		if (isMaximumRequestsPerHourExceeded(apiKey)) {
			
			logger.info("Maximum requests exceeded for Client API KEY - {} ",apiKey);
			
			httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
			httpServletResponse.getWriter().write(AppConstants.API_THROTTLE_ERROR_MESSAGE + MAX_REQUESTS_PER_HOUR);
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	/**
	 * Validates the no.of requests received for the apiKey
	 * 
	 * @param clientapiKey
	 * 
	 * @return true when rate exceeded
	 */
	private boolean isMaximumRequestsPerHourExceeded(String clientapiKey) {
		int requests = 0;
		try {
			requests = requestCountsApiKey.get(clientapiKey);
			if (requests == MAX_REQUESTS_PER_HOUR) {
				requestCountsApiKey.put(clientapiKey, requests);
				return true;
			}
		} catch (ExecutionException e) {
			requests = 0;
		}
		requests++;
		requestCountsApiKey.put(clientapiKey, requests);
		return false;
	}

	/**
	 * Get the clients Api Key from the request param
	 * 
	 * @param request
	 * 
	 * @return String API KEY
	 */
	public String getClientApiKey(HttpServletRequest request) {
		String apiKey = request.getParameter("apiKey");
		return apiKey;
	}

	@Override
	public void destroy() {

	}

}
