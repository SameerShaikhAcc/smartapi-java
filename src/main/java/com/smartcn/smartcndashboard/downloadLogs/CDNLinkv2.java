package com.smartcn.smartcndashboard.downloadLogs;

import java.util.List;

public class CDNLinkv2 {

	private String key;
	private List <BounceDTOv2> data;
	
	
	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public List<BounceDTOv2> getData() {
		return data;
	}


	public void setData(List<BounceDTOv2> data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "CDNLinkv2 [key=" + key + ", data=" + data + "]";
	}

}
