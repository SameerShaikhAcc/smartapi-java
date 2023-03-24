package com.smartcn.smartcndashboard.fileservice.v1;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService {
    FileModel putFileonS3(MultipartFile docfile);

    boolean save(FileModel fileModel, String fileName, String fileType, String createdBy);

    List<FileModel> getAllItems(int limit, Map<String, AttributeValue> exclusiveStartKey);
}
