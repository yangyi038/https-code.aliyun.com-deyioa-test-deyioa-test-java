package com.fs.web.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.framework.ActionUtil;
import com.framework.util.MD5;
import com.framework.util.Tools;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.SysuserService;

/**
 * 登录、注销controller
 * 
 * @author dyl
 *
 */
@Controller
@RequestMapping("/user")
public class LoginController extends BaseController {

	@Resource
	private SysuserService sysuserService;

	@ResponseBody
	@RequestMapping("/login.action")
	public Map<String, Object> login(@ModelAttribute("model") Sysuser model, HttpServletRequest request) {
		String rand = request.getParameter("rand");
		if (model.getLoginname() == null || model.getLoginname().equals("")) {
			return ActionUtil.ajaxFail("用户名不能为空", "");
		}
		if (model.getLoginpwd() == null || model.getLoginpwd().equals("")) {
			return ActionUtil.ajaxFail("密码不能为空！", "");
		}

		if (rand == null || !rand.equalsIgnoreCase((String) request.getSession().getAttribute("rand"))) {
			return ActionUtil.ajaxFail("验证码错误", "");
		} else {
			UsernamePasswordToken token = new UsernamePasswordToken(model.getLoginname(), model.getLoginpwd());
			// System.out.println("为了验证登录用户而封装的token为"+
			// ReflectionToStringBuilder.toString(token,ToStringStyle.MULTI_LINE_STYLE));
			// 获取当前的Subject
			Subject currentUser = SecurityUtils.getSubject();
			try {
				// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
				// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
				// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
				String rmbUser = request.getParameter("rmbUser");
				if (rmbUser != null && rmbUser.equals("1")) {
					token.setRememberMe(true);
				} else {
					token.setRememberMe(false);
				}
				currentUser.login(token);

			} catch (UnknownAccountException uae) {
				return ActionUtil.ajaxFail("未知账户", "");
			} catch (IncorrectCredentialsException ice) {
				return ActionUtil.ajaxFail("密码不正确", "");
			} catch (DisabledAccountException lae) {
				return ActionUtil.ajaxFail("账户已注销", "");
			} catch (ExcessiveAttemptsException eae) {
				return ActionUtil.ajaxFail("用户名或密码错误次数过多", "");
			} catch (AuthenticationException ae) {
				// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
				return ActionUtil.ajaxFail("用户名或密码不正确", "");
			}
			// 验证是否登录成功
			if (currentUser.isAuthenticated()) {
				insertSysLog("登录", "登录系统", "成功", currentUser().getLoginname() + "登录系统成功");
				return ActionUtil.ajaxSuccess("登录成功！", MD5.MD5Encode(model.getLoginpwd()));
			} else {
				token.clear();
			}
		}
		return null;
	}

	/**
	 * 用户登出
	 */
	@RequestMapping("/logout.action")
	public ModelAndView logout(HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
		if (sysuser != null) {
			sysuserService.saveSysloginlog(sysuser, Tools.getIpAddr(request), "自主退出");
		}

		insertSysLog("退出", "退出系统", "成功", currentUser().getLoginname() + "退出系统成功");
		SecurityUtils.getSubject().logout();
		return new ModelAndView("redirect:/admin");
	}

}
