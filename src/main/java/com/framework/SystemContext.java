package com.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SystemContext {
	private static ThreadLocal<HttpServletRequest> webRequest = new ThreadLocal<HttpServletRequest>(); 

	 

	public static HttpSession getWebSession() {
		if(webRequest.get()==null){
			return null;
		}
	HttpSession session = (HttpSession) ((webRequest.get()).getSession());

	return session;

	}

	public static HttpServletRequest getWebRequest() {

	return webRequest.get();

	}

	public static void setWebRequest(HttpServletRequest session) {

		webRequest.set(session);

	}

}
