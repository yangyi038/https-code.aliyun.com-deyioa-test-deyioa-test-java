package com.fs.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.mapper.AnswerEntityMapper;
import com.fs.comm.mapper.CateEntityMapper;
import com.fs.comm.mapper.CategoryEntityMapper;
import com.fs.comm.mapper.IndustryEntityMapper;
import com.fs.comm.mapper.ParameterMapper;
import com.fs.comm.mapper.ProblemEntityMapper;
import com.fs.comm.mapper.ShopEntityMapper;
import com.fs.comm.model.AnswerEntity;
import com.fs.comm.model.CateEntity;
import com.fs.comm.model.CategoryEntity;
import com.fs.comm.model.IndustryEntity;
import com.fs.comm.model.Parameter;
import com.fs.comm.model.ProblemEntity;
import com.fs.comm.model.ShopEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("ProblemService")
public class ProblemServiceImpl implements ProblemService{

	@Resource
	private ShopEntityMapper shopEntityMapper;
	
	@Resource
	private ProblemEntityMapper problemEntityMapper;

	@Resource
	private CateEntityMapper cateEntityMapper;
	
	@Resource
	private CategoryEntityMapper categoryEntityMapper;
	
	@Resource
	private IndustryEntityMapper industryEntityMapper;
	
	@Resource
	private AnswerEntityMapper answerEntityMapper;
	
	@Resource
	private ParameterMapper parameterMapper;
	
	/**
	 * 获取商店列表
	 * 
	 * @param p
	 * @return
	 */
	@Override
	public List<ShopEntity> selectShop(Map<String, Object> p) {
		List<ShopEntity> shopList = shopEntityMapper.getShopList(p);
		return shopList;
	}

	/**
	 * 获取问题列表 
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	@Override
	public List<ProblemEntity> problemList(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<ProblemEntity> problemList = problemEntityMapper.getProblemList(p);
		
		PageInfo page = new PageInfo(problemList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		return problemList;
	}

	/**
	 * 获取获取行业包列表
	 * 
	 * @param p
	 * @return
	 */
	@Override
	public List<IndustryEntity> selectIndustry(Map<String, Object> p) {
		List<IndustryEntity> industryList = industryEntityMapper.getIndustryList(p);
		return industryList;
	}

	/**
	 * 获取分类列表
	 * 
	 * @return
	 */
	@Override
	public List<CateEntity> qcateList() {
		List<CateEntity> qcateList = cateEntityMapper.getCateList();
		return qcateList;
	}

	/**
	 * 添加问题
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public boolean addProblem(ProblemEntity model) {
		int i = problemEntityMapper.insertSelective(model);
		if(i > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 通过id获取商店列表
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public ProblemEntity selectShopByid(ProblemEntity model) {
		ProblemEntity entity = problemEntityMapper.getProblemListByid(model);
		return entity;
	}

	/**
	 * 编辑问题操作
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public boolean editProblem(ProblemEntity model) {
		int index = problemEntityMapper.updateProblem(model);
		if(index > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除问题操作
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public boolean deleteProblem(ProblemEntity model) {
		int index = problemEntityMapper.deleteProblem(model);
		if(index > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 添加行业包操作
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public boolean addIndustry(IndustryEntity model) {
		int index = industryEntityMapper.insertSelective(model);
		if(index > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 获取问题分类列表
	 * 
	 * @return
	 */
	@Override
	public List<CategoryEntity> getCategoryList() {
		List<CategoryEntity> CategoryList = categoryEntityMapper.getCategoryList();
		return CategoryList;
	}

	/**
	 * 添加答案操作
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public boolean addAnswer(AnswerEntity model) {
		int j = answerEntityMapper.insertSelective(model);
		if(j > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据主键查询答案
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public AnswerEntity selecetAnswer(int id) {
		AnswerEntity model = new AnswerEntity();
		model.setId(id);
		AnswerEntity entity = answerEntityMapper.selectByPrimaryKey(model);
		return entity;
	}

	/**
	 * 根据主键更新答案
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public boolean updateAnswer(AnswerEntity entity) {
		int i = answerEntityMapper.updateByPrimaryKeySelective(entity);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除答案操作
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public boolean deleteAnswer(AnswerEntity entity) {
		int i = answerEntityMapper.deleteByPrimaryKey(entity);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 通过id删除答案操作
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public boolean deleteAnswerByid(AnswerEntity entity) {
		int i = answerEntityMapper.deleteByid(entity);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 检索已经删除的问题和答案
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public List<ProblemEntity> selectAnswer(JqGridPager jqGridPager, Map<String, Object> p) {
		PageHelper.startPage(Integer.valueOf(jqGridPager.getPager()), jqGridPager.getRows());
		List<ProblemEntity> answerList = problemEntityMapper.selectAnswer(p);
		PageInfo page = new PageInfo(answerList);
		jqGridPager.setRecords(String.valueOf(page.getTotal()));
		
		return answerList;
	}

	/**
	 * 恢复问题操作
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public boolean recoveryProblem(ProblemEntity entity) {
		int i = problemEntityMapper.recoveryProblem(entity);
		if(i > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据问题id恢复所有的答案
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public boolean recoveryByPrimaryKey(AnswerEntity key) {
		int i = answerEntityMapper.recoveryByPrimaryKey(key);
		if(i > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据id恢复一条答案
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public boolean recoveryByid(AnswerEntity key) {
		int i = answerEntityMapper.recoveryByid(key);
		if(i > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Parameter getParameterListByname(Parameter entity) {
		Parameter parameter = parameterMapper.getParameterListByname(entity);
		return parameter;
	}

	@Override
	public List<ProblemEntity> getProblemList(Map<String, Object> p) {
		List<ProblemEntity> problemList = problemEntityMapper.getProblemList(p);
		return problemList;
	}
}
