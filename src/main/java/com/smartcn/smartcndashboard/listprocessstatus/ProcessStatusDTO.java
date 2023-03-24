package com.smartcn.smartcndashboard.listprocessstatus;

import java.util.List;

import com.smartcn.smartcndashboard.dashboard.DashBoardModel;

public class ProcessStatusDTO {

	List<DashBoardModel> dashBoardModel;
	Integer size;

	public List<DashBoardModel> getDashBoardModel() {
		return dashBoardModel;
	}

	public void setDashBoardModel(List<DashBoardModel> dashBoardModel) {
		this.dashBoardModel = dashBoardModel;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ProcessStatusDTO [dashBoardModel=" + dashBoardModel + ", size=" + size + "]";
	}

}
