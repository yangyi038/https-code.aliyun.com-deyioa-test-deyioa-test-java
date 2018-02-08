package com.fs.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.AnswerEntity;
import com.fs.comm.model.CateEntity;
import com.fs.comm.model.CategoryEntity;
import com.fs.comm.model.IndustryEntity;
import com.fs.comm.model.Parameter;
import com.fs.comm.model.ProblemEntity;
import com.fs.comm.model.ShopEntity;

@Service
public interface ProblemService {

	/**
	 * 获取商店列表
	 * 
	 * @param p
	 * @return
	 */
	public List<ShopEntity> selectShop(Map<String, Object> p);
	
	/**
	 * 获取获取行业包列表
	 * 
	 * @param p
	 * @return
	 */
	public List<IndustryEntity> selectIndustry(Map<String, Object> p);
	
	/**
	 * 通过条件获取问题列表 
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<ProblemEntity> problemList(JqGridPager jqGridPager, Map<String, Object> p);
	
	/**
	 * 获取问题列表 
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<ProblemEntity> getProblemList(Map<String, Object> p);
	
	/**
	 * 获取分类列表
	 * 
	 * @return
	 */
	public List<CateEntity> qcateList();
	
	/**
	 * 获取问题分类列表
	 * 
	 * @return
	 */
	public List<CategoryEntity> getCategoryList();
	
	/**
	 * 添加问题
	 * 
	 * @param model
	 * @return
	 */
	public boolean addProblem(ProblemEntity model);
	
	/**
	 * 通过id获取商店列表
	 * 
	 * @param model
	 * @return
	 */
	public ProblemEntity selectShopByid(ProblemEntity model);
	
	/**
	 * 编辑问题操作
	 * 
	 * @param model
	 * @return
	 */
	public boolean editProblem(ProblemEntity model);
	
	/**
	 * 删除问题操作
	 * 
	 * @param model
	 * @return
	 */
	public boolean deleteProblem(ProblemEntity model);
	
	/**
	 * 添加行业包操作
	 * 
	 * @param model
	 * @return
	 */
	public boolean addIndustry(IndustryEntity model);
	
	/**
	 * 添加答案操作
	 * 
	 * @param model
	 * @return
	 */
	public boolean addAnswer(AnswerEntity model);
	
	/**
	 * 根据主键查询答案
	 * 
	 * @param model
	 * @return
	 */
	public AnswerEntity selecetAnswer(int id);
	
	/**
	 * 根据主键更新答案
	 * 
	 * @param entity
	 * @return
	 */
	public boolean updateAnswer(AnswerEntity entity);
	
	/**
	 * 删除答案操作
	 * 
	 * @param entity
	 * @return
	 */
	public boolean deleteAnswer(AnswerEntity entity);
	
	/**
	 * 通过id删除答案操作
	 * 
	 * @param entity
	 * @return
	 */
	public boolean deleteAnswerByid(AnswerEntity entity);
	
	/**
	 * 检索已经删除的问题和答案
	 * 
	 * @param key
	 * @return
	 */
	public List<ProblemEntity> selectAnswer(JqGridPager jqGridPager, Map<String, Object> p);
	
	/**
	 * 恢复问题操作
	 * 
	 * @param entity
	 * @return
	 */
	public boolean recoveryProblem(ProblemEntity entity);
	
	/**
	 * 根据问题id恢复所有的答案
	 * 
	 * @param key
	 * @return
	 */
	public boolean recoveryByPrimaryKey(AnswerEntity key);
	
	/**
	 * 根据id恢复一条答案
	 * 
	 * @param key
	 * @return
	 */
	public boolean recoveryByid(AnswerEntity key);
	
	/**
	 * 根据name获取类型
	 * 
	 * @param map
	 * @return
	 */
	Parameter getParameterListByname(Parameter entity);
}
