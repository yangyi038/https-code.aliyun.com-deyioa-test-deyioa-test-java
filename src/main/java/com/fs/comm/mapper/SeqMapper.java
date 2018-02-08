package com.fs.comm.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fs.comm.model.Parameter;
import com.fs.comm.model.Sysmenu;
import com.fs.comm.model.Sysuser;

public interface SeqMapper {
	
	String getSeq(@Param(value="seq_name")String seq_name);
	
}