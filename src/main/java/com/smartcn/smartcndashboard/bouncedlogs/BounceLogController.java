//package com.smartcn.smartcndashboard.bouncedlogs;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.TimeZone;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.acc.smartcontractnote.reverseapi.v1.SignedUrl;
//import com.acc.smartcontractnote.utils.JWTokenVerify;
//import com.acc.smartcontractnote.utils.StatusMessage;
//import com.amazonaws.ClientConfiguration;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.ObjectListing;
//import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
//
//@SuppressWarnings("unused")
//@CrossOrigin(origins = "*")
//@Transactional
//@RestController
//@RequestMapping(path = "/v1/bouncelog")
//public class BounceLogController {
//
//	@Value("${jwt.secret}")
//	private String secret;
//
//	@Autowired
//	BounceLogService bounceLogService;
//
//	public BounceLogController(BounceLogService bounceLogService) {
//		super();
//		this.bounceLogService = bounceLogService;
//	}
//
//	Integer count = 0;
//	Integer currentYear = 0;
//	String currentDateMonthStr = null;
//	String currentDayStr = null;
//	String key = " ";
//	String status = " ";
//	String pdfBucketName = "";
//	AmazonS3 s3Client;
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@GetMapping("/savelog/{segment}/{sentDate}")
//	public ResponseEntity<StatusMessage> ExportCSV(@PathVariable("segment") String segment,
//			@PathVariable("sentDate") String sentDate, @RequestHeader("x-access-token") String token) {
//		StatusMessage statusMessage = new StatusMessage();
//		CSVDTO csvdto = new CSVDTO();
//		List<CSVDTO> csvdtoList = new ArrayList<CSVDTO>();
//		SignedUrl signedUrl = new SignedUrl();
//		try {
//			JSONObject responseObj;
//			responseObj = JWTokenVerify.verifyJWT(token, secret);
//			if (responseObj.getString("message").equals("valid")) {
//
//				String distributionDomainName = "d1fjijvz1k57ez.cloudfront.net";
//				s3Client = AmazonS3ClientBuilder.standard().build();
//				ClientConfiguration config = new ClientConfiguration();
//				config.setConnectionTimeout(900000);
//				config.setSocketTimeout(900000);
//
//				Date createdDate = new SimpleDateFormat("yyyy-MM-dd").parse(sentDate);
//				String newTransactionDate = new SimpleDateFormat("dd/MM/yyyy").format(createdDate);
//
//				String[] date = newTransactionDate.split("/");
//				String day = date[0];
//				String month = date[1];
//				String year = date[2];
//				String bucketName = "mosl-cn-report-files";
//				switch (segment) {
//				case "stt":
//					key = "stt-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "pnl":
//					key = "pnl-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "dpholding":
//					key = "DPHolding-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "commcn":
//					key = "CommonCN-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "commodity":
//					key = "CommodityCN-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "bill":
//					key = "BillCN-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "dpledger":
//					key = "DPLedger-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "ros":
//					key = "ROS-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "agts":
//					key = "agts-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "dpholdingyearly":
//					key = "dpholdingyearly-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "qsledger":
//					key = "qsLedger-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "dptrade":
//					key = "dptrade-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "qsretention":
//					key = "qsretention-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				case "dmr":
//					key = "dmr-EmailReport/BounceLogAllReport/" + year + "/" + month + "/" + day + "/";
//					break;
//				}
//
//				long startDateMilli = (new Date()).getTime(); //
//				SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//				dateFormatter.setTimeZone(TimeZone.getTimeZone("IST"));
//				String activityDate = dateFormatter.format(startDateMilli);
//
//				ObjectListing listing = s3Client.listObjects(bucketName, key);
//				List<S3ObjectSummary> summaries = listing.getObjectSummaries();
//
//				while (listing.isTruncated()) {
//					listing = s3Client.listNextBatchOfObjects(listing);
//					summaries.addAll(listing.getObjectSummaries());
//				}
//				String filePath1 = "";
//				String s3fileName = "";
//				S3Object fullObject = null;
//				String s3Key = "";
//
//				if (!summaries.isEmpty()) {
//					for (Iterator iterator2 = summaries.iterator(); iterator2.hasNext();) {
//						S3ObjectSummary s3ObjectSummary = (S3ObjectSummary) iterator2.next();
//						s3Key = s3ObjectSummary.getKey();
//
////          if (s3Key.contains(".csv")) {
////            System.out.println("FileName: " + s3Key);
////
////            String prefix = "https://smartcn-test-files.s3.ap-south-1.amazonaws.com/";
////            csvdto.setKey(prefix + s3Key);
////            csvdtoList.add(csvdto);
////
////          }
//						if (s3Key.contains(".json")) {
//							csvdto.setKey(signedUrl.getSignedUrl(s3Key, distributionDomainName));
//							csvdtoList.add(csvdto);
//							;
//						}
//					}
//				}
//				statusMessage.setTotalCount(csvdtoList.size());
//				statusMessage.setListData(csvdtoList);
//				statusMessage.setMessage("log Fetch sucessfully");
//				statusMessage.setSuccess(true);
//				return new ResponseEntity<>(statusMessage, HttpStatus.OK);
//			} else {
//				statusMessage.setMessage("Token Invalid");
//				statusMessage.setSuccess(false);
//				return new ResponseEntity<>(statusMessage, HttpStatus.UNAUTHORIZED);
//			}
//
//		} catch (Exception e) {
//			statusMessage.setMessage(e.getMessage());
//			statusMessage.setSuccess(false);
//			e.printStackTrace();
//
//			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
//		}
//
//	}
//
//}
