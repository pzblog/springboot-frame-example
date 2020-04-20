package com.example.boot.core.error;

public enum CommonErrorMsg implements ErrorMsg{
	/**9999,标识系统运行异常*/
	SYSTEM_ERROR(500,"系统异常"),
	ILLEGE_ERROR(204,"非法请求"),
	CUSTOM_ERROR(202,"请求异常,请稍后再试"),
	SUCCESS_CODE(200,"成功")
	;
	/**标识*/
	private int code;
	/**信息*/
	private String message;
	
	
	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}
	
	private CommonErrorMsg(int code, String message) {
		this.code = code;
		this.message = message;
	}

}
