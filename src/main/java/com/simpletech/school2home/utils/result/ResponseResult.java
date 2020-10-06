package com.simpletech.school2home.utils.result;


import java.io.Serializable;

/**
 * @项目名称： cywebnew
 * @类名称： ResponseResult
 * @类描述： 前端请求响应结果,code:编码,message:描述,obj对象，可以是单个数据对象，数据列表或者PageInfo
 * @创建时间： 2019年5月28日10:57:24
 * @version:
 */
public class ResponseResult implements Serializable {
	
	private static final long serialVersionUID = 7285065610386199394L;

	private String code;
	private String message;
	private Object obj;
	
	public ResponseResult() {
		this.code = IResponseStatus.ResponseStatus.SUCCESS.getCode();
		this.message = IResponseStatus.ResponseStatus.SUCCESS.getMessage();
	}
	
	public ResponseResult(IResponseStatus statusMessage){
		this.code = statusMessage.getCode();
		this.message = statusMessage.getMessage();
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override public String toString() {
		return "ResponseResult{" + "code='" + code + '\'' + ", message='"
				+ message + '\'' + ", obj=" + obj + '}';
	}
}
