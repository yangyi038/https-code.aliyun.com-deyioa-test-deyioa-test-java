package com.fs.app.common;

public class I_Result<T> {
	
	/**
	 * 返回数据成功
	 */
	public static final int CODE_OK = 102;
	/**
	 * 参数为空
	 */
	public static final int CODE_NULL = 101;
	/**
	 * 没有该数据
	 */
	public static final int CODE_WITHOUT = 103;
	/**
	 * token数据不对
	 */
	public static final int CODE_TOKEN = 104;
	
	/**
	 * 返回失败
	 */
	public static final int CODE_FAIL = -102;
	/**
	 * 返回异常
	 */
	public static final int CODE_EXCEPTION = 500;
	
	
	
	private int code = 102;
	private String info;
	private T data;

	public void setResult(int code, String info) {
		this.code = code;
		this.info = info;
	}

	public int getCode() {
		return this.code;
	}

	public String getInfo() {
		return this.info;
	}

	public T getData() {
		return this.data;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof I_Result))
			return false;
		I_Result other = (I_Result) o;
		if (!other.canEqual(this))
			return false;
		if (getCode() != other.getCode())
			return false;
		Object this$msg = getInfo();
		Object other$msg = other.getInfo();
		if (this$msg == null ? other$msg != null : !this$msg.equals(other$msg))
			return false;
		Object this$data = getData();
		Object other$data = other.getData();
		return this$data == null ? other$data == null : this$data.equals(other$data);
	}

	protected boolean canEqual(Object other) {
		return other instanceof I_Result;
	}

	public int hashCode() {
		int PRIME = 59;
		int result = 1;
		result = result * 59 + getCode();
		Object $msg = getInfo();
		result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
		Object $data = getData();
		result = result * 59 + ($data == null ? 43 : $data.hashCode());
		return result;
	}

	public String toString() {
		return "Result(code=" + getCode() + ", msg=" + getInfo() + ", data=" + getData() + ")";
	}

}
