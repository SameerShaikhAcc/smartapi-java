package com.smartcn.smartcndashboard.dashboard;

public class DashBoardModelDTO {

	DashBoardModel display;
	DashBoardModelChart compareOne;
	DashBoardModelChart compareTwo;

	public DashBoardModel getDisplay() {
		return display;
	}

	public void setDisplay(DashBoardModel display) {
		this.display = display;
	}

	public DashBoardModelChart getCompareOne() {
		return compareOne;
	}

	public void setCompareOne(DashBoardModelChart compareOne) {
		this.compareOne = compareOne;
	}

	public DashBoardModelChart getCompareTwo() {
		return compareTwo;
	}

	public void setCompareTwo(DashBoardModelChart compareTwo) {
		this.compareTwo = compareTwo;
	}

	@Override
	public String toString() {
		return "DashBoardModelDTO [display=" + display + ", compareOne=" + compareOne + ", compareTwo=" + compareTwo
				+ "]";
	}

}