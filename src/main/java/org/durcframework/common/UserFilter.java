package org.durcframework.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFilter implements Filter{
	
	protected static List<String> NEED_NOT_LOGGIN = new ArrayList<String>();
	
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		WebContext.getInstance().setRequest(request);
		
		if (isLogin(request)) {
			arg2.doFilter(arg0, arg1);
		} else {
			response.sendRedirect(request.getContextPath() + "/" + getNeedLoginPage());
		}
	}
	
	/**
	 * 用户登陆页面
	 * @return
	 */
	protected String getNeedLoginPage() {
		return "needLogin.html";
	}
	
	/**
	 * 用户是否登录,如果没有登录则被拦截,跳转到getNeedLoginPage()页面
	 * @param request
	 * @return true,已登录
	 */
	protected boolean isLogin(HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		uri = uri.substring(uri.lastIndexOf("/") + 1);

		boolean isNeedNotLoginUrl = NEED_NOT_LOGGIN.contains(uri);

		if (isNeedNotLoginUrl) {
			return true;
		}
		
		return UserContext.getInstance().getUser() != null;
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		NEED_NOT_LOGGIN.add("");
		NEED_NOT_LOGGIN.add("index.jsp");
		NEED_NOT_LOGGIN.add("login.jsp");
		NEED_NOT_LOGGIN.add("login.do");
		NEED_NOT_LOGGIN.add(getNeedLoginPage());
	}
}
