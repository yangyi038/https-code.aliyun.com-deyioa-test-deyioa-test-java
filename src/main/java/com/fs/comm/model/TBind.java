package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

@Data
public class TBind {

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Long getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	public Long getDealerid() {
		return dealerid;
	}

	public void setDealerid(Long dealerid) {
		this.dealerid = dealerid;
	}

	public String getDealername() {
		return dealername;
	}

	public void setDealername(String dealername) {
		this.dealername = dealername;
	}

	public Long getHotelsid() {
		return hotelsid;
	}

	public void setHotelsid(Long hotelsid) {
		this.hotelsid = hotelsid;
	}

	public Integer getHotelnum() {
		return hotelnum;
	}

	public void setHotelnum(Integer hotelnum) {
		this.hotelnum = hotelnum;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public Long getStbsid() {
		return stbsid;
	}

	public void setStbsid(Long stbsid) {
		this.stbsid = stbsid;
	}

	public String getStbnum() {
		return stbnum;
	}

	public void setStbnum(String stbnum) {
		this.stbnum = stbnum;
	}

	public String getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreatetimeStr() {
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public String getWifipwd() {
		return wifipwd;
	}

	public void setWifipwd(String wifipwd) {
		this.wifipwd = wifipwd;
	}

	public Integer getNumFlag() {
		return numFlag;
	}

	public void setNumFlag(Integer numFlag) {
		this.numFlag = numFlag;
	}

	private Long sid;

	// 运营商ID
	private Long companyid;
	private String companyname;
	// 二级运营商
	private Long operatorid;
	private String operatorname;
	// 经销商
	private Long dealerid;
	private String dealername;

	// 酒店主键
	private Long hotelsid;
	// 用户编号
	private Integer hotelnum;
	// 用户名称
	private String hotelname;

	// 机顶盒主键
	private Long stbsid;
	// 机顶盒编号
	private String stbnum;

	// 房间号
	private String roomnum;

	// 客户名称
	private String customer;

	// 欢迎词
	private String welcome;
	// 创建时间
	private Date createtime;
	private String createtimeStr;

	// 备注
	private String comm;

	// 无线热点
	private String wifi;
	// 无线热点密码
	private String wifipwd;

	private Integer numFlag;

}