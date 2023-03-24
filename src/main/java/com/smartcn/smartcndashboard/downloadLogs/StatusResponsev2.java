package com.smartcn.smartcndashboard.downloadLogs;

import java.util.List;

public class StatusResponsev2<T> {
	private String statusMessage;
	private boolean success;
	private int statusCode;
	private long totalCount;
	private List<T> data;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "StatusResponsev2 [statusMessage=" + statusMessage + ", success=" + success + ", statusCode="
				+ statusCode + ", totalCount=" + totalCount + ", data=" + data + "]";
	}

}
