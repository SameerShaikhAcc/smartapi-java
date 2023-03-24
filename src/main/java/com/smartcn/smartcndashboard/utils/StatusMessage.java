package com.smartcn.smartcndashboard.utils;

import java.util.List;

public class StatusMessage<T> {

	private boolean success;
	private String message;
	private int totalCount;
	List<T> ListData;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getListData() {
		return ListData;
	}

	public void setListData(List<T> listData) {
		ListData = listData;
	}

	@Override
	public String toString() {
		return "StatusMessage [success=" + success + ", message=" + message + ", totalCount=" + totalCount
				+ ", ListData=" + ListData + "]";
	}

}
