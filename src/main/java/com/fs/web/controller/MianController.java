package com.fs.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.Tree;
import com.fs.comm.mapper.MianMapper;
import com.fs.comm.model.Sysmenu;
import com.fs.comm.model.Sysuser;

/**
 * 登录、注销controller
 * @author dyl
 *
 */
@Controller
@RequestMapping("/admin")
public class MianController{
	@Resource
    private MianMapper mianMapper;
	
	/**
	 * 获取主页（index.jsp）菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getmenu.action" )  
	public List<Sysmenu> getmenu(){
		List<Sysmenu> list=mianMapper.getmenu(null);
		for(Sysmenu sysmenu:list){
			List<Sysmenu> listc=mianMapper.getmenu(sysmenu.getId());
			if(listc!=null&&listc.size()>0){
				sysmenu.setChildSysmenu(listc);
			}
		}
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Sysuser sysuser=(Sysuser)session.getAttribute("currentUser");
		
		List<Sysmenu> retlist=Tree.BuildTreeSysmenu(list, sysuser);
		return retlist;
	}

}
