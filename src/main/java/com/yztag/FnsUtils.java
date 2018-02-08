package com.yztag;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.framework.OptionDictSupport;

public class FnsUtils {
	public static TreeMap<String, String> getCodeMap(HttpServletRequest request,String name){
		TreeMap<String, String> codemap=OptionDictSupport.getNameMap(request.getSession().getServletContext(), name);
        return codemap;
    }
	public static  String getCodeName(String value,String name){
		String code=OptionDictSupport.getCodeName(name,value);
        return code;
    }
}