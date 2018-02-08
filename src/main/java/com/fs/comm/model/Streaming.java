package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 流媒体配置表
 * @author pzj
 *
 */
@Data
public class Streaming {
	
    private Long id;
    private String programmeNum;
    private String firmware;
    private String softwareVersion;
    private Date productionData;
    private Date startTime;
    private Date overTime;
    private String rootPassword;
    private String hotelPassword;
    private String edgeIp;
    private Date lastTime;
    private String latsIp;
    
    
    private Long companyId;
    private String companyname;
    
    private String isDelete;

    private String tempProductionData;
    private String tempStartTime;
    private String tempOverTime;
    private String tempLastTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProgrammeNum() {
		return programmeNum;
	}
	public void setProgrammeNum(String programmeNum) {
		this.programmeNum = programmeNum;
	}
	public String getFirmware() {
		return firmware;
	}
	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	public Date getProductionData() {
		return productionData;
	}
	public void setProductionData(Date productionData) {
		this.productionData = productionData;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getOverTime() {
		return overTime;
	}
	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}
	public String getRootPassword() {
		return rootPassword;
	}
	public void setRootPassword(String rootPassword) {
		this.rootPassword = rootPassword;
	}
	public String getHotelPassword() {
		return hotelPassword;
	}
	public void setHotelPassword(String hotelPassword) {
		this.hotelPassword = hotelPassword;
	}
	public String getEdgeIp() {
		return edgeIp;
	}
	public void setEdgeIp(String edgeIp) {
		this.edgeIp = edgeIp;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getLatsIp() {
		return latsIp;
	}
	public void setLatsIp(String latsIp) {
		this.latsIp = latsIp;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getTempProductionData() {
		return tempProductionData;
	}
	public void setTempProductionData(String tempProductionData) {
		this.tempProductionData = tempProductionData;
	}
	public String getTempStartTime() {
		return tempStartTime;
	}
	public void setTempStartTime(String tempStartTime) {
		this.tempStartTime = tempStartTime;
	}
	public String getTempOverTime() {
		return tempOverTime;
	}
	public void setTempOverTime(String tempOverTime) {
		this.tempOverTime = tempOverTime;
	}
	public String getTempLastTime() {
		return tempLastTime;
	}
	public void setTempLastTime(String tempLastTime) {
		this.tempLastTime = tempLastTime;
	}

    
}