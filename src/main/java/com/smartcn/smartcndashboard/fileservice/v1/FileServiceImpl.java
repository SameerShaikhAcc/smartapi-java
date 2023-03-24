package com.smartcn.smartcndashboard.fileservice.v1;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.smartcn.smartcndashboard.utils.DateTimeUtil;
import com.smartcn.smartcndashboard.utils.GlobalConstants;
import com.smartcn.smartcndashboard.utils.S3ClientConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

@Service

public class FileServiceImpl implements FileService {

    @Override
    public FileModel putFileonS3(MultipartFile docfile) {
        File newFile = null;
        FileModel fileModel = new FileModel();
        try {

            newFile = new File(docfile.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(docfile.getBytes());
            fos.close();

            PutObjectRequest putObjectRequest = new PutObjectRequest(GlobalConstants.fileBucketName, docfile.getOriginalFilename(), newFile);
            S3ClientConfiguration.getClient().putObject(putObjectRequest);

            fileModel.setS3BucketName(GlobalConstants.fileBucketName);
            fileModel.setS3FilePath(docfile.getOriginalFilename());
            fileModel.setCreatedOn(DateTimeUtil.getCurrentDate());
            return fileModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            if (newFile != null) {
                newFile.delete();
            }
        }
    }

    @Override
    public boolean save(FileModel fileModel, String fileName, String fileType, String createdBy) {
        try {
            AmazonDynamoDB dbclient = AmazonDynamoDBClientBuilder.standard().build();
            DynamoDBMapper mapper = new DynamoDBMapper(dbclient);
            fileModel.setFileName(fileName);
            fileModel.setFileType(fileType);
            fileModel.setCreatedBy(createdBy);
            mapper.save(fileModel);
            dbclient.shutdown();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


//    public List<FileModel> getAllFiles() {
//        AmazonDynamoDB dbclient = AmazonDynamoDBClientBuilder.standard().build();
//        try {
//            DynamoDBMapper mapper = new DynamoDBMapper(dbclient);
//            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
//            scanExpression.setLimit(10);
//            return mapper.scan(FileModel.class, scanExpression);
//        } finally {
//            dbclient.shutdown();
//        }
//
//
//    }

    @Override
    public List<FileModel> getAllItems(int limit, Map<String, AttributeValue> exclusiveStartKey) {
        AmazonDynamoDB dbclient = AmazonDynamoDBClientBuilder.standard().build();
        try {
            DynamoDBMapper mapper = new DynamoDBMapper(dbclient);

            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                    .withLimit(limit);
            if (exclusiveStartKey != null) {
                scanExpression.setExclusiveStartKey(null);
            }
            return mapper.scan(FileModel.class, scanExpression);

        } finally {
            dbclient.shutdown();
        }
    }


}
