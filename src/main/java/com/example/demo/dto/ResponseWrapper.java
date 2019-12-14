package com.example.demo.dto;

public class ResponseWrapper {

	private boolean success;
	private String message;
	private Object data;	
	

	public ResponseWrapper(boolean success) {
		this(success, null);
	}

	public ResponseWrapper(boolean success, Object data) {
		this(success, null, data);
	}

	public ResponseWrapper(boolean success, String message, Object data) {
		if (null == message) {
			message = success ? "Successful" : "Failed";
		}

		if (null == data) {
			data = new Boolean(false);
		}

		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
