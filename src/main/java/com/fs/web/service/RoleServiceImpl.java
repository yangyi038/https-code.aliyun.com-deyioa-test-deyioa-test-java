package com.fs.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fantastic.ContextHolderUtils;
import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.RoleMapper;
import com.fs.comm.mapper.TCompanyMapper;
import com.fs.comm.model.Role;
import com.fs.comm.model.Sysmenu;
import com.fs.comm.model.Sysuser;
import com.fs.comm.model.TCompany;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("roleService")
/** 角色管理业务逻辑接口实现 */
public class RoleServiceImpl implements RoleService {
	/** 通过依赖注入Mapper组件实例 */
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private TCompanyMapper companyMapper;
	

	/** 角色加载 */
	public Role loadRole(Integer id) {
		Role role = roleMapper.loadRole(id);
		return role;
	}

	/** 新增角色 */
	public boolean saveRole(Role role) {
		try {
			if (roleMapper.saveRole(role) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/** 修改角色 */
	public boolean updateRole(Role role) {
		try {
			if (roleMapper.updateRole(role) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/** 浏览角色 */
	public List<Role> browseRole(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<Role> queryRole = roleMapper.browseRole(p);
		if (queryRole != null && queryRole.size() > 0) {
			for (Role role : queryRole) {
				// 获取运营商名称
				if (role.getCompanyid() != null) {
					TCompany company = companyMapper.selectByPrimaryKey(role.getCompanyid());
					if (company != null && StringUtils.isNotBlank(company.getCompanyname()))
						role.setCompanyname(company.getCompanyname());
				}
			}
		}
		
		PageInfo<List<Role>> page = new PageInfo(queryRole);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return queryRole;
	}

	/** 浏览角色 */
	public List<Role> browseRole(Map<String, Object> p) {
		List<Role> deps = roleMapper.browseRole(p);
		return deps;
	}

	/** 删除指定的角色 */
	public boolean delRole(Integer id) {
		try {
			if (roleMapper.delById(id) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/** 统计指定类的查询结果 */
	public int countSysuser(Integer id) {
		return roleMapper.countSysuser(id);
	}

	/** 加载权限树 */
	public List<Map<String, Object>> browseSysmenu(String menuparent, String privilege, String checkstate) {
		List<Map<String, Object>> rettree = new ArrayList<Map<String, Object>>();
		
		//根据角色控制菜单显示：系统管理员，运营商，运管理员
		Sysuser user = ContextHolderUtils.getLoginUser();
		Map<String, Object> map=new HashMap<>();
		if(user!=null){
			map.put("admintype", user.getAdmintype());
		}
		
		List<Sysmenu> menuList = roleMapper.getmenu(map);
		List<Sysmenu> sysmenus =new ArrayList<>();
		//遍历：为每一个最小子菜单添加增删改查节点(页面控件权限管理)
		for (Sysmenu sysmenu : menuList) {
			sysmenus.add(sysmenu);
			if(StringUtils.isNotBlank(sysmenu.getMenulink())){
				Sysmenu addMenu=new Sysmenu();
				addMenu.setId(Integer.parseInt(sysmenu.getId().toString()+901));
				addMenu.setFunctioncode(sysmenu.getId().toString()+901);
				addMenu.setMenuname("添加");
				addMenu.setMenuparent(sysmenu.getId());
				sysmenus.add(addMenu);
				
				Sysmenu delMenu=new Sysmenu();
				delMenu.setId(Integer.parseInt(sysmenu.getId().toString()+902));
				delMenu.setFunctioncode(sysmenu.getId().toString()+902);
				delMenu.setMenuname("删除");
				delMenu.setMenuparent(sysmenu.getId());
				sysmenus.add(delMenu);
				
				Sysmenu editMenu=new Sysmenu();
				editMenu.setId(Integer.parseInt(sysmenu.getId().toString()+903));
				editMenu.setFunctioncode(sysmenu.getId().toString()+903);
				editMenu.setMenuname("修改");
				editMenu.setMenuparent(sysmenu.getId());
				sysmenus.add(editMenu);
				
				Sysmenu viewMenu=new Sysmenu();
				viewMenu.setId(Integer.parseInt(sysmenu.getId().toString()+904));
				viewMenu.setFunctioncode(sysmenu.getId().toString()+904);
				viewMenu.setMenuname("查看");
				viewMenu.setMenuparent(sysmenu.getId());
				sysmenus.add(viewMenu);
				
				//机顶盒管理，终端管理
				if(sysmenu.getId()==80 || sysmenu.getId()==82){
					Sysmenu downloadTemplate=new Sysmenu();
					downloadTemplate.setId(Integer.parseInt(sysmenu.getId().toString()+905));
					downloadTemplate.setFunctioncode(sysmenu.getId().toString()+905);
					downloadTemplate.setMenuname("下载模板");
					downloadTemplate.setMenuparent(sysmenu.getId());
					sysmenus.add(downloadTemplate);
					
					Sysmenu importMenu=new Sysmenu();
					importMenu.setId(Integer.parseInt(sysmenu.getId().toString()+906));
					importMenu.setFunctioncode(sysmenu.getId().toString()+906);
					importMenu.setMenuname("导入");
					importMenu.setMenuparent(sysmenu.getId());
					sysmenus.add(importMenu);
				}
			}
		}
		
		String[] privilegearray;
		Map<String, String> privilegemap = new HashMap<String, String>();
		if (privilege != null && !privilege.equals("")) {
			privilegearray = privilege.split("\\|");
			for (String privileges : privilegearray) {
				String pvalue = privileges.substring(0, privileges.indexOf("("));
				String cs = privileges.substring(privileges.indexOf("(") + 1, privileges.indexOf(")"));
				privilegemap.put(pvalue, cs);
			}
		}
		Map<String, Object> rootnode = new HashMap<String, Object>();// 根节点
		List<Map<String, Object>> parentnodes = new ArrayList<Map<String, Object>>();// 根节点以下树结构
		
		for (Sysmenu sysmenu : sysmenus) {
			if (sysmenu.getMenuparent() == null) {// 根节点
				rootnode.put("id", sysmenu.getId().toString());
				rootnode.put("text", sysmenu.getMenuname());
				rootnode.put("value", sysmenu.getFunctioncode());
				rootnode.put("showcheck", true);
				rootnode.put("complete", true);
				rootnode.put("isexpand", true);
				if (privilegemap != null) {
					if (privilegemap.get(sysmenu.getId().toString()) != null) {
						rootnode.put("checkstate", Integer.valueOf(privilegemap.get(sysmenu.getId().toString())));
					}
				}
			} else {// 根节点下所有子节点
				Map<String, Object> nodemap = new HashMap<String, Object>();
				nodemap.put("id", sysmenu.getId().toString());
				nodemap.put("text", sysmenu.getMenuname());
				nodemap.put("value", sysmenu.getFunctioncode());
				nodemap.put("showcheck", true);
				nodemap.put("complete", true);
				nodemap.put("isexpand", false);
				// 判断是否需要勾选已经选中的权限值
				if (privilegemap != null) {
					if (privilegemap.get(sysmenu.getId().toString()) != null) {
						nodemap.put("checkstate", Integer.valueOf(privilegemap.get(sysmenu.getId().toString())));
					}
				}
				nodemap.put("pid", sysmenu.getMenuparent());

				if ((sysmenu.getMenuparent().toString()).equals(rootnode.get("id"))) {// 为tree root 增加子节点
					parentnodes.add(nodemap);
					if (rootnode.get("ChildNodes") == null) {
						rootnode.put("ChildNodes", new ArrayList<Map<String, Object>>());
					}
					((List<Map<String, Object>>) rootnode.get("ChildNodes")).add(nodemap);
					rootnode.put("hasChildren", true);
					if (privilegemap != null) {
						if (privilegemap.get(sysmenu.getId().toString()) != null) {
							nodemap.put("checkstate", Integer.valueOf(privilegemap.get(sysmenu.getId().toString())));
						}
					}

				} else {// 为菜单增加子节点
					getChildrenNodes(parentnodes, nodemap, privilegemap);
					parentnodes.add(nodemap);
				}

			}
		}
		rettree.add(rootnode);
		return rettree;
	}

	/**
	 * 为菜单增加子节点
	 */
	public static void getChildrenNodes(List<Map<String, Object>> parentnodes, Map<String, Object> node,
			Map<String, String> privilegemap) {
		// 循环遍历所有父节点和node进行匹配，确定父子关系
		for (int i = parentnodes.size() - 1; i >= 0; i--) {

			Map<String, Object> pnode = parentnodes.get(i);
			// 如果是父子关系，为父节点增加子节点，退出for循环
			if ((pnode.get("id").toString()).equals(node.get("pid").toString())) {
				// pnode.setState("closed") ;//关闭二级树
				if (pnode.get("ChildNodes") == null) {
					pnode.put("ChildNodes", new ArrayList<Map<String, Object>>());
				}
				((List<Map<String, Object>>) pnode.get("ChildNodes")).add(node);
				pnode.put("hasChildren", true);
				return;
			} else {
				// 如果不是父子关系，删除父节点栈里当前的节点，
				// 继续此次循环，直到确定父子关系或不存在退出for循环
				parentnodes.remove(i);
			}
		}
	}
}
