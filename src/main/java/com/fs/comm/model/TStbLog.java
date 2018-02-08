package com.fs.comm.model;

import java.util.Date;

import lombok.Data;

/**
 * 终端日志表
 * 
 * @author pzj
 *
 */
@Data
public class TStbLog {

	public String getStbtoken() {
		return stbtoken;
	}

	public void setStbtoken(String stbtoken) {
		this.stbtoken = stbtoken;
	}

	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public String getStbnum() {
		return stbnum;
	}

	public void setStbnum(String stbnum) {
		this.stbnum = stbnum;
	}

	public Integer getTerminaltype() {
		return terminaltype;
	}

	public void setTerminaltype(Integer terminaltype) {
		this.terminaltype = terminaltype;
	}

	public Integer getStreamstatus() {
		return streamstatus;
	}

	public void setStreamstatus(Integer streamstatus) {
		this.streamstatus = streamstatus;
	}

	public Date getLastonlinetime() {
		return lastonlinetime;
	}

	public void setLastonlinetime(Date lastonlinetime) {
		this.lastonlinetime = lastonlinetime;
	}

	public String getLastonlinetimeStr() {
		return lastonlinetimeStr;
	}

	public void setLastonlinetimeStr(String lastonlinetimeStr) {
		this.lastonlinetimeStr = lastonlinetimeStr;
	}

	public String getLanip() {
		return lanip;
	}

	public void setLanip(String lanip) {
		this.lanip = lanip;
	}

	public String getWanip() {
		return wanip;
	}

	public void setWanip(String wanip) {
		this.wanip = wanip;
	}

	public String getStbid() {
		return stbid;
	}

	public void setStbid(String stbid) {
		this.stbid = stbid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getWifimac() {
		return wifimac;
	}

	public void setWifimac(String wifimac) {
		this.wifimac = wifimac;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public String getLogintimeStr() {
		return logintimeStr;
	}

	public void setLogintimeStr(String logintimeStr) {
		this.logintimeStr = logintimeStr;
	}

	public Date getOuttime() {
		return outtime;
	}

	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}

	public String getOuttimeStr() {
		return outtimeStr;
	}

	public void setOuttimeStr(String outtimeStr) {
		this.outtimeStr = outtimeStr;
	}

	public String getApkversion() {
		return apkversion;
	}

	public void setApkversion(String apkversion) {
		this.apkversion = apkversion;
	}

	public Integer getOnlinestatus() {
		return onlinestatus;
	}

	public void setOnlinestatus(Integer onlinestatus) {
		this.onlinestatus = onlinestatus;
	}

	public String getRomversion() {
		return romversion;
	}

	public void setRomversion(String romversion) {
		this.romversion = romversion;
	}

	public String getRomfirmware() {
		return romfirmware;
	}

	public void setRomfirmware(String romfirmware) {
		this.romfirmware = romfirmware;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	private String stbtoken;

	// 运营商ID
	private Long companyid;

	private String stbnum;

	private Integer terminaltype;

	private Integer streamstatus;

	// 上次心跳时间
	private Date lastonlinetime;
	private String lastonlinetimeStr;

	private String lanip;

	private String wanip;

	private String stbid;

	private String mac;

	private String wifimac;

	// 登录时间
	private Date logintime;
	private String logintimeStr;
	// 退出时间
	private Date outtime;
	private String outtimeStr;

	private String apkversion;

	private Integer onlinestatus;

	private String romversion;

	private String romfirmware;

	private String apptype;

}