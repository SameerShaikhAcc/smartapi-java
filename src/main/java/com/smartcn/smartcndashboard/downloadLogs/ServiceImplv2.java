package com.smartcn.smartcndashboard.downloadLogs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.HttpMethod;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CompressionType;
import com.amazonaws.services.s3.model.ExpressionType;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.InputSerialization;
import com.amazonaws.services.s3.model.JSONInput;
import com.amazonaws.services.s3.model.JSONOutput;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.OutputSerialization;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.SelectObjectContentRequest;
import com.amazonaws.services.s3.model.SelectObjectContentResult;
import com.smartcn.smartcndashboard.utils.DateSplitter;
import com.smartcn.smartcndashboard.utils.SignedUrlOneDay;

@Service
public class ServiceImplv2 implements Servicev2 {

	// @Autowired(required=true)
	private AmazonS3 as3;
	SignedUrlOneDay signedUrl = new SignedUrlOneDay();
	String preSignedUrl = null;

	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public List<CDNLinkv2> getLogsFromS3(String bucketName, List clientcode, String date, String segment, String type)
			throws IOException {
		as3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
		List<BounceDTOv2> bounceDTOList = new ArrayList<>();
		List<CDNLinkv2> cdnLinkList = new ArrayList<>();
		CDNLinkv2 cdnLink = new CDNLinkv2();
//		File zipFile = new File("temp.zip");
//		System.out.println(zipFile);
		String searchQuery = null;
		String t1 = new DateSplitter().hypendate(date);
		String yyyy, mm, dd;
		yyyy = t1.substring(0, 4);
		mm = t1.substring(4, 6);
		dd = t1.substring(6, 8);
		String directoryName = null;
		String d1 = null;
		for (int i = 0; i < clientcode.size(); i++) {
			switch (segment) {
			case "commoncn":
				if (type.equals("Delivered")) {
					directoryName = "CommonCN-EmailReport/SentLog/";
					d1 = directoryName;
				} else if (type.equals("Bounce")) {
					directoryName = "CommonCN-EmailReport/BounceLog/";
					d1 = directoryName;
				}
				break;
			default:
				directoryName = " ";
				break;
			}
			if (!clientcode.isEmpty() && date.isEmpty() && segment.isEmpty()) {
				searchQuery = "SELECT * FROM S3Object s where s.Client_Code='" + clientcode.get(i) + "'";
			} else if (clientcode.isEmpty() && date.isEmpty() && !segment.isEmpty()) {
				searchQuery = "SELECT * FROM S3Object s where s.EXCHANGE='" + segment + "'";
			} else if (clientcode.isEmpty() && !date.isEmpty() && segment.isEmpty()) {
				directoryName = directoryName + yyyy + "/" + mm + "/" + dd + "/";
				searchQuery = "SELECT * FROM S3Object s where s.tradedate='" + date + "'";
			} else if (!clientcode.isEmpty() && date.isEmpty() && !segment.isEmpty()) {
				searchQuery = "SELECT * FROM S3Object s where s.Client_Code='" + clientcode.get(i) + "'and s.EXCHANGE='"
						+ segment + "'";
			} else if (!clientcode.isEmpty() && !date.isEmpty() && segment.isEmpty()) {
				directoryName = directoryName + yyyy + "/" + mm + "/" + dd + "/" + clientcode.get(i) + "/";
				searchQuery = "SELECT * FROM S3Object s where s.Client_Code='" + clientcode.get(i)
						+ "'and s.tradedate='" + date + "'";
			} else if (clientcode.isEmpty() && !date.isEmpty() && !segment.isEmpty()) {
				directoryName = directoryName + yyyy + "/" + mm + "/" + dd + "/";
				searchQuery = "SELECT * FROM S3Object s where s.EXCHANGE='" + segment + "'and s.tradedate='" + date
						+ "'";
			} else if (!clientcode.isEmpty() && !date.isEmpty() && !segment.isEmpty()) {
				directoryName = directoryName + yyyy + "/" + mm + "/" + dd + "/" + clientcode.get(i) + "/";
				searchQuery = "SELECT * FROM S3Object s where s.Client_Code='" + clientcode.get(i)
						+ "'and s.tradedate='" + date + "'and s.EXCHANGE='" + segment + "'";
			} else {
				searchQuery = "SELECT * FROM S3Object s";
			}
			ObjectListing objectListing = as3.listObjects(bucketName, directoryName);
//				zipEntry = new ZipEntry();
//			ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
			for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {

				SelectObjectContentRequest request = new SelectObjectContentRequest();
				request.setBucketName(bucketName);
				request.setKey(summary.getKey());
				request.setExpression(searchQuery);
				request.setExpressionType(ExpressionType.SQL);
//					jsonFiles.add(request.getKey().substring(directoryName.length()));
				InputSerialization inputSerialization = new InputSerialization();

				inputSerialization.setJson(new JSONInput().withType("Lines"));
				inputSerialization.setCompressionType(CompressionType.NONE);

				OutputSerialization outputSerialization = new OutputSerialization();
				outputSerialization.setJson(new JSONOutput());

				request.setInputSerialization(inputSerialization);
				request.setOutputSerialization(outputSerialization);

				try {
					SelectObjectContentResult result = as3.selectObjectContent(request);
					final InputStream stream = result.getPayload().getRecordsInputStream();

					String output = new BufferedReader(new InputStreamReader(stream)).lines()
							.collect(Collectors.joining("\n"));
					if (!output.isBlank()) {
						JSONObject obj = new JSONObject(output);
						BounceDTOv2 bounceDTO = new BounceDTOv2();
						bounceDTO.setACTIVITYDATE(obj.getString("ACTIVITY DATE"));
						bounceDTO.setBOREASON(obj.getString("BO REASON"));
						bounceDTO.setBOType(obj.getString("BO Type"));
						bounceDTO.setCLIENTCODE(obj.optString("CLIENT_CODE", "5000274"));
						bounceDTO.setCLIENTEMAIL(obj.getString("CLIENT EMAIL"));
						bounceDTO.setCLIENTNAME(obj.getString("CLIENT NAME"));
						bounceDTO.setEXCHANGE(obj.getString("EXCHANGE"));
						bounceDTO.setFileName(obj.getString("fileName"));
						bounceDTO.setTradedate(obj.getString("tradedate"));
						bounceDTOList.add(bounceDTO);
						System.out.println("result: " + directoryName + bounceDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
//		zipOutputStream.close();
		String path = String.format("%s", bucketName);
		String fileName = String.format("%s", "logs.json");
		d1 = d1 + yyyy + "/" + mm + "/" + dd + "/";
		String keyObject = d1 + fileName;
//		String zipKey = d1 + "multi-client-zip-file.zip";
		as3.putObject(path, keyObject, bounceDTOList.toString());
//		as3.putObject(bucketName, zipKey, zipFile);
		String signedKey = signedUrl.getSignedUrl(keyObject, "d171ahyejz9a2n.cloudfront.net");

		java.util.Date expiration = new java.util.Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60 * 60;
		expiration.setTime(expTimeMillis);
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, keyObject)
				.withMethod(HttpMethod.GET).withExpiration(expiration);
		ResponseHeaderOverrides overrides = new ResponseHeaderOverrides();
		generatePresignedUrlRequest.setResponseHeaders(overrides);
		overrides.setContentDisposition("attachment; filename=\"" + keyObject + "\"");
		generatePresignedUrlRequest.setResponseHeaders(overrides);
		URL url = as3.generatePresignedUrl(generatePresignedUrlRequest);
		preSignedUrl = url.toString();
		System.out.println("Pre-Signed URL: " + preSignedUrl);
		cdnLink.setData(bounceDTOList);
		cdnLink.setKey(preSignedUrl);
		cdnLinkList.add(cdnLink);
		return cdnLinkList;
	}

	@SuppressWarnings("unused")
	@Override
	public List<CDNLinkv2> getAllLogsFromS3(String bucketName, String date, String segment)
			throws IOException {
		as3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
		List<BounceDTOv2> bounceDTOList = new ArrayList<>();
		List<CDNLinkv2> cdnLinkList = new ArrayList<>();
		File zipFile = new File("temp.zip");
//		ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
		String searchQuery = null;
		String t1 = new DateSplitter().hypendate(date);
		String yyyy, mm, dd;
		yyyy = t1.substring(0, 4);
		mm = t1.substring(4, 6);
		dd = t1.substring(6, 8);
		System.out.println(yyyy + " " + mm + " " + dd);
		String Segdirectory = null;
		String typedirectory = null;
		String finaldirectory = null;
//		clientcode = "";
//		segment = "";
//		date = "";
		switch (segment) {
		case "commoncn":
				Segdirectory = "CommonCN-EmailReport/";
			break;
		default:
			Segdirectory = " ";
			break;
		}
		ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
		for (int i = 1; i < 3; i++) {
			switch (i) {
			case 1:
				typedirectory = "SentLogAllReport/";
				System.out.println(typedirectory);
				break;
			case 2:
				typedirectory = "BounceLogAllReport/";
				System.out.println(typedirectory);
				break;
			default:
				Segdirectory = "";
				break;
			}
		if (date.isEmpty() && !segment.isEmpty()) {
//			searchQuery = "SELECT * FROM S3Object s where s.EXCHANGE='" + segment + "'";
		} else if (!date.isEmpty() && segment.isEmpty()) {
			finaldirectory = Segdirectory + typedirectory + yyyy + "/" + mm + "/" + dd + "/" + "allreport/";
			System.out.println(finaldirectory);
//			searchQuery = "SELECT * FROM S3Object s where s.tradedate='" + date + "'";
		} else if (!date.isEmpty() && !segment.isEmpty()) {
			finaldirectory = Segdirectory + typedirectory + yyyy + "/" + mm + "/" + dd + "/" + "allreport/";
			System.out.println(finaldirectory);
//			searchQuery = "SELECT * FROM S3Object s where s.tradedate='" + date + "'and s.EXCHANGE='" + segment + "'";
		} else {
//			searchQuery = "SELECT * FROM S3Object s";
		}

		ObjectListing objectListing = as3.listObjects(bucketName, finaldirectory);

		for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
			String objectKey = summary.getKey();
			System.out.println(objectKey);
			if (objectKey.contains(".json")) {
				S3Object s3Object = as3.getObject(bucketName, objectKey);
				ZipEntry zipEntry = new ZipEntry(objectKey.substring(Segdirectory.length()));
				zipOutputStream.putNextEntry(zipEntry);

				InputStream inputStream = s3Object.getObjectContent();
				byte[] buffer = new byte[1024];
				int length;
				while ((length = inputStream.read(buffer)) >= 0) {
					zipOutputStream.write(buffer, 0, length);
				}
				inputStream.close();
				zipOutputStream.closeEntry();
			}
		}
		}
		zipOutputStream.close();
		String zipKey = Segdirectory + "all-log-zip-file.zip";
		System.out.println(zipKey);
//		String path = String.format("%s", bucketName);
//		String fileName = String.format("%s", "demo.json");
//		as3.putObject(path, fileName, bounceDTOList.toString());

//		PutObjectRequest request;
//		String keyObject = directoryName + fileName;

		as3.putObject(bucketName, zipKey, zipFile);

		String signedKey = signedUrl.getSignedUrl(zipKey, "d171ahyejz9a2n.cloudfront.net");
		CDNLinkv2 cdnLink = new CDNLinkv2();
		java.util.Date expiration = new java.util.Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60 * 60;
		expiration.setTime(expTimeMillis);
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, zipKey)
				.withMethod(HttpMethod.GET).withExpiration(expiration);
		ResponseHeaderOverrides overrides = new ResponseHeaderOverrides();
		generatePresignedUrlRequest.setResponseHeaders(overrides);
		overrides.setContentDisposition("attachment; filename=\"" + "all-log-zip-file.zip" + "\"");
		generatePresignedUrlRequest.setResponseHeaders(overrides);
		URL url = as3.generatePresignedUrl(generatePresignedUrlRequest);
		preSignedUrl = url.toString();
		System.out.println("Pre-Signed URL: " + preSignedUrl);
		cdnLink.setKey(preSignedUrl);
		cdnLinkList.add(cdnLink);
		return cdnLinkList;
	}


	@Override
	public List<CDNLinkv2> downloadFile(String BUCKET_NAME, String keyName) {
		try {
			S3Object s3object = as3.getObject(new GetObjectRequest(BUCKET_NAME, keyName));
			InputStream is = s3object.getObjectContent();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int len;
			byte[] buffer = new byte[4096];
			while ((len = is.read(buffer, 0, buffer.length)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			String signedKey = signedUrl.getSignedUrl(keyName, "d171ahyejz9a2n.cloudfront.net");
			CDNLinkv2 cdnLink = new CDNLinkv2();
			List<CDNLinkv2> cdnLinkList = new ArrayList<>();
			java.util.Date expiration = new java.util.Date();
			long expTimeMillis = expiration.getTime();
			expTimeMillis += 1000 * 60 * 60;
			expiration.setTime(expTimeMillis);
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(BUCKET_NAME,
					keyName).withMethod(HttpMethod.GET).withExpiration(expiration);
			ResponseHeaderOverrides overrides = new ResponseHeaderOverrides();
			generatePresignedUrlRequest.setResponseHeaders(overrides);
			overrides.setContentDisposition("attachment; filename=\"" + keyName + "\"");
			generatePresignedUrlRequest.setResponseHeaders(overrides);
			URL url = as3.generatePresignedUrl(generatePresignedUrlRequest);
			preSignedUrl = url.toString();
			System.out.println("Pre-Signed URL: " + preSignedUrl);
			cdnLink.setKey(preSignedUrl);
			cdnLinkList.add(cdnLink);
			return cdnLinkList;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	// add

	@Override
	public List<BounceDTOv2> listLogsFromS3(String bucketName, String date, String segment) throws IOException {
		as3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
		List<BounceDTOv2> bounceDTOList = new ArrayList<>();
		List<CDNLinkv2> cdnLinkList = new ArrayList<>();
		CDNLinkv2 cdnLink = new CDNLinkv2();
//		File zipFile = new File("temp.zip");
//		System.out.println(zipFile);
		String searchQuery = null;
		String t1 = new DateSplitter().hypendate(date);
		String yyyy, mm, dd;
		yyyy = t1.substring(0, 4);
		mm = t1.substring(4, 6);
		dd = t1.substring(6, 8);
		String Segdirectory = null;
		String typedirectory = null;
		String finaldirectory = null;
//		String d1 = null;
		switch (segment) {
		case "commoncn":
			Segdirectory = "CommonCN-EmailReport/";
			System.out.println(Segdirectory);
			break;
		default:
			Segdirectory = " ";
			break;
		}
		for (int i = 1; i < 3; i++) {
			switch (i) {
			case 1:
				typedirectory = "SentLog/";
				System.out.println(typedirectory);
				break;
			case 2:
				typedirectory = "BounceLog/";
				System.out.println(typedirectory);
				break;
			default:
				Segdirectory = "";
				break;
			}
			if (date.isEmpty() && !segment.isEmpty()) {
				searchQuery = "SELECT * FROM S3Object s where s.EXCHANGE='" + segment + "'";
//				System.out.println("356 Inside If:" + searchQuery);
			} else if (!date.isEmpty() && segment.isEmpty()) {
				finaldirectory = Segdirectory + typedirectory + yyyy + "/" + mm + "/" + dd + "/";
				searchQuery = "SELECT * FROM S3Object s where s.tradedate='" + date + "'";
//				System.out.println("360 Inside else If:" + finaldirectory);
//				System.out.println("361 Inside else If:" + searchQuery);
			} else if (!date.isEmpty() && !segment.isEmpty()) {
				finaldirectory = Segdirectory + typedirectory + yyyy + "/" + mm + "/" + dd + "/";
				searchQuery = "SELECT * FROM S3Object s where s.tradedate='" + date + "'and s.EXCHANGE='" + segment
						+ "'";
//				System.out.println("366 Inside else If:" + finaldirectory);
//				System.out.println("367 Inside else If:" + searchQuery);
			} else {
				searchQuery = "SELECT * FROM S3Object s";
			}
			ObjectListing objectListing = as3.listObjects(bucketName, finaldirectory);
			for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
				System.out.println("keys: " + summary.getKey());
				SelectObjectContentRequest request = new SelectObjectContentRequest();
				request.setBucketName(bucketName);
				request.setKey(summary.getKey());
				request.setExpression(searchQuery);
				request.setExpressionType(ExpressionType.SQL);
				InputSerialization inputSerialization = new InputSerialization();

				inputSerialization.setJson(new JSONInput().withType("Lines"));
				inputSerialization.setCompressionType(CompressionType.NONE);

				OutputSerialization outputSerialization = new OutputSerialization();
				outputSerialization.setJson(new JSONOutput());

				request.setInputSerialization(inputSerialization);
				request.setOutputSerialization(outputSerialization);
				System.out.println("Before try");
				try {
					System.out.println("Inside try");
					SelectObjectContentResult result = as3.selectObjectContent(request);
					final InputStream stream = result.getPayload().getRecordsInputStream();

					String output = new BufferedReader(new InputStreamReader(stream)).lines()
							.collect(Collectors.joining("\n"));
					System.out.println("Before if");
					if (!output.isBlank()) {
						System.out.println("after if");
						JSONObject obj = new JSONObject(output);
						System.out.println("Json : " + obj);
						BounceDTOv2 bounceDTO = new BounceDTOv2();
						bounceDTO.setACTIVITYDATE(obj.getString("ACTIVITY DATE"));
						bounceDTO.setBOREASON(obj.getString("BO REASON"));
						bounceDTO.setBOType(obj.optString("BO Type"));
						bounceDTO.setCLIENTCODE(obj.getString("CLIENT_CODE"));
						bounceDTO.setCLIENTEMAIL(obj.getString("CLIENT EMAIL"));
						bounceDTO.setCLIENTNAME(obj.getString("CLIENT NAME"));
						bounceDTO.setEXCHANGE(obj.getString("EXCHANGE"));
						bounceDTO.setFileName(obj.getString("fileName"));
						bounceDTO.setTradedate(obj.getString("tradedate"));
						bounceDTOList.add(bounceDTO);
						System.out.println("bounceDTOList : " + bounceDTOList);
						System.out.println("result: " + finaldirectory + bounceDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println();
				}
			}
		}

		cdnLink.setData(bounceDTOList);
		cdnLinkList.add(cdnLink);
		return bounceDTOList;
	}

}
