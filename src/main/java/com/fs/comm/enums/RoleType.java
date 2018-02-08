package com.fs.comm.enums;

/**
 * 角色类型
 * @author pzj
 *
 */
public enum RoleType {
	
	超级管理员(99,"超级管理员"),
	
	系统管理员(0, "系统管理员"), 
	一级运营商(1, "一级运营商"), 
	一级运管(2,"一级运管"),  
	二级运营商(3,"二级运营商"), 
	经销商(4, "经销商");
	
	private int roleValue;
	private String roleName;

	private RoleType(int rolevalue, String rolename) {
		this.roleValue = rolevalue;
		this.roleName = rolename;
	}

	public int getRoleValue() {
		return roleValue;
	}

	public String getRoleName() {
		return roleName;
	}

	public static RoleType findByValue(int roleValue) {
		for (RoleType each : values()) {
			if (each.roleValue == roleValue) {
				return each;
			}
		}
		return null;
	}

	public static RoleType findByName(String roleName) {
		for (RoleType each : values()) {
			if (each.roleName.equals(roleName)) {
				return each;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "[" + this.roleValue + ":" + this.roleName + "]";
	}

}
