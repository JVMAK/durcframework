package org.durcframework.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 设置request,response对象.方便在其它地方调用
 * 每次请求都会设置一下<br>
 * 详见:org.egula.framework.servlet.DispatcherServlet.service(HttpServletRequest, HttpServletResponse)
 * 使用方法:<br>
 * WebContext.get().getRequest();<br>
 * @author hc.tang
 * 2013-4-11
 */
public enum WebContext {
	INSTANCE;
	
	private ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	
	public static WebContext get() {
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
