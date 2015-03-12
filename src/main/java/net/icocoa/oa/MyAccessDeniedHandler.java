package net.icocoa.oa;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
	private Logger logger = Logger.getLogger(this.getClass());

	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
		try {
			request.getSession().setAttribute("message", "你没有权限访问这个页面!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/denied");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}