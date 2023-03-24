package com.smartcn.smartcndashboard.dashboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

@Service
public class DashBoardServiceImpl implements DashBoardService {
	Double totalCustomerCount = 0.0;
	Double totalCustomerFailureCount = 0.0;
	Double totalGeneratedPdf = 0.0;
	Double totalPdfFailure = 0.0;
	Double totalEmailSentCount = 0.0;
	Double totalEmailSentSuccessCount = 0.0;
	Double totalEmailSentBounceCount;
	Double totalEmailSentFailureHardBounceCount = 0.0;
	Double totalEmailSentFailureSoftBounceCount = 0.0;
	Double totalEmailSentFailureComplainCount = 0.0;
	Double totalEmailSentFailureEmailThrottlingtCount = 0.0;
	Double totalEmailSentFailureotherCount = 0.0;
	Double totalEmailSentAnalyticsCount = 0.0;
	Double totalEmailSentAnalyticsReadCount = 0.0;
	Double totalEmailSentAnalyticsClickCount = 0.0;

	public DashBoardServiceImpl() {
		super();
	}

	public DashBoardServiceImpl(Double totalCustomerCount, Double totalCustomerFailureCount, Double totalGeneratedPdf,
			Double totalPdfFailure, Double totalEmailSentCount, Double totalEmailSentSuccessCount,
			Double totalEmailSentBounceCount, Double totalEmailSentFailureHardBounceCount,
			Double totalEmailSentFailureSoftBounceCount, Double totalEmailSentFailureComplainCount,
			Double totalEmailSentFailureEmailThrottlingtCount, Double totalEmailSentFailureotherCount,
			Double totalEmailSentAnalyticsCount, Double totalEmailSentAnalyticsReadCount,
			Double totalEmailSentAnalyticsClickCount) {
		super();
		this.totalCustomerCount = totalCustomerCount;
		this.totalCustomerFailureCount = totalCustomerFailureCount;
		this.totalGeneratedPdf = totalGeneratedPdf;
		this.totalPdfFailure = totalPdfFailure;
		this.totalEmailSentCount = totalEmailSentCount;
		this.totalEmailSentSuccessCount = totalEmailSentSuccessCount;
		this.totalEmailSentBounceCount = totalEmailSentBounceCount;
		this.totalEmailSentFailureHardBounceCount = totalEmailSentFailureHardBounceCount;
		this.totalEmailSentFailureSoftBounceCount = totalEmailSentFailureSoftBounceCount;
		this.totalEmailSentFailureComplainCount = totalEmailSentFailureComplainCount;
		this.totalEmailSentFailureEmailThrottlingtCount = totalEmailSentFailureEmailThrottlingtCount;
		this.totalEmailSentFailureotherCount = totalEmailSentFailureotherCount;
		this.totalEmailSentAnalyticsCount = totalEmailSentAnalyticsCount;
		this.totalEmailSentAnalyticsReadCount = totalEmailSentAnalyticsReadCount;
		this.totalEmailSentAnalyticsClickCount = totalEmailSentAnalyticsClickCount;
	}

	@Override
	public DashBoardModel fetchDailyRecords(List<DashBoardModel> betweenRecords) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		Double sumTotalCustomerCount = 0.0;
		Double sumTotalCustomerFailureCount = 0.0;
		Double sumTotalGeneratedPdf = 0.0;
		Double sumTotalPdfFailure = 0.0;
		Double sumTotalEmailSentCount = 0.0;
		Double sumTotalEmailSentSuccessCount = 0.0;
		Double sumTotalEmailSentFailureCount = 0.0;
		Double sumTotalEmailSentBounceCount = 0.0;
		Double sumTotalEmailSentFailureHardBounceCount = 0.0;
		Double sumTotalEmailSentFailureSoftBounceCount = 0.0;
		Double sumTotalEmailSentFailureComplainCount = 0.0;
		Double sumTotalEmailSentFailureEmailThrottlingtCount = 0.0;
		Double sumTotalEmailSentFailureotherCount = 0.0;
		Double sumTotalEmailSentAnalyticsCount = 0.0;
		Double sumTotalEmailSentAnalyticsReadCount = 0.0;
		Double sumTotalEmailSentAnalyticsClickCount = 0.0;
		String timeStart = null;
		String timeEnd = null;
		DashBoardModel dashBoardModel = new DashBoardModel();
		for (DashBoardModel boardModel : betweenRecords) {
			if (boardModel.getTotalCustomerCount() != 0) {
				sumTotalCustomerCount = sumTotalCustomerCount + boardModel.getTotalCustomerCount();
				;
//                sumTotalCustomerFailureCount = sumTotalCustomerCount-sumTotalEmailSentCount +boardModel.getTotalCustomerFailureCount();;
				sumTotalCustomerFailureCount = sumTotalCustomerFailureCount + boardModel.getTotalCustomerFailureCount();
				;

				// sumTotalCustomerFailureCount=sumTotalEmailSentSuccessCount-sumTotalGeneratedPdf;;
				sumTotalGeneratedPdf = sumTotalGeneratedPdf + boardModel.getTotalGeneratedPdf();
				;
				sumTotalPdfFailure = sumTotalPdfFailure + boardModel.getTotalPdfFailure();
				;

				sumTotalEmailSentSuccessCount = sumTotalEmailSentSuccessCount
						+ boardModel.getTotalEmailSentSuccessCount();
				;
				sumTotalEmailSentFailureHardBounceCount = sumTotalEmailSentFailureHardBounceCount
						+ boardModel.getTotalEmailSentFailureHardBounceCount();
				;
				sumTotalEmailSentFailureSoftBounceCount = sumTotalEmailSentFailureSoftBounceCount
						+ boardModel.getTotalEmailSentFailureSoftBounceCount();
				;
				sumTotalEmailSentBounceCount = sumTotalEmailSentFailureHardBounceCount
						+ sumTotalEmailSentFailureSoftBounceCount;
				sumTotalEmailSentFailureComplainCount = sumTotalEmailSentFailureComplainCount
						+ boardModel.getTotalEmailSentFailureComplainCount();
				;
				sumTotalEmailSentFailureEmailThrottlingtCount = sumTotalEmailSentFailureEmailThrottlingtCount
						+ boardModel.getTotalEmailSentFailureEmailThrottlingtCount();
				;
				sumTotalEmailSentFailureotherCount = sumTotalEmailSentFailureotherCount
						+ boardModel.getTotalEmailSentFailureotherCount();
				;
				// sumTotalEmailSentFailureotherCount =
				// sumTotalEmailSentFailureotherCount+sumTotalEmailSentFailureSoftBounceCount+sumTotalEmailSentFailureHardBounceCount;;
//              sumTotalEmailSentFailureCount = sumTotalEmailSentFailureCount + sumTotalEmailSentBounceCount + sumTotalEmailSentFailureEmailThrottlingtCount + sumTotalEmailSentFailureotherCount;
				sumTotalEmailSentFailureCount = sumTotalEmailSentBounceCount
						+ sumTotalEmailSentFailureEmailThrottlingtCount + sumTotalEmailSentFailureotherCount;
				sumTotalEmailSentCount = sumTotalEmailSentSuccessCount + sumTotalEmailSentFailureCount;
				;

				sumTotalEmailSentAnalyticsReadCount = sumTotalEmailSentAnalyticsReadCount
						+ boardModel.getTotalEmailSentAnalyticsReadCount();
				;
				sumTotalEmailSentAnalyticsClickCount = sumTotalEmailSentAnalyticsClickCount
						+ boardModel.getTotalEmailSentAnalyticsClickCount();
				;
				sumTotalEmailSentAnalyticsCount = sumTotalEmailSentAnalyticsClickCount
						+ sumTotalEmailSentAnalyticsReadCount;
				;
				// sumTotalEmailSentAnalyticsCount = sumTotalEmailSentAnalyticsCount +
				// sumTotalEmailSentAnalyticsReadCount + sumTotalEmailSentAnalyticsClickCount;
				dashBoardModel.setTotalCustomerCount(sumTotalCustomerCount);
				dashBoardModel.setTotalCustomerFailureCount(sumTotalCustomerFailureCount);
				dashBoardModel.setTotalGeneratedPdf(sumTotalGeneratedPdf);
				dashBoardModel.setTotalPdfFailure(sumTotalPdfFailure);
//                dashBoardModel.setTotalEmailSentCount(sumTotalEmailSentCount);
				dashBoardModel.setTotalEmailSentSuccessCount(sumTotalEmailSentSuccessCount);
				dashBoardModel.setTotalEmailSentFailureCount(sumTotalEmailSentFailureCount);
//              dashBoardModel.setTotalEmailSentFailureCount(sumTotalEmailSentFailureotherCount);
				dashBoardModel.setTotalEmailSentBounceCount(sumTotalEmailSentBounceCount);
				dashBoardModel.setTotalEmailSentFailureHardBounceCount(sumTotalEmailSentFailureHardBounceCount);
				dashBoardModel.setTotalEmailSentFailureSoftBounceCount(sumTotalEmailSentFailureSoftBounceCount);
				dashBoardModel.setTotalEmailSentFailureComplainCount(sumTotalEmailSentFailureComplainCount);
				dashBoardModel
						.setTotalEmailSentFailureEmailThrottlingtCount(sumTotalEmailSentFailureEmailThrottlingtCount);
				dashBoardModel.setTotalEmailSentCount(sumTotalEmailSentCount);
				dashBoardModel.setTotalEmailSentFailureotherCount(sumTotalEmailSentFailureotherCount);
				dashBoardModel.setTotalEmailSentAnalyticsCount(sumTotalEmailSentAnalyticsCount);
				dashBoardModel.setTotalEmailSentAnalyticsReadCount(sumTotalEmailSentAnalyticsReadCount);
				dashBoardModel.setTotalEmailSentAnalyticsClickCount(sumTotalEmailSentAnalyticsClickCount);
				dashBoardModel.setStartTime(boardModel.getStartTime());
				dashBoardModel.setEndTime(boardModel.getEndTime());
				timeStart = boardModel.getStartTime();
				timeEnd = boardModel.getEndTime();
			}
		}
		try {
			// Parsing the Time Period
			if (timeStart != null) {
				Date date1 = simpleDateFormat.parse(timeStart);
				Date date2 = simpleDateFormat.parse(timeEnd);
				// Calculating the difference in milliseconds
				long differenceInMilliSeconds = Math.abs(date2.getTime() - date1.getTime());
				// Calculating the difference in Hours
				long differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
				// Calculating the difference in Minutes
				long differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60;
				// Calculating the difference in Seconds
				long differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;
				String totalTimeDifference = differenceInHours + ":" + differenceInMinutes + ":" + differenceInSeconds;
				dashBoardModel.setTotalProcessTime(totalTimeDifference);
			}
			return dashBoardModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}