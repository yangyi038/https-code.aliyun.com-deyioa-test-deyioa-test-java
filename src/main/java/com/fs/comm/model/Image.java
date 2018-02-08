package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 缩略图
 * @author pzj
 *
 */
@Data
public class Image {
	
    private Integer id;
    private String name;
    private String fileName;
    private String imageUrl;
    private String type;
    private String imageDesc;
    private Date createTime;
    private String isDelete;
    private Integer imagetextId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImageDesc() {
		return imageDesc;
	}
	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getImagetextId() {
		return imagetextId;
	}
	public void setImagetextId(Integer imagetextId) {
		this.imagetextId = imagetextId;
	}

   
}