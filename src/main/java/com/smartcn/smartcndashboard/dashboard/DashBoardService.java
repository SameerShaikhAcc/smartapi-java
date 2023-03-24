package com.smartcn.smartcndashboard.dashboard;

import java.util.List;

public interface DashBoardService {

	DashBoardModel fetchDailyRecords(List<DashBoardModel> betweenRecords);

//	DashBoardModel fetchRecords(List<DashBoardModel> betweenRecords);

//	DashBoardModel fetchRecordsMonthBefore(List<DashBoardModel> betweenRecords2);

}