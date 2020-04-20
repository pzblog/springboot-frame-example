package com.example.boot.core.result;

import java.io.Serializable;

public class ResultMsg<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**返回状态码*/
	private int code;
	
	/**返回描述信息(接口描述)*/
	private String msg;
	
	/**使用泛型T返回前端想要的数据(任意数据类型)*/
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResultMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ResultMsg(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
}
