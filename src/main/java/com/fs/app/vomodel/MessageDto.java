package com.fs.app.vomodel;

import lombok.Data;

/**
 * 接口返回格式
 * 
 * @author pzj
 *
 */
@Data
public class MessageDto {

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	// 编号
	private int code;
	// 信息
	private String info;
	// 对象
	private Object data;

}
