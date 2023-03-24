package com.smartcn.smartcndashboard.downloadLogs;

import java.io.IOException;
import java.util.List;

public interface Servicev2 {
	List<CDNLinkv2> getLogsFromS3(String bucketName, List clientcode, String ipdate, String seg, String type)
			throws IOException;

//	List<CDNLinkv2> getAllLogsFromS3(String bucketName, String date, String segment, String type)
//			throws IOException;

	List<CDNLinkv2> downloadFile(String BUCKET_NAME, String keyName);

	List<BounceDTOv2> listLogsFromS3(String bucketName, String date, String segment) throws IOException;
	//List<BounceDTOv2> listLogsFromS3(String bucketName, String date, String segment) throws IOException;

	List<CDNLinkv2> getAllLogsFromS3(String bucketName, String date, String segment) throws IOException;
}
