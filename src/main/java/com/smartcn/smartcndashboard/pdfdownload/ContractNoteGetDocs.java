package com.smartcn.smartcndashboard.pdfdownload;

public class ContractNoteGetDocs {

	private String fileName;
	private String exchange;
	private String clientCode;
	private String tradeDate;
	private String documentLink;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getDocumentLink() {
		return documentLink;
	}

	public void setDocumentLink(String documentLink) {
		this.documentLink = documentLink;
	}

	@Override
	public String toString() {
		return "ContractNoteGetDocs [fileName=" + fileName + ", exchange=" + exchange + ", clientCode=" + clientCode
				+ ", tradeDate=" + tradeDate + ", documentLink=" + documentLink + "]";
	}

}