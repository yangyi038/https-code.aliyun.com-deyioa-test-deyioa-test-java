package com.yztag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.fs.comm.model.Role;
import com.fs.comm.model.Sysuser;

public class YzButton extends SimpleTagSupport {

	public static final Logger log = Logger.getLogger(YzButton.class);

	protected String id;
	protected String href;
	protected String name;
	protected String power;
	protected String cssClass;

	public YzButton() {
	}

	public void doTag() throws JspException, IOException {
		StringBuffer sb = new StringBuffer();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		Role role = sysuser.getSysrole();
		if (sysuser.getLoginname().equals("admin")) {
			sb.append("<button class=\"" + cssClass + "\" "
					+ (href == null || href.equals("") ? "" : "onclick=\"" + href + "\"")
					+ "  data-toggle=\"modal\" id=\"" + id + "\" >" + name + "</button>");
		} else {
			String menuid = role.getMenuid();
			String[] menuids = menuid.split("\\|");
			for (String mid : menuids) {
				String m = mid.substring(0, mid.indexOf("("));
				//该页面是否包含增删改查权限
				if (power == null || power.equals(m)) { 
					sb.append("<button class=\"" + cssClass + "\" "
							+ (href == null || href.equals("") ? "" : "onclick=\"" + href + "\"")
							+ " data-toggle=\"modal\" id=\"" + id + "\" >" + name + "</button>");
					break;
				}
			}
		}
		getJspContext().getOut().println(sb);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

}
