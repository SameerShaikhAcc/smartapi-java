package com.smartcn.smartcndashboard.listprocessstatus;

public interface ProcessStatusReportService {

	ProcessStatusDTO fetchAll(String segmentName, String status, String startDate, String endDate, long skip,
			long limit);

	ProcessStatusDTO fetchWODate(String segmentName, String status, long skip, long limit);

	ProcessStatusDTO fetchWOValues(long skip, long limit);

	ProcessStatusDTO fetchWOSegment(String status, String startDate, String endDate, long skip, long limit);

	ProcessStatusDTO fetchWOfileName(String segmentName, String status, String startDate, String endDate, long skip, long limit);

	ProcessStatusDTO fetchWOStatus(String segmentName, String startDate, String endDate, long skip, long limit);

	ProcessStatusDTO fetchWithFN(long skip, long limit);

	ProcessStatusDTO fetchWithStatus(String status, long skip, long limit);

	ProcessStatusDTO fetchWithSegment(String segmentName, long skip, long limit);

	ProcessStatusDTO fetchWithDate(String startDate, String endDate, long skip, long limit);

	ProcessStatusDTO fetchWOFNSG(String status, String startDate, String endDate, long skip, long limit);

	ProcessStatusDTO fetchWOSGST(String startDate, String endDate, long skip, long limit);

	ProcessStatusDTO fetchWOSGDate(String status, long skip, long limit);

	ProcessStatusDTO fetchWOFNST(String segmentName, String startDate, String endDate, long skip, long limit);

	ProcessStatusDTO fetchWOFNDate(String segmentName, String status, long skip, long limit);

	ProcessStatusDTO fetchWOSTDate(String segmentName, long skip, long limit);

	

}
