package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 直播频道表
 * @author pzj
 *
 */
@Data
public class Channel {

	private Integer id;
	private Integer number;
	private String name;
	private String type;
	private String bilingualSigns;
	private String protectiveEmblem;
	private String language;
	private String videoParameter;
	private String audioParameter;
	private String signalSource;
	private String bitStream;
	private Integer starLevel;
	private String tvLogo;
	private String country;
	private String province;
	private String city;
	private String email;
	private String supplier;
	private String correlation;
	private String contentDesignator;
	private String tvDescribe;
	private String status;
	private String lineStatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBilingualSigns() {
		return bilingualSigns;
	}
	public void setBilingualSigns(String bilingualSigns) {
		this.bilingualSigns = bilingualSigns;
	}
	public String getProtectiveEmblem() {
		return protectiveEmblem;
	}
	public void setProtectiveEmblem(String protectiveEmblem) {
		this.protectiveEmblem = protectiveEmblem;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getVideoParameter() {
		return videoParameter;
	}
	public void setVideoParameter(String videoParameter) {
		this.videoParameter = videoParameter;
	}
	public String getAudioParameter() {
		return audioParameter;
	}
	public void setAudioParameter(String audioParameter) {
		this.audioParameter = audioParameter;
	}
	public String getSignalSource() {
		return signalSource;
	}
	public void setSignalSource(String signalSource) {
		this.signalSource = signalSource;
	}
	public String getBitStream() {
		return bitStream;
	}
	public void setBitStream(String bitStream) {
		this.bitStream = bitStream;
	}
	public Integer getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	public String getTvLogo() {
		return tvLogo;
	}
	public void setTvLogo(String tvLogo) {
		this.tvLogo = tvLogo;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getCorrelation() {
		return correlation;
	}
	public void setCorrelation(String correlation) {
		this.correlation = correlation;
	}
	public String getContentDesignator() {
		return contentDesignator;
	}
	public void setContentDesignator(String contentDesignator) {
		this.contentDesignator = contentDesignator;
	}
	public String getTvDescribe() {
		return tvDescribe;
	}
	public void setTvDescribe(String tvDescribe) {
		this.tvDescribe = tvDescribe;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLineStatus() {
		return lineStatus;
	}
	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getTempTime() {
		return tempTime;
	}
	public void setTempTime(String tempTime) {
		this.tempTime = tempTime;
	}
	public Long getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
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
	private Date createDate;
	private String isDelete;
	private String tempTime;

	private Long companyid;
	// 二级运营商
	private Long operatorid;
	private String operatorname;
	// 经销商
	private Long dealerid;
	private String dealername;
	
}