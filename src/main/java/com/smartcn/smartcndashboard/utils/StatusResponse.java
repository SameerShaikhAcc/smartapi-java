package com.smartcn.smartcndashboard.utils;

public class StatusResponse {

	int statusCode;
	String message;
	String name;
	String token;
	boolean success;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "StatusResponse [statusCode=" + statusCode + ", message=" + message + ", name=" + name + ", token="
				+ token + ", success=" + success + "]";
	}

}
