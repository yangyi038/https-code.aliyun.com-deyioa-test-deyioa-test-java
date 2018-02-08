package com.fs.comm.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fs.comm.model.Parameter;
import com.fs.comm.model.Sysmenu;

public interface MianMapper {
	
	List<Sysmenu> getmenu(@Param(value="menuparent")Integer menuparent);
	List<Parameter> getParameters(Map<String, Object> p);
}