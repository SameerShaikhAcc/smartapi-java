package com.smartcn.smartcndashboard.pdfdownload;
//package com.acc.smartcontractnote.reverseapi.v1;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import org.joda.time.LocalDate;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.acc.smartcontractnote.reverseapi.v1.ContractNoteGetDocs;
//import com.acc.smartcontractnote.reverseapi.v1.SignedUrlV2;
//import com.acc.smartcontractnote.utils.StatusMessage;
//import com.amazonaws.ClientConfiguration;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.GetObjectRequest;
//import com.amazonaws.services.s3.model.ObjectListing;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
//
//@CrossOrigin(origins = "*")
//@Transactional
//@RestController
//@RequestMapping(path = "/v3/reverseapi")
//public class ReverseApiControllerV3 {
//
////	@Autowired
////	ReverseApiServiceV2 reverseApiServiceV2;
////
////	public ReverseApiControllerV3(ReverseApiServiceV2 reverseApiService) {
////		super();
////		reverseApiServiceV2 = reverseApiService;
////	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes", "unused", "unlikely-arg-type" })
//	@GetMapping("/fetchsegments/{clientcode}/{fromDate}/{toDate}/{segment}")
//	public ResponseEntity<StatusMessage> fetch(@PathVariable("clientcode") String clientcode,
//			@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
//			@PathVariable("segment") String segment) {
//
//		StatusMessage statusMessage = new StatusMessage<>();
//		SignedUrlV2 signedUrl = new SignedUrlV2();
//		try {
//			// SignedUrl signedUrl = new SignedUrl();
//			List<ContractNoteGetDocs> contractNoteGetDocsList = new ArrayList<ContractNoteGetDocs>();
//			contractNoteGetDocsList.clear();
//
//			String pdfBucketName = "";
//			String key = "";
//			String folderName = "";
//			String fileName = "";
//			String fileSuffix = "";
//
//			ClientConfiguration config = new ClientConfiguration();
//			config.setConnectionTimeout(900000);
//			config.setSocketTimeout(900000);
//			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withClientConfiguration(config).build();
//			Date logsNow = new Date();
//			String activityDate = new SimpleDateFormat("yyyy-MM-dd").format(logsNow);
//			String[] dateArray = activityDate.split("-");
//			String year = dateArray[0];
//			String month = dateArray[1];
//			String day = dateArray[2];
//			String fileNameArray[] = fileName.split("\\.");
//			File newFile = new File(fileName);
//			Random rand = new Random();
//			int randNumber = rand.nextInt(1000);
//
//			Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
//			Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
//
//			if ((startDate.before(endDate) && endDate.before(new Date())) || (startDate.equals(endDate))) {
//
//				s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
//
//				LocalDate start = LocalDate.parse(fromDate);
//				LocalDate end = LocalDate.parse(toDate);
//
//				List<LocalDate> totalDates = new ArrayList<>();
//				while (!start.isAfter(end)) {
//					totalDates.add(start);
//					start = start.plusDays(1);
//				}
//
//				for (Iterator iterator = totalDates.iterator(); iterator.hasNext();) {
//
//					LocalDate localDate = (LocalDate) iterator.next();
//					Integer currentDay = localDate.getDayOfMonth();
//
//					// Getting the current month
//					Integer currentMonth = localDate.getMonthOfYear();
//
//					// getting the current year
//					Integer currentYear = localDate.getYear();
//
//					String currentDateMonthStr = null;
//					if (currentMonth < 10) {
//						currentDateMonthStr = "0" + currentMonth;
//					} else {
//						currentDateMonthStr = "" + currentMonth;
//					}
//
//					String currentDayStr = null;
//					if (currentDay < 10) {
//						currentDayStr = "0" + currentDay;
//					} else {
//						currentDayStr = "" + currentDay;
//					}
//
//					if (segment.equals("all")) {
//						String[] allSegments = { "stt", "pnl", "dpholding", "commcn", "commodity", "bill", "dpledger",
//								"ros", "agts", "dpholdingyearly", "qsledger", "dptrade", "qsretention" };
//
//						for (String segments : allSegments) {
//							switch (segment) {
//							case "stt":
//								pdfBucketName = "mosl-stt-pdf-files";
//								folderName = "contractNote/";
//								break;
//
//							case "pnl":
//								pdfBucketName = "mosl-pnl-cn-pdf-file";
//								folderName = "contractNote/";
//								break;
//
//							case "dpholding":
//								pdfBucketName = "mosl-pdf-files-with-email-sms";
//								folderName = "contractNote/";
//								break;
//
//							case "commcn":
//								pdfBucketName = "mosl-commom-cn-pdf-files";
//								folderName = "contractNote/";
//								break;
//
//							case "commodity":
//								pdfBucketName = "mosl-commom-cn-pdf-files";
//								folderName = "contractNote/";
//								break;
//
//							case "bill":
//								pdfBucketName = "mosl-bill-pdf-files";
//								folderName = "billContractNote/";
//								break;
//
//							case "dpledger":
//								pdfBucketName = "mosl-dpledgerweeklypdf-files";
//								folderName = "contractNote/";
//								break;
//
//							case "ros":
//								pdfBucketName = "mosl-ros-cn-pdf-files";
//								folderName = "contractNote/";
//								break;
//
//							case "agts":
//								pdfBucketName = "mosl-agts-cn-pdf-file";
//								folderName = "contractNote/";
//								break;
//
//							case "dpholdingyearly":
//								pdfBucketName = "mosl-dp-yearly-pdf-files-with-email-sms";
//								folderName = "contractNote/";
//								break;
//
//							case "qsledger":
//								pdfBucketName = "mosl-cn-qsledger-pdf-files";
//								folderName = "ContractNote/";
//								break;
//
//							case "dptrade":
//								pdfBucketName = "mosl-trade-transaction-pdf-files-with-email-sms";
//								folderName = "contractNote/";
//								break;
//
//							case "qsretention":
//								pdfBucketName = "mosl-qsretention-cn-pdf-file";
//								folderName = "contractNote/";
//								break;
//							}
//
//							key = folderName + clientcode + "/" + currentDay + "/" + currentDateMonthStr + "/"
//									+ currentYear + "/";
//
//							ObjectListing listing = s3Client.listObjects(pdfBucketName, key);
//							List<S3ObjectSummary> summaries = listing.getObjectSummaries();
//
//							while (listing.isTruncated()) {
//								listing = s3Client.listNextBatchOfObjects(listing);
//								summaries.addAll(listing.getObjectSummaries());
//							}
//
//							for (Iterator iterator2 = summaries.iterator(); iterator2.hasNext();) {
//								S3ObjectSummary s3ObjectSummary = (S3ObjectSummary) iterator2.next();
//								String s3Key = s3ObjectSummary.getKey();
//
//								if (s3Key.contains(".pdf")) {
//									S3Object s3PdfFile = s3Client.getObject(new GetObjectRequest(pdfBucketName, s3Key));
//									System.out.println("after pdf get ");
//									ObjectMetadata objectMetadata = s3PdfFile.getObjectMetadata();
//									Map<String, String> userMetadataMap = objectMetadata.getUserMetadata();
//
//									System.out.println("PartyCode: " + userMetadataMap.get("partycode"));
//
//									ContractNoteGetDocs contractNoteGetDocs = new ContractNoteGetDocs();
//									// contractNoteGetDocs.setDocumentLink(url);
//									contractNoteGetDocs.setClientCode(userMetadataMap.get("partycode"));
//									contractNoteGetDocs.setExchange(userMetadataMap.get("exchange"));
//									contractNoteGetDocs.setFileName(userMetadataMap.get("filename"));
//									contractNoteGetDocs.setTradeDate(userMetadataMap.get("tradedate"));
//									contractNoteGetDocsList.add(contractNoteGetDocs);
//								}
//							}
//						}
//					} else if (!segment.equals("all")) {
//
//						switch (segment) {
//						case "stt":
//							pdfBucketName = "mosl-stt-pdf-files";
//							folderName = "contractNote/";
//							break;
//
//						case "pnl":
//							pdfBucketName = "mosl-pnl-cn-pdf-file";
//							folderName = "contractNote/";
//							break;
//
//						case "dpholding":
//							pdfBucketName = "mosl-pdf-files-with-email-sms";
//							folderName = "contractNote/";
//							break;
//
//						case "commcn":
//							pdfBucketName = "mosl-commom-cn-pdf-files";
//							folderName = "contractNote/";
//							break;
//
//						case "commodity":
//							pdfBucketName = "mosl-commom-cn-pdf-files";
//							folderName = "contractNote/";
//							break;
//
//						case "bill":
//							pdfBucketName = "mosl-bill-pdf-files";
//							folderName = "billContractNote/";
//							break;
//
//						case "dpledger":
//							pdfBucketName = "mosl-dpledgerweeklypdf-files";
//							folderName = "contractNote/";
//							break;
//
//						case "ros":
//							pdfBucketName = "mosl-ros-cn-pdf-files";
//							folderName = "contractNote/";
//							break;
//
//						case "agts":
//							pdfBucketName = "mosl-agts-cn-pdf-file";
//							folderName = "contractNote/";
//							break;
//
//						case "dpholdingyearly":
//							pdfBucketName = "mosl-dp-yearly-pdf-files-with-email-sms";
//							folderName = "contractNote/";
//							break;
//
//						case "qsledger":
//							pdfBucketName = "mosl-cn-qsledger-pdf-files";
//							folderName = "ContractNote/";
//							break;
//
//						case "dptrade":
//							pdfBucketName = "mosl-trade-transaction-pdf-files-with-email-sms";
//							folderName = "contractNote/";
//							break;
//
//						case "qsretention":
//							pdfBucketName = "mosl-qsretention-cn-pdf-file";
//							folderName = "contractNote/";
//							break;
//						}
//
//						key = folderName + clientcode + "/" + currentDay + "/" + currentDateMonthStr + "/" + currentYear
//								+ "/";
//					}
//				}
//			} else {
//				statusMessage.setSuccess(false);
//				statusMessage.setMessage("Please enter valid start and end date");
//
//				JSONObject response = new JSONObject();
//				response.put("statusCode", 400);
//				response.put("message", "Please enter valid start and end date");
//				response.put("success", false);
//				throw new Error(response.toString());
//			}
//
//			statusMessage.setMessage("Data Fetch SuccessFul");
//			statusMessage.setSuccess(true);
//			statusMessage.setTotalCount(contractNoteGetDocsList.size());
//			statusMessage.setListData(contractNoteGetDocsList);
//			return new ResponseEntity<>(statusMessage, HttpStatus.OK);
//
//		} catch (Exception e) {
//			statusMessage.setMessage(e.getMessage());
//			statusMessage.setSuccess(false);
//			e.printStackTrace();
//			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
//		}
//	}
//}