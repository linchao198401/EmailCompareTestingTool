package org.performance.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class SimpleMappingExceptionResolver extends
		org.springframework.web.servlet.handler.SimpleMappingExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		logger.error("SimpleMappingExceptionResolver resolve : ", ex);
		return super.resolveException(request, response, handler, ex);
	}

}