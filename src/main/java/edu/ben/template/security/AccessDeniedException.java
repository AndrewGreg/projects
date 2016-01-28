package edu.ben.template.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AccessDeniedException implements AccessDeniedHandler {
	private String accessDeniedUrl;

	public AccessDeniedException() {
	}

	public AccessDeniedException(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.access.AccessDeniedException arg2) throws IOException, ServletException {
		response.sendRedirect(accessDeniedUrl);
		request.getSession().setAttribute("message", "You do not have permission to access this page!");

	}
}