package com.smartcn.smartcndashboard.dashboardchart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.smartcn.smartcndashboard.dashboard.DashBoardModelChart;


@Service
public class DashBoardChartServiceimpl implements DashBoardChartService {

	@Override
	public List<DashBoardChartDTO> fetchRecords(List<DashBoardModelChart> yearList) {

		Double totalCustomerCount = 0.0;
		Double totalCustomerFailureCount = 0.0;
		Double totalGeneratedPdf = 0.0;
		Double totalPdfFailure = 0.0;
		Double totalEmailSentCount = 0.0;
		Double totalEmailSentSuccessCount = 0.0;
		Double totalEmailSentFailureCount = 0.0;
		Double totalEmailSentBounceCount = 0.0;
		Double totalEmailSentFailureHardBounceCount = 0.0;
		Double totalEmailSentFailureSoftBounceCount = 0.0;
		Double totalEmailSentFailureComplainCount = 0.0;
		Double totalEmailSentFailureEmailThrottlingtCount = 0.0;
		Double totalEmailSentFailureotherCount = 0.0;
		Double totalEmailSentAnalyticsCount = 0.0;
		Double totalEmailSentAnalyticsReadCount = 0.0;
		Double totalEmailSentAnalyticsClickCount = 0.0;

		DashBoardChartDTO dashBoardChartDTO = new DashBoardChartDTO();
		DashBoardModelChart janChart = new DashBoardModelChart();
		DashBoardModelChart febChart = new DashBoardModelChart();
		DashBoardModelChart marChart = new DashBoardModelChart();
		DashBoardModelChart aprChart = new DashBoardModelChart();
		DashBoardModelChart mayChart = new DashBoardModelChart();
		DashBoardModelChart junChart = new DashBoardModelChart();
		DashBoardModelChart julyChart = new DashBoardModelChart();
		DashBoardModelChart augChart = new DashBoardModelChart();
		DashBoardModelChart sepChart = new DashBoardModelChart();
		DashBoardModelChart octChart = new DashBoardModelChart();
		DashBoardModelChart novChart = new DashBoardModelChart();
		DashBoardModelChart decChart = new DashBoardModelChart();

		totalCustomerCount = yearList.stream().filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "01".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		janChart.setTotalCustomerCount(totalCustomerCount);
		janChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		janChart.setTotalGeneratedPdf(totalGeneratedPdf);
		janChart.setTotalPdfFailure(totalPdfFailure);
		janChart.setTotalEmailSentCount(totalEmailSentCount);
		janChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		janChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		janChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		janChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		janChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		janChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		janChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		janChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		janChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		janChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		janChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setJan(janChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "02".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		febChart.setTotalCustomerCount(totalCustomerCount);
		febChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		febChart.setTotalGeneratedPdf(totalGeneratedPdf);
		febChart.setTotalPdfFailure(totalPdfFailure);
		febChart.setTotalEmailSentCount(totalEmailSentCount);
		febChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		febChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		febChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		febChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		febChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		febChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		febChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		febChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		febChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		febChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		febChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);
		dashBoardChartDTO.setFeb(febChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "03".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		marChart.setTotalCustomerCount(totalCustomerCount);
		marChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		marChart.setTotalGeneratedPdf(totalGeneratedPdf);
		marChart.setTotalPdfFailure(totalPdfFailure);
		marChart.setTotalEmailSentCount(totalEmailSentCount);
		marChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		marChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		marChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		marChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		marChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		marChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		marChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		marChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		marChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		marChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		marChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setMar(marChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "04".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		aprChart.setTotalCustomerCount(totalCustomerCount);
		aprChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		aprChart.setTotalGeneratedPdf(totalGeneratedPdf);
		aprChart.setTotalPdfFailure(totalPdfFailure);
		aprChart.setTotalEmailSentCount(totalEmailSentCount);
		aprChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		aprChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		aprChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		aprChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		aprChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		aprChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		aprChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		aprChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		aprChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		aprChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		aprChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setApr(aprChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "05".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		mayChart.setTotalCustomerCount(totalCustomerCount);
		mayChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		mayChart.setTotalGeneratedPdf(totalGeneratedPdf);
		mayChart.setTotalPdfFailure(totalPdfFailure);
		mayChart.setTotalEmailSentCount(totalEmailSentCount);
		mayChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		mayChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		mayChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		mayChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		mayChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		mayChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		mayChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		mayChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		mayChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		mayChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		mayChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setMay(mayChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "06".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		junChart.setTotalCustomerCount(totalCustomerCount);
		junChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		junChart.setTotalGeneratedPdf(totalGeneratedPdf);
		junChart.setTotalPdfFailure(totalPdfFailure);
		junChart.setTotalEmailSentCount(totalEmailSentCount);
		junChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		junChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		junChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		junChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		junChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		junChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		junChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		junChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		junChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		junChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		junChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setJun(junChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "07".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		julyChart.setTotalCustomerCount(totalCustomerCount);
		julyChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		julyChart.setTotalGeneratedPdf(totalGeneratedPdf);
		julyChart.setTotalPdfFailure(totalPdfFailure);
		julyChart.setTotalEmailSentCount(totalEmailSentCount);
		julyChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		julyChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		julyChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		julyChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		julyChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		julyChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		julyChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		julyChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		julyChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		julyChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		julyChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setJul(julyChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "08".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		augChart.setTotalCustomerCount(totalCustomerCount);
		augChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		augChart.setTotalGeneratedPdf(totalGeneratedPdf);
		augChart.setTotalPdfFailure(totalPdfFailure);
		augChart.setTotalEmailSentCount(totalEmailSentCount);
		augChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		augChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		augChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		augChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		augChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		augChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		augChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		augChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		augChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		augChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		augChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setAug(augChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "09".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		sepChart.setTotalCustomerCount(totalCustomerCount);
		sepChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		sepChart.setTotalGeneratedPdf(totalGeneratedPdf);
		sepChart.setTotalPdfFailure(totalPdfFailure);
		sepChart.setTotalEmailSentCount(totalEmailSentCount);
		sepChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		sepChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		sepChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		sepChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		sepChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		sepChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		sepChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		sepChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		sepChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		sepChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		sepChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setSep(sepChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "10".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		octChart.setTotalCustomerCount(totalCustomerCount);
		octChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		octChart.setTotalGeneratedPdf(totalGeneratedPdf);
		octChart.setTotalPdfFailure(totalPdfFailure);
		octChart.setTotalEmailSentCount(totalEmailSentCount);
		octChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		octChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		octChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		octChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		octChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		octChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		octChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		octChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		octChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		octChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		octChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setOct(octChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "11".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		novChart.setTotalCustomerCount(totalCustomerCount);
		novChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		novChart.setTotalGeneratedPdf(totalGeneratedPdf);
		novChart.setTotalPdfFailure(totalPdfFailure);
		novChart.setTotalEmailSentCount(totalEmailSentCount);
		novChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		novChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		novChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		novChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		novChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		novChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		novChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		novChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		novChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		novChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		novChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setNov(novChart);
		
		totalCustomerCount = yearList.stream().filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerCount));
		totalCustomerFailureCount = yearList.stream().filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalGeneratedPdf = yearList.stream().filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalCustomerFailureCount));
		totalPdfFailure = yearList.stream().filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalPdfFailure));
		totalEmailSentCount = yearList.stream().filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentCount));
		totalEmailSentSuccessCount = yearList.stream().filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentSuccessCount));
		totalEmailSentFailureCount = yearList.stream().filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureCount));
		totalEmailSentBounceCount = yearList.stream().filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentBounceCount));
		totalEmailSentFailureHardBounceCount = yearList.stream()
				.filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureHardBounceCount));
		totalEmailSentFailureSoftBounceCount = yearList.stream()
				.filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureSoftBounceCount));
		totalEmailSentFailureComplainCount = yearList.stream()
				.filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureComplainCount));
		totalEmailSentFailureEmailThrottlingtCount = yearList.stream()
				.filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureEmailThrottlingtCount));
		totalEmailSentFailureotherCount = yearList.stream()
				.filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentFailureotherCount));
		totalEmailSentAnalyticsCount = yearList.stream().filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsCount));
		totalEmailSentAnalyticsReadCount = yearList.stream()
				.filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsReadCount));
		totalEmailSentAnalyticsClickCount = yearList.stream()
				.filter(segmentName -> "12".equals(segmentName.getMonthSearched()))
				.collect(Collectors.summingDouble(DashBoardModelChart::getTotalEmailSentAnalyticsClickCount));

		decChart.setTotalCustomerCount(totalCustomerCount);
		decChart.setTotalCustomerFailureCount(totalCustomerFailureCount);
		decChart.setTotalGeneratedPdf(totalGeneratedPdf);
		decChart.setTotalPdfFailure(totalPdfFailure);
		decChart.setTotalEmailSentCount(totalEmailSentCount);
		decChart.setTotalEmailSentSuccessCount(totalEmailSentSuccessCount);
		decChart.setTotalEmailSentFailureCount(totalEmailSentFailureCount);
		decChart.setTotalEmailSentBounceCount(totalEmailSentBounceCount);
		decChart.setTotalEmailSentFailureHardBounceCount(totalEmailSentFailureHardBounceCount);
		decChart.setTotalEmailSentFailureSoftBounceCount(totalEmailSentFailureSoftBounceCount);
		decChart.setTotalEmailSentFailureComplainCount(totalEmailSentFailureComplainCount);
		decChart.setTotalEmailSentFailureEmailThrottlingtCount(totalEmailSentFailureEmailThrottlingtCount);
		decChart.setTotalEmailSentFailureotherCount(totalEmailSentFailureotherCount);
		decChart.setTotalEmailSentAnalyticsCount(totalEmailSentAnalyticsCount);
		decChart.setTotalEmailSentAnalyticsReadCount(totalEmailSentAnalyticsReadCount);
		decChart.setTotalEmailSentAnalyticsClickCount(totalEmailSentAnalyticsClickCount);

		dashBoardChartDTO.setDec(decChart);
		

		List<DashBoardChartDTO> boardChartDTOs = new ArrayList<DashBoardChartDTO>();
		boardChartDTOs.add(dashBoardChartDTO);

		return boardChartDTOs;
	}

}
