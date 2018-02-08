// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package com.yztag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.fs.comm.model.Role;
import com.fs.comm.model.Sysuser;

public class YzActButton extends SimpleTagSupport {
	public static final Logger log = Logger.getLogger(YzActButton.class);

	protected String onclick;
	protected String ioc;
	protected String title;
	protected String power;
	protected String event;

	public YzActButton() {
	}

	public void doTag() throws JspException, IOException {
		StringBuffer sb = new StringBuffer();
		HttpServletRequest request = (HttpServletRequest) ((PageContext) getJspContext()).getRequest();
		String basepath = request.getContextPath();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		Role role = sysuser.getSysrole();
		if (sysuser.getPrivilege().equals("1")) {
			sb.append("{onclick:\"" + onclick + "\",");
			if (ioc != null && !ioc.equals("")) {
				sb.append("ioc:\"<img src='" + basepath + "/images/admin/ioc" + ioc + "' border='0'>\",");
			}
			sb.append("title:'" + title + "'");
			if (event != null && !event.equals("")) {
				sb.append(",event:" + event);
			}
			sb.append("},");
		} else {
			String menuid = role.getMenuid();
			String[] menuids = menuid.split("\\|");
			for (String mid : menuids) {
				String m = mid.substring(0, mid.indexOf("("));
				//该页面是否包含增删改查权限
				if (power == null || power.equals(m)) {
					sb.append("{onclick:\"" + onclick + "\",");
					if (ioc != null && !ioc.equals("")) {
						sb.append("ioc:\"<img src='" + basepath + "/images/admin/ioc" + ioc + "' border='0'>\",");
					}
					sb.append("title:'" + title + "'");
					if (event != null && !event.equals("")) {
						sb.append(",event:" + event);
					}
					sb.append("},");
					break;
				}
			}
		}
		getJspContext().getOut().println(sb);
	}

	public void setPower(String power) {
		this.power = power;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public void setIoc(String ioc) {
		this.ioc = ioc;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
