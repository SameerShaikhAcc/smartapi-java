//package dashboard;//package com.acc.smartcontractnote.dashboard.v1;
//
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.acc.smartcontractnote.utils.StatusMessage;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.ObjectListing;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
//
//@CrossOrigin(origins = "*")
//@Transactional
//@RestController
//@RequestMapping(path = "/v2/dashboard")
//public class DashBoardControllerV2 {
//	AmazonS3 s3Client;
//
//	@GetMapping("/fetch")
//	public ResponseEntity<StatusMessage> fetchData() {
//		s3Client = AmazonS3ClientBuilder.standard().build();
//		//String key = "pnl-EmailReport/BounceLog/2022/02/05/";
//		String key = "pnl-EmailReport/BounceLog/2022/02/05/";
//		String bucketName = "mosl-cn-report-files";
//		ObjectListing listing = s3Client.listObjects(bucketName, key);
//		List<S3ObjectSummary> summaries = listing.getObjectSummaries();
//		System.out.println("summaries Top: " + summaries.size());
//
//		return null;
//
//	}
//}
//
