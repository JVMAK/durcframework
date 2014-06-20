package org.durcframework.common;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * request对象监听器,方便在其它地方使用request对象,<br>
 * 使用方法<code>WebContext.get().getRequest();</code>
 * 
 * 在web.xml中添加:<br>
 * <code>
 * {@literal
 * <listener>     
        <listener-class>org.durcframework.common.RequestContextListener</listener-class>     
	</listener>  
	}
 * </code>	
 * </pre>
 * @author hc.tang
 * 2013-5-6
 */
public class RequestContextListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		WebContext.get().resetRquest();
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		WebContext.get().setRequest(request);
	}

}