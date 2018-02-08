package com.fs.comm.mapper;

import com.fs.comm.model.AnswerEntity;

public interface AnswerEntityMapper {
	/**
	 * 根据id查询问题
	 * 
	 * @param key
	 * @return
	 */
	public AnswerEntity selectByPrimaryKey(AnswerEntity key);
	
	/**
	 * 根据问题id删除问题的所有答案
	 * 
	 * @param key
	 * @return
	 */
	public int deleteByPrimaryKey(AnswerEntity key);
	
	/**
	 * 根据id删除一条答案
	 * 
	 * @param key
	 * @return
	 */
	public int deleteByid(AnswerEntity key);
	
	/**
	 * 根据问题id恢复所有的答案
	 * 
	 * @param key
	 * @return
	 */
	public int recoveryByPrimaryKey(AnswerEntity key);
	
	/**
	 * 根据id恢复一条答案
	 * 
	 * @param key
	 * @return
	 */
	public int recoveryByid(AnswerEntity key);

    public int insert(AnswerEntity record);

    /**
     * 插入答案
     * 
     * @param record
     * @return
     */
    public int insertSelective(AnswerEntity record);

    /**
     * 通过id更新答案
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(AnswerEntity record);

    public int updateByPrimaryKey(AnswerEntity record);
}