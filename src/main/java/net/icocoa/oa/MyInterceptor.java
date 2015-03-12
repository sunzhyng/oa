package net.icocoa.oa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		try {
			super.afterCompletion(request, response, handler, ex);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		try {
			logger.debug(request.getContentType() + "-----" + request.getCharacterEncoding() + "------" + request.getContextPath());
			if (modelAndView != null)
				logger.debug("MyInterceptor.postHandle()---viewName:" + modelAndView.getViewName());
			super.postHandle(request, response, handler, modelAndView);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			String url = request.getRequestURI();
			logger.debug("MyInterceptor.preHandle()" + url);
			return super.preHandle(request, response, handler);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
}
