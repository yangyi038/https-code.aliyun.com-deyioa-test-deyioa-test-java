package com.fs.comm.enums;

/**
 * 节目类型
 * 
 * @author pzj
 *
 */
public enum ProgramType {
	// 节目类型
	// 1：连续剧
	// 2：影片
	// 3：图片
	// 4：字幕
	// 5：应用
	// 6：栏目
	// 14：图文信息

	连续剧(1, "连续剧"), 影片(2, "影片"), 图片(3, "图片"), 字幕(4, "字幕"), 应用(5, "应用"), 栏目(6, "栏目"), 图文信息(14, "图文信息");

	private int value;
	private String name;

	private ProgramType(int value, String name) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public static ProgramType findByValue(int value) {
		for (ProgramType each : values()) {
			if (each.value == value) {
				return each;
			}
		}
		return null;
	}

	public static ProgramType findByName(String name) {
		for (ProgramType each : values()) {
			if (each.name.equals(name)) {
				return each;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "[" + this.value + ":" + this.name + "]";
	}

}
