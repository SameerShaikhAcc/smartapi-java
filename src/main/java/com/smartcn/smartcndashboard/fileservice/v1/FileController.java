package com.smartcn.smartcndashboard.fileservice.v1;



import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import com.smartcn.smartcndashboard.addtemplate.TemplateDTO;
import com.smartcn.smartcndashboard.utils.JWTokenVerify;
import com.smartcn.smartcndashboard.utils.StatusMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@Transactional
@RestController
@RequestMapping(path = "/v1/file")
public class FileController {


    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private FileService fileService;

    public FileController(FileService fileService) {
        super();
        this.fileService = fileService;
    }


    @PostMapping("/upload/{fileName}/{fileType}/{createdBy}")
    public ResponseEntity<StatusMessage> uploadFile(@PathVariable("fileName") String fileName, @PathVariable("fileType") String fileType,
                                                    @PathVariable("createdBy") String createdBy,
                                                    @RequestParam(value = "docfile", required = true) MultipartFile docfile) {
        StatusMessage statusMessage = new StatusMessage();
        try {
            FileModel fileModel = fileService.putFileonS3(docfile);
            if (fileModel != null) {
                boolean insertFlag = fileService.save(fileModel, fileName, fileType, createdBy);
                if (insertFlag) {
                    statusMessage.setMessage("file saved sucessfully");
                    statusMessage.setSuccess(true);
                    return new ResponseEntity<>(statusMessage, HttpStatus.CREATED);
                }
            } else {
                statusMessage.setMessage("Bad Request"); // change
                statusMessage.setSuccess(false);
                return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            statusMessage.setMessage(e.getLocalizedMessage());
            statusMessage.setSuccess(false);
            e.printStackTrace();
            return new ResponseEntity<>(statusMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }


    @GetMapping("/fetchAll/{limit}/{offset}}")
    public ResponseEntity<StatusMessage> listTemplates(@PathVariable("limit") int limit,
                                                       @PathVariable("offset") int offset,
                                                       @RequestHeader("x-access-token") String token)
    {
        TemplateDTO templateDto = new TemplateDTO();
        StatusMessage statusMessage = new StatusMessage();
        try {
            JSONObject responseObj;
            responseObj = JWTokenVerify.verifyJWT(token, secret);
            System.out.println(responseObj.getString("message"));
            if (responseObj.getString("message").equals("valid")) {

                Map<String, AttributeValue> exclusiveStartKey = null;
                if (offset > 0) {
                    exclusiveStartKey = encodeExclusiveStartKey(offset);
                }
                List<FileModel> fileModelList =  fileService.getAllItems(limit, exclusiveStartKey);
                if (fileModelList.size() > 0) {
                    statusMessage.setMessage("Data Fetch SuccessFul");
                    statusMessage.setSuccess(true);
                    statusMessage.setListData(fileModelList);
                    statusMessage.setTotalCount(fileModelList.size());
                } else {
                    statusMessage.setMessage("Data not Present");
                    statusMessage.setSuccess(true);
                    statusMessage.setListData(fileModelList);
                }
                return new ResponseEntity<>(statusMessage, HttpStatus.OK);
            } else {
                statusMessage.setMessage("Token Invalid");
                statusMessage.setSuccess(false);
                return new ResponseEntity<>(statusMessage, HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception e) {
            statusMessage.setMessage(e.getMessage());
            statusMessage.setSuccess(false);
            e.printStackTrace();
            return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
        }
    }


    private Map<String, AttributeValue> encodeExclusiveStartKey(int offset) {
        List<FileModel> items = fileService.getAllItems(offset, null);
        if (items.size() > 0) {
            FileModel lastItem = items.get(items.size() - 1);
            Map<String, AttributeValue> exclusiveStartKey = new HashMap<>();
//            exclusiveStartKey.put("id", AttributeValue.builder().s(lastItem.getFileId()).build());
            return exclusiveStartKey;
        }
        return null;
    }

}
