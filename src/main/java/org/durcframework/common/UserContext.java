package org.durcframework.common;

import org.durcframework.entity.IUser;

public enum UserContext {
	INSTANCE;

	private static String S_KEY_USER = "S_KEY_USER";

	public static UserContext getInstance() {
		return INSTANCE;
	}

	/**
	 * 获取用户
	 * @return
	 */
	public <T extends IUser> T getUser() {
		return (T) WebContext.getInstance().getSession()
				.getAttribute(S_KEY_USER);
	}

	/**
	 * 保存用户
	 * @param BackUser
	 */
	public <T extends IUser> void setUser(T t) {
		WebContext.getInstance().getSession()
				.setAttribute(S_KEY_USER, t);
	}

	public boolean isAdmin() {
		return "admin".equals(getUser().getUsername());
	}
}
