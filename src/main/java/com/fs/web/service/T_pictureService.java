package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.T_picture;

public interface T_pictureService {
	
	/**
	 * 添加图片
	 * @param model
	 * @return
	 */
	public boolean insertPicture(T_picture model);
	
	/**
	 * 获取图片列表
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllPictureInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 删除图片信息
	 * @param id
	 * @return
	 */
	public int delPictureById(String id);
	
	/**
	 * 审核通过
	 * @param id
	 * @return
	 */
	public int agreePictureById(String id);
	
	/**
	 * 取消审核
	 * @param id
	 * @return
	 */
	public int unAgreePictureById(String id);
	
	/**
	 * 根据id查询图片信息
	 * @param id
	 * @return
	 */
	public T_picture queryPictureById(Integer id);
	
	/**
	 * 查询图片组
	 * @return
	 */
	public List<T_picture> selectPictureGroup(Map<String,Object> p);
}
