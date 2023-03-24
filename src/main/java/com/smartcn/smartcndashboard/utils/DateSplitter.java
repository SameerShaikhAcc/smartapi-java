package com.smartcn.smartcndashboard.utils;

public class DateSplitter {

	public String hypendate(String date)
	{
		String yyyy, mm, dd;
		if(date.length()==9)
		{
			if(date.contains("-"))
		{
			date.replace("-", "");
			yyyy = date.substring(0, 4);
			mm = date.substring(5, 7);
			dd = date.substring(8, 9);
			if (dd.length() == 1) {
				dd = "0" + dd;
			}
			date = yyyy + mm + dd;
		}
		else if (date.contains("/")) {
			date.replace("/", "");
			yyyy = date.substring(0, 2);
			mm = date.substring(2, 4);
			dd = date.substring(4, 8);
//			if (dd.length() == 1) {
//				dd = "0" + dd;
//			}
			date = yyyy + mm + dd;
		}
	}
	else if (date.length() == 10) {
		if (date.contains("-")) {
			date.replace("-", "");
			yyyy = date.substring(0, 4);
			mm = date.substring(5, 7);
			dd = date.substring(8, 10);
			System.out.println(yyyy + " " + mm + " " + dd);
//			if (dd.length() == 1) {
//				dd = "0" + dd;
//			}
			date = yyyy + mm + dd;
		} else if (date.contains("/")) {
			date.replace("/", "");
			yyyy = date.substring(0, 2);
			mm = date.substring(2, 4);
			dd = date.substring(4, 8);
//				if (dd.length() == 1) {
//					dd = "0" + dd;
//				}
			date = yyyy + mm + dd;
		}
	}
		return date;

	}
}
