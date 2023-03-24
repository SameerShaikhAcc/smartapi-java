package com.smartcn.smartcndashboard.dashboardchart;

import java.util.List;

import com.smartcn.smartcndashboard.dashboard.DashBoardModelChart;


public interface DashBoardChartService {

//	List<DashBoardChartDTO> fetchMonthlyRecords(List<DashBoardModel> yearList);

	List<DashBoardChartDTO> fetchRecords(List<DashBoardModelChart> yearList);
}