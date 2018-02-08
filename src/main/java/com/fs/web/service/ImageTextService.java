package com.fs.web.service;

import java.util.List;
import java.util.Map;

import com.framework.jqgrid.JqGridPager;
import com.fs.comm.model.Image;
import com.fs.comm.model.ImageText;
import com.fs.comm.model.ImageVideo;

public interface ImageTextService {
	
	/**
	 * 添加图文信息
	 * @param model
	 * @return
	 */
	public boolean addImageText(ImageText model);
	
	/**
	 * 查询所以的图文信息
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getAllImageTextInfo(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 根据ID删除图文信息
	 * @param id
	 * @return
	 */
	public int delImageTextById(Integer id);
	
	/**通过ID取消审核
	 * 
	 * @param id
	 * @return
	 */
	public int unAgreeImageTextById(Integer id);
	
	/**
	 * 根据ID通过审核
	 * @param id
	 * @return
	 */
	public int agreeImageTextById(Integer id);
	
	/**
	 * 根据ID上线
	 * @param id
	 * @return
	 */
	public int agreeOnlineById(Integer id);
	
	/**
	 * 根据ID下线
	 * @param id
	 * @return
	 */
	public int agreeUnOnlineById(Integer id);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public ImageText queryImageTextbyId(Integer id);
	
	
	/**
	 * 根据ID修改
	 * @param id
	 * @return
	 */
	public int updateImageTextbyId(ImageText model);
	
	
	/********************************************************************************************************/
	
	
	
	/********************************视频  start***********************************************/
	
	/**
	 * 查询视频列表
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getImageVideoByImageTextId(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 添加视频
	 * @param model
	 * @return
	 */
	public int addVideo(ImageVideo model);
	
	/**
	 * 修改视频
	 * @param model
	 * @return
	 */
	public int editVideo(ImageVideo model);
	
	/**
	 * 删除视频
	 * @param model
	 * @return
	 */
	public int delVideo(Integer id);
	
	/**
	 * 添加图片
	 * @param model
	 * @return
	 */
	public int addPicture(ImageVideo model);
	
	
	/**************************************视频  end****************************************************/
	
	
	/***************************************图片   start*************************************************/
	/**
	 * 查询图片信息
	 * @param jqGridPager
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> getImageByImageTextId(JqGridPager jqGridPager,Map<String, Object> p);
	
	/**
	 * 添加图片
	 * @param model
	 * @return
	 */
	public int addImage(Image model);
	
	/**
	 * 删除图片
	 * @param id
	 * @return
	 */
	public int delImage(Integer id);
	
	/**************************************图片 end****************************************************/
	
	/***********************************************************************************/
	
	/**
	 * 添加属性模板
	 * @param model
	 * @return
	 */
	//public boolean addTattribute(Tattribute model);
}
