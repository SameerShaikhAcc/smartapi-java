//package com.acc.smartcontractnote.bouncedlogs.v1;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//
//import org.springframework.stereotype.Service;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.GetObjectRequest;
//import com.amazonaws.services.s3.model.S3Object;
//
//@Service
//public class BounceLogServiceImpl implements BounceLogService {
//
//	@Override
//	public ByteArrayOutputStream downloadFile(String keyName) {
//		try {
//			AmazonS3 s3Client;
//			s3Client = AmazonS3ClientBuilder.standard().build();
////			S3Object s3object = amazonS3Client.getObject(new GetObjectRequest(bucketName, keyName));
//			S3Object fullObject = null;
//			fullObject = s3Client.getObject(new GetObjectRequest("mosl-cn-report-files", keyName));
//		
//			InputStream is = fullObject.getObjectContent();
//			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//			int len;
//			byte[] buffer = new byte[4096];
//			while ((len = is.read(buffer, 0, buffer.length)) != -1) {
//				outputStream.write(buffer, 0, len);
//			}
//
//			return outputStream;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
//
//		return null;
//	}
//
//}
