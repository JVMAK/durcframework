package org.durcframework.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public enum WebContext {
	INSTANCE;
	
	private ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	
	public static WebContext getInstance() {
		return INSTANCE;
	}
	
	public void setRequest(HttpServletRequest req) {
		request.set(req);
	}
	
	public HttpServletRequest getRequest() {
		return request.get();
	}
	
	public void resetRquest() {
		request.remove();
	}
	
	public HttpSession getSession() {
		if(getRequest() != null) {
			return getRequest().getSession();
		}
		return null;
	}
	
}
