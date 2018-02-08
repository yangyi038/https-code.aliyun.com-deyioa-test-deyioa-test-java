package com.fs.web.service;

import java.util.List;
import java.util.Map; 

import com.framework.jqgrid.JqGridPager;     
/** 活动管理管理业务逻辑接口 */
public interface PaylistService {
			public  List<Map<String,Object>> browsePaylist(JqGridPager jqGridPager,Map<String, Object> p);  
}
