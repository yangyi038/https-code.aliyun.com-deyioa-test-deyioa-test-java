package com.fs.comm.model;

public class RegisterMessage {
	
	private String userid;
	private int status;
	private String password;
	private String hostName;
	private String hotelid;
	private String userGroup;
	private String iptvGroupCode;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHotelid() {
		return hotelid;
	}
	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	public String getIptvGroupCode() {
		return iptvGroupCode;
	}
	public void setIptvGroupCode(String iptvGroupCode) {
		this.iptvGroupCode = iptvGroupCode;
	}
	
	
	

}
