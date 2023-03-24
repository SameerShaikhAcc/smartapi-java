
package com.smartcn.smartcndashboard.dashboard;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "smartcn_smartcontract_note_chart")
public class DashBoardModelChart {

	@DynamoDBHashKey(attributeName = "processId")
	@DynamoDBAutoGeneratedKey
	private String processId;
	private String segmentName;
	private String yearSearched;
	private String monthSearched;
	private Double totalCustomerCount;
	private Double totalCustomerFailureCount;
	private Double totalGeneratedPdf;
	private Double totalPdfFailure;
	private Double totalEmailSentCount;
	private Double totalEmailSentSuccessCount;
	private Double totalEmailSentFailureCount;
	private Double totalEmailSentBounceCount;
	private Double totalEmailSentFailureHardBounceCount;
	private Double totalEmailSentFailureSoftBounceCount;
	private Double totalEmailSentFailureComplainCount;
	private Double totalEmailSentFailureEmailThrottlingtCount;
	private Double totalEmailSentFailureotherCount;
	private Double totalEmailSentAnalyticsCount;
	private Double totalEmailSentAnalyticsReadCount;
	private Double totalEmailSentAnalyticsClickCount;
	private String startTime;
	private String endTime;

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getYearSearched() {
		return yearSearched;
	}

	public void setYearSearched(String yearSearched) {
		this.yearSearched = yearSearched;
	}

	public String getMonthSearched() {
		return monthSearched;
	}

	public void setMonthSearched(String monthSearched) {
		this.monthSearched = monthSearched;
	}

	public Double getTotalCustomerCount() {
		return totalCustomerCount;
	}

	public void setTotalCustomerCount(Double totalCustomerCount) {
		this.totalCustomerCount = totalCustomerCount;
	}

	public Double getTotalCustomerFailureCount() {
		return totalCustomerFailureCount;
	}

	public void setTotalCustomerFailureCount(Double totalCustomerFailureCount) {
		this.totalCustomerFailureCount = totalCustomerFailureCount;
	}

	public Double getTotalGeneratedPdf() {
		return totalGeneratedPdf;
	}

	public void setTotalGeneratedPdf(Double totalGeneratedPdf) {
		this.totalGeneratedPdf = totalGeneratedPdf;
	}

	public Double getTotalPdfFailure() {
		return totalPdfFailure;
	}

	public void setTotalPdfFailure(Double totalPdfFailure) {
		this.totalPdfFailure = totalPdfFailure;
	}

	public Double getTotalEmailSentCount() {
		return totalEmailSentCount;
	}

	public void setTotalEmailSentCount(Double totalEmailSentCount) {
		this.totalEmailSentCount = totalEmailSentCount;
	}

	public Double getTotalEmailSentSuccessCount() {
		return totalEmailSentSuccessCount;
	}

	public void setTotalEmailSentSuccessCount(Double totalEmailSentSuccessCount) {
		this.totalEmailSentSuccessCount = totalEmailSentSuccessCount;
	}

	public Double getTotalEmailSentFailureCount() {
		return totalEmailSentFailureCount;
	}

	public void setTotalEmailSentFailureCount(Double totalEmailSentFailureCount) {
		this.totalEmailSentFailureCount = totalEmailSentFailureCount;
	}

	public Double getTotalEmailSentBounceCount() {
		return totalEmailSentBounceCount;
	}

	public void setTotalEmailSentBounceCount(Double totalEmailSentBounceCount) {
		this.totalEmailSentBounceCount = totalEmailSentBounceCount;
	}

	public Double getTotalEmailSentFailureHardBounceCount() {
		return totalEmailSentFailureHardBounceCount;
	}

	public void setTotalEmailSentFailureHardBounceCount(Double totalEmailSentFailureHardBounceCount) {
		this.totalEmailSentFailureHardBounceCount = totalEmailSentFailureHardBounceCount;
	}

	public Double getTotalEmailSentFailureSoftBounceCount() {
		return totalEmailSentFailureSoftBounceCount;
	}

	public void setTotalEmailSentFailureSoftBounceCount(Double totalEmailSentFailureSoftBounceCount) {
		this.totalEmailSentFailureSoftBounceCount = totalEmailSentFailureSoftBounceCount;
	}

	public Double getTotalEmailSentFailureComplainCount() {
		return totalEmailSentFailureComplainCount;
	}

	public void setTotalEmailSentFailureComplainCount(Double totalEmailSentFailureComplainCount) {
		this.totalEmailSentFailureComplainCount = totalEmailSentFailureComplainCount;
	}

	public Double getTotalEmailSentFailureEmailThrottlingtCount() {
		return totalEmailSentFailureEmailThrottlingtCount;
	}

	public void setTotalEmailSentFailureEmailThrottlingtCount(Double totalEmailSentFailureEmailThrottlingtCount) {
		this.totalEmailSentFailureEmailThrottlingtCount = totalEmailSentFailureEmailThrottlingtCount;
	}

	public Double getTotalEmailSentFailureotherCount() {
		return totalEmailSentFailureotherCount;
	}

	public void setTotalEmailSentFailureotherCount(Double totalEmailSentFailureotherCount) {
		this.totalEmailSentFailureotherCount = totalEmailSentFailureotherCount;
	}

	public Double getTotalEmailSentAnalyticsCount() {
		return totalEmailSentAnalyticsCount;
	}

	public void setTotalEmailSentAnalyticsCount(Double totalEmailSentAnalyticsCount) {
		this.totalEmailSentAnalyticsCount = totalEmailSentAnalyticsCount;
	}

	public Double getTotalEmailSentAnalyticsReadCount() {
		return totalEmailSentAnalyticsReadCount;
	}

	public void setTotalEmailSentAnalyticsReadCount(Double totalEmailSentAnalyticsReadCount) {
		this.totalEmailSentAnalyticsReadCount = totalEmailSentAnalyticsReadCount;
	}

	public Double getTotalEmailSentAnalyticsClickCount() {
		return totalEmailSentAnalyticsClickCount;
	}

	public void setTotalEmailSentAnalyticsClickCount(Double totalEmailSentAnalyticsClickCount) {
		this.totalEmailSentAnalyticsClickCount = totalEmailSentAnalyticsClickCount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "DashBoardModelChart [processId=" + processId + ", segmentName=" + segmentName + ", yearSearched="
				+ yearSearched + ", monthSearched=" + monthSearched + ", totalCustomerCount=" + totalCustomerCount
				+ ", totalCustomerFailureCount=" + totalCustomerFailureCount + ", totalGeneratedPdf="
				+ totalGeneratedPdf + ", totalPdfFailure=" + totalPdfFailure + ", totalEmailSentCount="
				+ totalEmailSentCount + ", totalEmailSentSuccessCount=" + totalEmailSentSuccessCount
				+ ", totalEmailSentFailureCount=" + totalEmailSentFailureCount + ", totalEmailSentBounceCount="
				+ totalEmailSentBounceCount + ", totalEmailSentFailureHardBounceCount="
				+ totalEmailSentFailureHardBounceCount + ", totalEmailSentFailureSoftBounceCount="
				+ totalEmailSentFailureSoftBounceCount + ", totalEmailSentFailureComplainCount="
				+ totalEmailSentFailureComplainCount + ", totalEmailSentFailureEmailThrottlingtCount="
				+ totalEmailSentFailureEmailThrottlingtCount + ", totalEmailSentFailureotherCount="
				+ totalEmailSentFailureotherCount + ", totalEmailSentAnalyticsCount=" + totalEmailSentAnalyticsCount
				+ ", totalEmailSentAnalyticsReadCount=" + totalEmailSentAnalyticsReadCount
				+ ", totalEmailSentAnalyticsClickCount=" + totalEmailSentAnalyticsClickCount + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

}