package com.smartcn.smartcndashboard.downloadLogs;

public class BounceDTOv2 {

	String ACTIVITYDATE;
	String CLIENTNAME;
	String fileName;
	String tradedate;
	String BOREASON;
	String BOType;
	String EXCHANGE;
	String CLIENTCODE;
	String CLIENTEMAIL;

	public String getACTIVITYDATE() {
		return ACTIVITYDATE;
	}

	public void setACTIVITYDATE(String aCTIVITYDATE) {
		ACTIVITYDATE = aCTIVITYDATE;
	}

	public String getCLIENTNAME() {
		return CLIENTNAME;
	}

	public void setCLIENTNAME(String cLIENTNAME) {
		CLIENTNAME = cLIENTNAME;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTradedate() {
		return tradedate;
	}

	public void setTradedate(String tradedate) {
		this.tradedate = tradedate;
	}

	public String getBOREASON() {
		return BOREASON;
	}

	public void setBOREASON(String bOREASON) {
		BOREASON = bOREASON;
	}

	public String getBOType() {
		return BOType;
	}

	public void setBOType(String bOType) {
		BOType = bOType;
	}

	public String getEXCHANGE() {
		return EXCHANGE;
	}

	public void setEXCHANGE(String eXCHANGE) {
		EXCHANGE = eXCHANGE;
	}

	public String getCLIENTCODE() {
		return CLIENTCODE;
	}

	public void setCLIENTCODE(String cLIENTCODE) {
		CLIENTCODE = cLIENTCODE;
	}

	public String getCLIENTEMAIL() {
		return CLIENTEMAIL;
	}

	public void setCLIENTEMAIL(String cLIENTEMAIL) {
		CLIENTEMAIL = cLIENTEMAIL;
	}
	

	@Override
	public String toString() {
		return "\nACTIVITYDATE = " + ACTIVITYDATE + ",\n CLIENTNAME = " + CLIENTNAME + ",\n fileName = " + fileName
				+ ",\n tradedate = " + tradedate + ",\n BOREASON = " + BOREASON + ",\n BOType = " + BOType
				+ ",\n EXCHANGE = " + EXCHANGE + ",\n CLIENTCODE = " + CLIENTCODE + ",\n CLIENTEMAIL = " + CLIENTEMAIL
				+ "\n";
	}
//	@Override
//	public String toString() {
//		return "\n\nACTIVITYDATE = " + ACTIVITYDATE + ",\n CLIENTNAME = " + CLIENTNAME + ",\n fileName = " + fileName
//				+ ",\n tradedate = " + tradedate + ",\n BOREASON = " + BOREASON + ",\n BOType = " + BOType
//				+ ",\n EXCHANGE = " + EXCHANGE + ",\n CLIENTCODE = " + CLIENTCODE + ",\n CLIENTEMAIL = " + CLIENTEMAIL;
//	}

}
