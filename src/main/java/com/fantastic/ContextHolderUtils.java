package com.fantastic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fs.comm.model.Sysuser;

/**
 * @ClassName: ContextHolderUtils
 * @Description: TODO(上下文工具类)
 * @author pzj
 * @date 2017-06-12
 *
 */
public final class ContextHolderUtils {

	public static ThreadLocal<HttpServletRequest> localrequest = new ThreadLocal<HttpServletRequest>();
	private static ContextHolderUtils instance;

	public static ContextHolderUtils shareInstance() {
		if (instance == null) {
			instance = new ContextHolderUtils();
		}
		return instance;
	}

	public void setRequest(HttpServletRequest request) {
		localrequest.set(request);
	}

	/**
	 * SpringMvc下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		if (RequestContextHolder.getRequestAttributes() == null) {
			return localrequest.get();
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;

	}

	/**
	 * SpringMvc下获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

	/**
	 * 获取当前登录用户信息
	 * 
	 * @return
	 */
	public static Sysuser getLoginUser() {
//		HttpSession session = ContextHolderUtils.getSession();
//		Client client = ClientManager.getInstance().getClient(session.getId());
//		Sysuser user = client.getUser(getRequest());
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser user=(Sysuser)session.getAttribute("currentUser");
		return user == null ? null : user;
	}

	/**
	 * 设置当前登录用户信息
	 */
	public static void setLoginUser(Sysuser user) {
//		HttpSession session = ContextHolderUtils.getSession();
//		Client client = ClientManager.getInstance().getClient(session.getId());
//		client.setUser(user);
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute("currentUser", user);
	}

	// public static String getSessionRoleType2() {
	// HttpSession session = ContextHolderUtils.getSession();
	// Client client = ClientManager.getInstance().getClient(session.getId());
	// String role = client.getUser() == null ? null :
	// client.getUser().getRoleType();
	// if (role == null) {
	// return null;
	// }
	// if ("超级管理员".equals(role)) {
	// return "admin";
	// }
	// if ("管理员".equals(role) || "项目经理".equals(role)|| "经理".equals(role)||
	// "老板".equals(role)|| "高管".equals(role)
	// || "财务".equals(role) || "出纳".equals(role)|| "会计".equals(role)) {
	// return "manager";
	// }
	// return "employee";
	// }

}
