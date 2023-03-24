package com.smartcn.smartcndashboard.downloadLogs;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcn.smartcndashboard.utils.DateSplitter;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping(path = "/v1/fetchlogs")
public class Controllerv2 {
//	@Autowired
//	private Servicev2 as;

	@Autowired
	private Servicev2 as;

	public Controllerv2(Servicev2 as) {
		super();
		this.as = as;
	}

	// @Autowired
	private AmazonS3 as3;

	private static String bucketName = "cn-report-files";

	@SuppressWarnings("rawtypes")
	@GetMapping("/s3logs/{type}/{date}/{segment}/{status}")
	public ResponseEntity<StatusResponsev2> s3selectv2(@PathVariable("type") String type,
			@RequestParam("clientcode") List clientcode, @PathVariable("date") String date,
			@PathVariable("segment") String segment, @PathVariable("status") String status) throws IOException {
		StatusResponsev2<CDNLinkv2> statusMessage = new StatusResponsev2<CDNLinkv2>();
		List<CDNLinkv2> temp = null;
		System.out.println(clientcode.get(0));
		switch (type) {
		case "logs":
//			String temp = as.Test(bucketName, ipclientcode, ipdate, seg);
			temp = as.getLogsFromS3(bucketName, clientcode, date, segment, status);
		}

		statusMessage.setSuccess(true);
		statusMessage.setStatusMessage("Data Retrived Successfully");
		statusMessage.setData(temp);
		statusMessage.setTotalCount(temp.size());
		return new ResponseEntity<>(statusMessage, HttpStatus.OK);
	}
	
//s3 list
	@SuppressWarnings("rawtypes")
	@GetMapping("/s3all/AllLogs/{type}/{date}/{segment}/{status}")
	public ResponseEntity<StatusResponsev2> s3SelectAllv2(@PathVariable("type") String type,
			@PathVariable("date") String date, @PathVariable("segment") String segment,
			@PathVariable("status") String status) throws IOException {
		StatusResponsev2<CDNLinkv2> statusMessage = new StatusResponsev2<CDNLinkv2>();

		List<CDNLinkv2> temp = null;
		switch (type) {
		case "logs":
			temp = as.getAllLogsFromS3(bucketName, date, segment);
		}
		statusMessage.setSuccess(true);
		statusMessage.setStatusMessage("Data Retrived Successfully");
		statusMessage.setData(temp);
		statusMessage.setTotalCount(temp.size());
		return new ResponseEntity<>(statusMessage, HttpStatus.OK);
	}

	@GetMapping("/s3pdf/pdf/{clientcode}/{date}/{segment}/{status}")
	public ResponseEntity<StatusResponsev2> pdfDownload(@PathVariable("clientcode") String clientcode,
			@PathVariable("date") String date, @PathVariable("segment") String segment,
			@PathVariable("status") String status) throws JsonProcessingException, JSONException {
		StatusResponsev2<CDNLinkv2> statusMessage = new StatusResponsev2<CDNLinkv2>();
		List<CDNLinkv2> temp = null;
		switch (segment) {
		case "commoncn":
			segment = "commcn";
			break;
		}
//		String filename1 = seg + "_CONTRACT_" + ipclientcode + "_" + dd + mm + yyyy + ".pdf";
		String filename1 = null;
		String t1 = new DateSplitter().hypendate(date);
		String yyyy, mm, dd;
		yyyy = t1.substring(0, 4);
		mm = t1.substring(4, 6);
		dd = t1.substring(6, 8);
//		dd = t1.substring(0, 2);
//		mm = t1.substring(2, 4);
//		yyyy = t1.substring(4, 8);
		String folder = "common-cn-pdf-file/";
		String directoryName = folder + "contractNote/" + yyyy + "/" + mm + "/" + dd + "/" + clientcode + "/";

		ObjectListing objectListing = as3.listObjects(bucketName, directoryName);
		System.out.println("In First switch pdf case:" + directoryName);
		for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
			filename1 = summary.getKey();
			System.out.println("In First switch pdf case:" + filename1);
			S3Object s3PdfFile = as3.getObject(new GetObjectRequest(bucketName, filename1));
			ObjectMetadata objectMetadata = s3PdfFile.getObjectMetadata();
			Map<String, String> userMetadataMap = objectMetadata.getUserMetadata();
			ObjectMapper objectMapper = new ObjectMapper();
			JSONObject json = new JSONObject(objectMapper.writeValueAsString(userMetadataMap));
			String x = json.optString("filename");
			filename1 = json.optString("filename");
			System.out.println(x);
			System.out.println(filename1);
		}
		temp = as.downloadFile(bucketName, filename1);
		statusMessage.setSuccess(true);
		statusMessage.setStatusMessage("Data Retrived Successfully");
		statusMessage.setData(temp);
		statusMessage.setTotalCount(temp.size());
		return new ResponseEntity<>(statusMessage, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/s3/list/{date}/{segment}")
	public ResponseEntity<StatusResponsev2> s3listing(@PathVariable("date") String date,
			@PathVariable("segment") String segment) throws IOException {
		StatusResponsev2<BounceDTOv2> statusMessage = new StatusResponsev2<BounceDTOv2>();
		//List<BounceDTOv2> temp = null;
		List<BounceDTOv2> temp=as.listLogsFromS3(bucketName, date, segment);
//			String temp = as.Test(bucketName, ipclientcode, ipdate, seg);
		//temp = as.listLogsFromS3(bucketName, date, segment);
		statusMessage.setSuccess(true);
		statusMessage.setStatusMessage("Data Retrived Successfully");
		statusMessage.setData(temp);
		statusMessage.setTotalCount(temp.size());
		return new ResponseEntity<>(statusMessage, HttpStatus.OK);
	}


	@SuppressWarnings("unused")
	private MediaType contentType(String filename) {
		String[] fileArrSplit = filename.split("\\.");
		String fileExtension = fileArrSplit[fileArrSplit.length - 1];
		switch (fileExtension) {
		case "txt":
			return MediaType.TEXT_PLAIN;
		case "png":
			return MediaType.IMAGE_PNG;
		case "jpg":
			return MediaType.IMAGE_JPEG;
		case "pdf":
			return MediaType.APPLICATION_PDF;
		case "json":
			return MediaType.APPLICATION_JSON;
		default:
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
}
