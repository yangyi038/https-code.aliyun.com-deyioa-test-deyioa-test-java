package com.fs.comm.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fs.comm.model.T_picture;

/**
 * 图片管理mapper
 * 
 * @author pzj
 *
 */
@Repository
@SuppressWarnings("javadoc")
public interface T_pictureMapper {

	/**
	 * 添加图片
	 * 
	 * @param model
	 * @return
	 */
	int insertPicture(T_picture model);

	/**
	 * 获取图片列表
	 * 
	 * @param p
	 * @return
	 */
	List<Map<String, Object>> getAllPictureInfo(Map<String, Object> p);

	int deletePictureById(String id);

	int agreePicture(String id);

	int unAgreePicture(String id);

	T_picture queryPictureById(Integer id);

	List<T_picture> selectPictuireGroup(Map<String,Object> p);

}
