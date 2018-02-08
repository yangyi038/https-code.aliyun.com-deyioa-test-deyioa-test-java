package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.Program;

/**
 * 节目相关接口
 * @author pzj
 *
 */
public interface ProgramMapper {
	
    int deleteByPrimaryKey(Integer id);
    int insert(Program record);
    int insertSelective(Program record);
    Program selectByPrimaryKey(long id);
    int updateByPrimaryKeySelective(Program record);
    int updateByPrimaryKey(Program record);
    
    List<Map<String,Object>> getAllTitleColumn(Map<String, Object> p);
    
    Program getProgramByColumnId(Program model);
    
    int delProgramById(Integer id);
    
    int delProgramByModel(Program model);
    
    int agreeCheckprogram(Program model);
    
    int unAgreeCheckprogram(Program model);
    
    int agreeOnline(Program model);
    
    int unAgreeOnline(Program model);
    
    List<Program> getProgramByModel(Program model);
    
    int updateProgramSelective(Program model);
    
    /**
     * 根据栏目ID获取其下绑定的节目列表
     * @param columnid
     * @return
     */
    List<Program> getProgramListByColumnId(Map<String, Object> map);
}