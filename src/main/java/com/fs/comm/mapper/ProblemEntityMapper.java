package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import com.fs.comm.model.ProblemEntity;

public interface ProblemEntityMapper {
	
    /**
     * 新增问题
     * 
     * @param record
     * @return
     */
    public int insertSelective(ProblemEntity record);
    
	/**
	 * 获取问题列表
	 * 
	 * @param map
	 * @return
	 */
	public List<ProblemEntity> getProblemList(Map<String, Object> map); 
	
	/**
	 * 获取分类列表
	 * 
	 * @param map
	 * @return
	 */
	public List<ProblemEntity> getQcateList(Map<String, Object> map);
	
	/**
	 * 通过id获取问题列表
	 * 
	 * @param map
	 * @return
	 */
	public ProblemEntity getProblemListByid(ProblemEntity entity);
	
	/**
	 * 编辑问题
	 * 
	 * @param entity
	 * @return
	 */
	public int updateProblem(ProblemEntity entity);
	
	/**
	 * 删除问题操作
	 * 
	 * @param entity
	 * @return
	 */
	public int deleteProblem(ProblemEntity entity);
	
	/**
	 * 检索已经删除的问题
	 * 
	 * @param p
	 * @return
	 */
	public List<ProblemEntity> selectAnswer(Map<String, Object> p);
	
	/**
	 * 恢复问题操作
	 * 
	 * @param entity
	 * @return
	 */
	public int recoveryProblem(ProblemEntity entity);
}