package com.smartcn.smartcndashboard.adminlogin;

import java.util.List;


public class AdminDTO {
	List<AdminLoginModel> adminModel;
	Integer size;
	public List<AdminLoginModel> getAdminModel() {
		return adminModel;
	}
	public void setAdminModel(List<AdminLoginModel> adminModel) {
		this.adminModel = adminModel;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "AdminDTO [adminModel=" + adminModel + ", size=" + size + "]";
	}
	
}
