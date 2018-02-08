package com.framework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fs.comm.model.Category;
import com.fs.comm.model.Column;
import com.fs.comm.model.Department;
import com.fs.comm.model.Sysmenu;
import com.fs.comm.model.Sysuser;

public class Tree {
	/**
	 * 部门树
	 * @param departmentList
	 * @param level
	 * @return
	 */
	public static List<Map<String, Object>> BuildTree(Collection<Department> departmentList, String level) {
		List<Map<String, Object>> array = new ArrayList<Map<String, Object>>();
		for (Department department : departmentList) {
			Map map = new HashMap();
			map.put("id", department.getId().toString());
			map.put("text", department.getDepname());
			map.put("value", department.getDepcode() + "|" + department.getLevels());
			map.put("showcheck", false);
			map.put("complete", false);
			map.put("isexpand", false);
			map.put("pid", department.getDepparent() == null ? "" : department.getDepparent().toString());
			map.put("checkstate", 0);
			if (department.getCnum() > 0) {
				map.put("hasChildren", true);
				// map.put("ChildNodes",
				// BuildTree(department.getChildDepartment(),""));
			} else {
				map.put("hasChildren", false);
			}
			array.add(map);
		}
		return array;
	}

	/**
	 * 系统菜单树
	 * @param menuList
	 * @param level
	 * @return
	 */
	public static List<Map<String, Object>> BuildTreeSysmenu(Collection<Sysmenu> menuList, String level) {
		List<Map<String, Object>> array = new ArrayList<Map<String, Object>>();
		for (Sysmenu menu : menuList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", menu.getId().toString()); //主键
			map.put("text", menu.getMenuname()); //菜单名称
			map.put("value", menu.getFunctioncode() + "|" + "");//menu.getLevels()
			map.put("showcheck", false);
			map.put("complete", false);
			map.put("isexpand", false);
			map.put("pid", menu.getMenuparent() == null ? "" : menu.getMenuparent().toString());
			map.put("checkstate", 0);
			if (menu.getCnum() > 0) {//子集合数量
				map.put("hasChildren", true);
			} else {
				map.put("hasChildren", false);
			}
			array.add(map);
		}
		return array;
	}
	
	/**
	 * 类别树
	 * @param categoryList
	 * @return
	 */
	public static List<Map<String, Object>> BuildTrees(Collection<Category> categoryList) {
		List<Map<String, Object>> array = new ArrayList<Map<String, Object>>();
		for (Category category : categoryList) {
			Map map = new HashMap();
			map.put("id", category.getCat_id().toString());
			map.put("text", category.getCat_name().toString());
			map.put("pid", category.getParent_id() == null ? "" : category.getParent_id().toString());
			if (category.getCnum() > 0) {
				map.put("hasChildren", true);
				// map.put("ChildNodes",
				// BuildTree(department.getChildDepartment(),""));
			} else {
				map.put("hasChildren", false);
			}
			array.add(map);
		}
		return array;
	}

	/**
	 * 根据子节点的状态，获取当前节点的状态
	 * 
	 * @param sysmenuSet
	 * @param privilegemap
	 * @return
	 */
	public static int getcheckstate(List<Sysmenu> sysmenuSet, Map<String, String> privilegemap) {
		int i = 0;
		int checkstate = 0;
		for (Sysmenu sysmenu : sysmenuSet) {
			if (privilegemap.get(sysmenu.getId().toString()) != null
					&& (sysmenu.getChildSysmenu() == null || sysmenu.getChildSysmenu().size() == 0)) {
				i++;
			} else {
				int ii = getcheckstate(sysmenu.getChildSysmenu(), privilegemap);
				if (ii == 1) {
					i++;
				} else if (ii == 2) {
					return 2;
				}
			}
		}
		if (i != 0 && i == sysmenuSet.size()) {
			checkstate = 1;
		} else if ((i != 0 && i < sysmenuSet.size())) {
			checkstate = 2;
		}
		return checkstate;
	}

	public static int checkPrivilege(Set<Sysmenu> sysmenuSet, Map<String, String> privilegemap) {
		int i = 0;
		int checkstate = 0;
		for (Sysmenu sysmenu : sysmenuSet) {
			if (privilegemap.get(sysmenu.getId().toString()) != null) {
				i++;
			} else if (getcheckstate(sysmenu.getChildSysmenu(), privilegemap) == 2) {
				return 2;
			}
		}
		if (i != 0 && i == sysmenuSet.size()) {
			checkstate = 1;
		} else if ((i != 0 && i < sysmenuSet.size())) {
			checkstate = 2;
		}
		return checkstate;
	}

	/**
	 * 
	 * @param departmentList
	 * @param privilege
	 * @param checkstate
	 * @return
	 */
	public static List<Sysmenu> BuildTreeSysmenu(List<Sysmenu> sysmenuList, Sysuser admin) {// TCompanyAdmin
		List<Sysmenu> sysmenus = new ArrayList<Sysmenu>();
		if (admin.getSysrole() == null) {
			return null;
		}
		// 系统（超级）管理员
		if (admin.getSysrole().getId() == 1) {
			return sysmenuList;
		}
		String privilege = admin.getSysrole().getMenuid();
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
		Sysmenu obj = null;
		Iterator it = sysmenuList.iterator();
		while (it.hasNext()) {
			obj = (Sysmenu) it.next();
			String checkstate = privilegemap.get(obj.getId().toString());
			if (checkstate != null) {
				sysmenus.add(obj);
				addsysmenus(sysmenus, obj, privilegemap);
			}

			// if(privilegemap.get(obj.getId().toString())!=null){//当前节点是否为权限值
			// sysmenus.add(obj);
			// addsysmenus(sysmenus,obj,privilegemap);
			// }else{
			// if(getcheckstate(obj.getChildSysmenu(), privilegemap)!=0){
			// sysmenus.add(obj);
			// addsysmenus(sysmenus,obj,privilegemap);
			// }
			// }
		}
		return removeDuplicateWithOrder(sysmenus);
	}

	public static List removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}

	/**
	 * 添加权限菜单
	 * 
	 * @param list
	 * @return
	 */
	public static void addsysmenus(List<Sysmenu> sysmenus, Sysmenu obj) {

		for (Sysmenu sysmenu : obj.getChildSysmenu()) {
			// sysmenus.add(sysmenu);
			addsysmenus(sysmenus, sysmenu);
		}
		sysmenus.add(obj);
	}

	/**
	 * 添加权限菜单
	 * 
	 * @param list
	 * @return
	 */
	public static void addsysmenus(List<Sysmenu> sysmenus, Sysmenu obj, Map<String, String> privilegemap) {
		List<Sysmenu> temp = new ArrayList<Sysmenu>();
		for (Sysmenu sysmenu : obj.getChildSysmenu()) {
			if (privilegemap.get(sysmenu.getId().toString()) != null) {
				temp.add(sysmenu);
			}
		}
		obj.setChildSysmenu(temp);
	}
	
	
	

	/*************** jzb start *****************/
	/**
	 * 栏目列表
	 * @param columnList
	 * @param level
	 * @return
	 */
	public static List<Map<String, Object>> BuildTreeColumn(Collection<Column> columnList, String level) {
		List<Map<String, Object>> array = new ArrayList<Map<String, Object>>();
		for (Column column : columnList) {
			Map map = new HashMap();
			map.put("id", column.getId().toString());
			map.put("name", column.getName());
			map.put("type", column.getType());
			map.put("text", column.getName());
			map.put("value", column.getClassify() + "|" + column.getLevels());
			map.put("showcheck", false);
			map.put("complete", false);
			map.put("isexpand", false);
			map.put("pid", column.getParentId() == null ? "" : column.getParentId().toString());
			map.put("checkstate", 0);
			if (column.getCnum() > 0) {
				map.put("hasChildren", true);
				// map.put("ChildNodes",
				// BuildTree(department.getChildDepartment(),""));
			} else {
				map.put("hasChildren", false);
			}
			array.add(map);
		}
		return array;
	}
	
}
