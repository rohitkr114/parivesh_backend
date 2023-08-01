package com.backend.parivesh;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyFilter implements Filter {

	// private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if ("/".equals(request.getRequestURI()) || "/parivesh2_dev/".equals(request.getRequestURI())) {
			response.getOutputStream().write("".getBytes());
			return;
		}

		// call next filter in the filter chain
		filterChain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}
}