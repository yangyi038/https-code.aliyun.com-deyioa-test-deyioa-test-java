package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.ImageLable;

/**
 * 图文信息模板service
 * 
 * @author pzj
 *
 */
public interface ImageTextTemplateService {

	/**
	 * 获取图文模板列表
	 * 
	 * @param p
	 * @return
	 */
	public List<ImageLable> getImageLableList(Map<String, Object> p);

	/**
	 * 添加图文信息模板
	 * 
	 * @param model
	 * @return
	 */
	public boolean addImageLable(ImageLable model);

	/**
	 * 查询所有的图文信息列表
	 * 
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> getAllImageLableInfo(JqGridPager jqGridPager, Map<String, Object> p);

	/**
	 * 根据ID删除图文信息模板
	 * 
	 * @param id
	 * @return
	 */
	public int delImageLableById(Integer id);

	/**
	 * 通过审核
	 * 
	 * @param id
	 * @return
	 */
	public int agreeImageLableById(Integer id);

	/**
	 * 取消审核
	 * 
	 * @param id
	 * @return
	 */
	public int unArgeeImageLableById(Integer id);

	/**
	 * 根据ID查询要修改的信息
	 * 
	 * @param id
	 * @return
	 */
	public ImageLable queryImageLableById(Integer id);

	/**
	 * 根据ID修改图文信息模板
	 * 
	 * @param model
	 * @return
	 */
	public int updateImageLableById(ImageLable model);

	int unAgreeOnlineById(Integer id);

}
