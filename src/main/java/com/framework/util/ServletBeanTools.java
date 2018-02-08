package com.framework.util;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
 

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
 


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
public class ServletBeanTools {
    
    /**自动匹配参数赋值到实体bean中
     * @author LiuDing
     * 2014-2-16 下午10:23:37
     * @param bean
     * @param request
     */
    public static void populate(Object bean, HttpServletRequest request){
        Class clazz = bean.getClass();
        Method ms[] = clazz.getDeclaredMethods();
        String mname;
        String field;
        String fieldType;
        String value;
        for(Method m : ms){
            mname = m.getName();
            if(!mname.startsWith("set")
                    || ArrayUtils.isEmpty(m.getParameterTypes())){
                continue;
            }
            try{
                field = mname.toLowerCase().charAt(3) + mname.substring(4, mname.length());
                value = request.getParameter(field);
                if(StringUtils.isEmpty(value)){
                    continue;
                }
                fieldType = m.getParameterTypes()[0].getName();
                //以下可以确认value为String类型
                if(String.class.getName().equals(fieldType)){
                    m.invoke(bean, (String)value);
                }else if(Integer.class.getName().equals(fieldType) && NumberUtils.isDigits((String)value)){
                    m.invoke(bean, Integer.valueOf((String)value));
                }else if(Short.class.getName().equals(fieldType) && NumberUtils.isDigits((String)value)){
                    m.invoke(bean, Short.valueOf((String)value));
                }else if(Float.class.getName().equals(fieldType) && NumberUtils.isNumber((String)value)){
                    m.invoke(bean, Float.valueOf((String)value));
                }else if(Double.class.getName().equals(fieldType) && NumberUtils.isNumber((String)value)){
                    m.invoke(bean, Double.valueOf((String)value));
                }else if(BigDecimal.class.getName().equals(fieldType) && NumberUtils.isNumber((String)value)){
                    m.invoke(bean, new BigDecimal((String)value));
                }else if(Date.class.getName().equals(fieldType)){
                    m.invoke(bean, DateUtils.parseDate((String)value, "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"));
                }else{
                    m.invoke(bean, value);
                }
            }catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
    /**
     * 从request中获得参数Map，并返回可读的Map
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map getParameterMap(HttpServletRequest request) {
    	// 参数Map
    	Map properties = request.getParameterMap();
    	// 返回值Map
    	Map returnMap = new TreeMap();
    	Iterator entries = properties.entrySet().iterator();
    	Map.Entry entry;
    	String name = "";
    	String value = "";
    	while (entries.hasNext()) {
    		entry = (Map.Entry) entries.next();
    		name = (String) entry.getKey();
    		Object valueObj = entry.getValue();
    		if(null == valueObj){
    			value = "";
    			returnMap.put(name, value);
    		}else if(valueObj instanceof String[]){
    			String[] values = (String[])valueObj;

    			if(values.length>1){
    				returnMap.put(name, valueObj);
    			}else{
        			for(int i=0;i<values.length;i++){
    				value = values[i] + ",";
    			}
    			value = value.substring(0, value.length()-1);
    			returnMap.put(name, value);
    			}
    		}else{
    			value = valueObj.toString();
    			returnMap.put(name, value);
    		}
    		
    	}
    	return returnMap;
    }
}
