package com.framework;

import java.util.TreeMap;


public class GlobalName {
	/**
	 * 发送邮件的邮箱参数
	 */
	public static String key_smtp="";
	public static String value_smtp="";
	public static String key_props="";
	public static String send_user="";
	public static String send_uname="";
	public static String send_pwd="";
	/**
	 * 参数集合
	 */
	public static TreeMap<String, TreeMap<String, Object>> parameter=new TreeMap<String, TreeMap<String, Object>>();
	
	/**
	 * 快速索引,哪些表包含有代码
	 */
	public static TreeMap<String, String> ClassHasParameter=new TreeMap<String, String>();
}
