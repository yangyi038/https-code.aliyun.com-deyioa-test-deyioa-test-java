package com.fs.comm.model;

import lombok.Data;

@Data
public class Autoshutdown {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getDur() {
		return dur;
	}

	public void setDur(String dur) {
		this.dur = dur;
	}

	public String getTimersetcheckdur() {
		return timersetcheckdur;
	}

	public void setTimersetcheckdur(String timersetcheckdur) {
		this.timersetcheckdur = timersetcheckdur;
	}

	public String getCountdowntime() {
		return countdowntime;
	}

	public void setCountdowntime(String countdowntime) {
		this.countdowntime = countdowntime;
	}

	public String getCountdowntip() {
		return countdowntip;
	}

	public void setCountdowntip(String countdowntip) {
		this.countdowntip = countdowntip;
	}

	public String getExcludedate() {
		return excludedate;
	}

	public void setExcludedate(String excludedate) {
		this.excludedate = excludedate;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	private Long id;

	private String starttime;

	private String endtime;

	private String dur;

	private String timersetcheckdur;

	private String countdowntime;

	private String countdowntip;

	private String excludedate;

	private String isDelete;

}