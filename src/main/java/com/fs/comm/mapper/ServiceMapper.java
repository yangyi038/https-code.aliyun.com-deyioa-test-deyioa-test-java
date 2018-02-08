package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.Service;

public interface ServiceMapper {
		//服务列表
		List<Map<String,Object>> browseService(Map<String,Object> p);
		//添加服务标签
		int addService(Service service);
		//修改服务标签
		int updateService(Service service);
		//删除服务标签
		int delService(String id);
		//服务标签详情
		Service loadById(String id); 
 }
