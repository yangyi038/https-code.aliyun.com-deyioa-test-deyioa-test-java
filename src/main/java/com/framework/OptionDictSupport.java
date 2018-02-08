/**
 * <p>标题: 关于字典类型数据的内存加载基类</p>
 * <p>说明: 此方式定义了一个通用的字典加载类</p>
 * <p>公司: 浙江天正思维信息技术有限公司</p>
 * <p>版权: Copyright 2005 .Zhejiang Top Thinking information technology CO.,Ltd.</p>
 * <p>时间: 2006-11-2914:45:28</p>
 *
 * @author zqb
 * @version 1.0
 */
package com.framework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fs.comm.mapper.MianMapper;
import com.fs.comm.model.Parameter;


public abstract class OptionDictSupport {
	/**
	 * 根据字段名字和小类编号获取小类名称
	 * 
	 * @param type
	 *            大类编号
	 * @param code
	 *            小类编号
	 * @return 小类名称
	 */

	public final static String getCodeName(
			String type, String code) {
		TreeMap treeMaptemp=GlobalName.parameter;
		TreeMap treevalue=(TreeMap) treeMaptemp.get(type);
		if(treevalue!=null)
			return (String)treevalue.get(code);
		else
			return code;
		}
	public final static String getCodeName(HttpServletRequest request,
			String type, String code) {
		TreeMap treeMaptemp=(TreeMap) request.getSession().getServletContext().getAttribute(
				"parameter");
		TreeMap treevalue=(TreeMap) treeMaptemp.get(type);
		if(treevalue!=null){
			if(treevalue.get(code)!=null){
				return (String)treevalue.get(code);
			}else{
				return code;
			}
			
		}else{
			treevalue=(TreeMap) treeMaptemp.get(type.toLowerCase());
			if(treevalue!=null){
				return (String)treevalue.get(code);
			}
			
			return code;
		}
			
		}
	/**
	 * 根据参数名称获取参数列表
	 * @param request
	 * @param name
	 * @return
	 */
	public static  TreeMap<String, String> getNameMap(ServletContext servletContext,String name){
		TreeMap tempmap=(GlobalName.parameter).get(name);
		if(tempmap==null){
			return null;
		}
		return tempmap;
	}
	/**
	 * 根据参数名称获取参数值
	 * @param request
	 * @param name
	 * @return
	 */
	public static  String getCodeValue(String name,String value){
		TreeMap tempmap=(GlobalName.parameter).get(name);
		if(tempmap==null){
			return "";
		}else{
			if(tempmap!=null){
    			Iterator iter = tempmap.entrySet().iterator(); 
   	    	 while (iter.hasNext()) { 
   	    	Map.Entry entry = (Map.Entry) iter.next(); 
   	    	 Object key = entry.getKey(); 
   	    	 if(entry.getValue().equals(value)){
   	    		return key.toString();   	    		
   	    	 }
   	    	
    		}
    		

    	}
			return "";
		}
		
	}
	/**
	 * 根据参数名称获取参数值
	 * @param request
	 * @param name
	 * @return
	 */
	public static  String getCodeValue(HttpServletRequest request,String name,String value){
		TreeMap tempmap=((TreeMap<String, TreeMap>)request.getSession().getServletContext().getAttribute("parameter")).get(name);
		if(tempmap==null){
			return "";
		}else{
			if(tempmap!=null){
    			Iterator iter = tempmap.entrySet().iterator(); 
   	    	 while (iter.hasNext()) { 
   	    	Map.Entry entry = (Map.Entry) iter.next(); 
   	    	 Object key = entry.getKey(); 
   	    	 if(entry.getValue().equals(value)){
   	    		return key.toString();   	    		
   	    	 }
   	    	
    		}
    		

    	}
			return "";
		}
		
	}
	public static void main(String[] args) {

		System.out.println("1004");

	}
	public static  TreeMap delFirst(TreeMap tempmap){
		tempmap.remove("0");
		return tempmap;
	}
	public static  TreeMap updateFirst(TreeMap tempmap){
		tempmap.remove("0");
		tempmap.put("0","全部");
		return tempmap;
	}
}
