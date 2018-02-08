package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.AnswerEntity;
import com.fs.comm.model.ColumnType;
import com.fs.comm.model.Program;

/**
 * 栏目下节目相关业务
 * @author pzj
 *
 */
public interface ProgramService {
	
	
	/**
	 * 获取栏目列表
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAlltitleColumn(JqGridPager jqGridPager,Map<String, Object> p);
	
	
	
	/**
	 * 查看节目列表
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAlltitleColumnType(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public ColumnType getColumnTypeById(Integer id);
	
	/**
	 * 添加节目信息
	 * @param model
	 * @return
	 */
	public int insertProgram(Program model);
	
	/**
	 * 根据column_id查询信息
	 * @param id
	 * @return
	 */
	public Program getProgramByColumnId(Integer id,String name);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delProgramById(Integer id);
	
	/**
	 * 根据column_id查询信息
	 * @param id
	 * @return
	 */
	public Program getProgramById(Integer id);
	
	/**
	 * 根据Id修改信息
	 * @param model
	 * @return
	 */
	public int updateProgramById(Program model);
	
	
	/**
	 * 根据栏目ID查询其下绑定的节目列表
	 * @param ColumnId
	 * @return
	 */
	public List<Program> getProgramListByColumnId(Map<String, Object> map);
}
