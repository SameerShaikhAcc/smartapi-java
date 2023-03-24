package com.smartcn.smartcndashboard.addtemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.smartcn.smartcndashboard.listprocessstatus.ProcessStatusDTO;
import com.smartcn.smartcndashboard.utils.GlobalConstants;
import com.smartcn.smartcndashboard.utils.JWTokenVerify;
import com.smartcn.smartcndashboard.utils.StatusMessage;

@CrossOrigin(origins = "*")
@Transactional
@RestController
@RequestMapping(path = "/v1/template")
public class TemplateController {
	@Autowired
	private TemplateService templateService;

	public TemplateController(TemplateService templateService) {
		super();
		this.templateService = templateService;
	}

	@Value("${jwt.secret}")
	private String secret;
	String createdDate;
	
	//GlobalConstants const=new GlobalConstants();
	String bucketName =GlobalConstants.templateBucketName;
	AmazonS3 s3Client;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	// StatusResponse statusMessage = new StatusResponse();

	@PostMapping("/upload/{segmentName}")
	public ResponseEntity<StatusMessage> uploadFile(@PathVariable("segmentName") String segmentName,
			@RequestParam(value = "docfile", required = true) MultipartFile docfile) {
		String fileSuffix = "";
System.out.println("Started");
		ClientConfiguration config = new ClientConfiguration();
		config.setConnectionTimeout(900000);
		config.setSocketTimeout(900000);
		s3Client = AmazonS3ClientBuilder.standard().withClientConfiguration(config).build();

		Date logsNow = new Date();
		
		String createdDate = new SimpleDateFormat("yyyy-MM-dd").format(logsNow);
		String[] dateArray = createdDate.split("-");
		String year = dateArray[0];
		String month = dateArray[1];
		String startTime = new SimpleDateFormat("HH:mm:ss").format(logsNow);
		String fileName = docfile.getOriginalFilename();
		String fileNameArray[] = fileName.split("\\.");
		String nameStamp = new SimpleDateFormat("_yyyyMMddHHmmssSSS").format(new Date());

		String newFileName = fileNameArray[0] + nameStamp + "." + fileNameArray[1];
		File newFile = new File(docfile.getOriginalFilename());
//		String bucketName = "";

		StatusMessage statusMessage = new StatusMessage();

		Random rand = new Random();
		int randNumber = rand.nextInt(1000);
		fileSuffix = new SimpleDateFormat(randNumber + "_yyyyMMddHHmmssSSS'.txt'").format(new Date());

		AmazonDynamoDB dbclient = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDBMapper mapper = new DynamoDBMapper(dbclient);
		final AmazonSQS sqsClient = AmazonSQSClientBuilder.defaultClient();
		try {

			switch (segmentName) {
			case "commcn":
				bucketName = "pdf-templates-bucket";
				break;
			}

			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(docfile.getBytes());
			fos.close();

			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":val1", new AttributeValue().withS(newFileName));
			// eav.put(":val2", new AttributeValue().withS(owner));
			eav.put(":val2", new AttributeValue().withS(createdDate));

			TemplateModel templateModel=new TemplateModel();
			templateModel.setName(newFileName);
			templateModel.setOwner(fileName);
			templateModel.setCreatedDate(createdDate);
			templateModel.setLastChange(createdDate);
			templateModel.setAction("");
			templateService.save(templateModel);

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("name =:val1 and owner =:val2 and createdDate =:val3 and lastChange =:val4 and action =:val5")
					.withExpressionAttributeValues(eav);
			List<TemplateModel> records = mapper.scan(TemplateModel.class, scanExpression);
			records = mapper.scan(TemplateModel.class, scanExpression);

			System.out.println("Before records : "+records.size());

			if (records.size() == 0) {
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("setbucketName", bucketName);
				// jsonObject.put("name", name);
				jsonObject.put("s3Key", fileName);
				jsonObject.put("newfile", newFileName);
				jsonObject.put("activityDate", createdDate);
				jsonObject.put("segmentName", segmentName);

				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newFileName, newFile);
				s3Client.putObject(putObjectRequest);
				statusMessage.setMessage("Saved in Bucket");
				statusMessage.setSuccess(true);

			} else if (records.size() > 0) {
				System.out.println("inside > 0");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("setbucketName", bucketName);
				jsonObject.put("getBucketname", bucketName);
				jsonObject.put("s3Key", fileName);
				jsonObject.put("newfile", newFileName);
				jsonObject.put("activityDate", createdDate);
				jsonObject.put("segmentName", segmentName);
				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newFileName,
						newFile);
				s3Client.putObject(putObjectRequest);
//				newFile.delete();
//				s3Client.shutdown();
				statusMessage.setMessage("Upcate in Bucket Successfully");
				statusMessage.setSuccess(true);	
			}
			newFile.delete();
			s3Client.shutdown();
			dbclient.shutdown();
			return new ResponseEntity<>(statusMessage, HttpStatus.OK);
		} catch (Exception e) {
			statusMessage.setMessage(e.getMessage());
			statusMessage.setSuccess(false);
			e.printStackTrace();
			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@GetMapping("/fetch/{name}/{owner}/{createdDate}/{lastChange}/{action}")
	public ResponseEntity<StatusMessage> listTemplates(@PathVariable("name") String name, @PathVariable("owner") String owner, @PathVariable("createdDate") String createdDate,@PathVariable("lastChange") String lastChange,@PathVariable("action") String action, @RequestHeader("x-access-token") String token)
	{
		TemplateDTO templateDto = new TemplateDTO();
		StatusMessage statusMessage = new StatusMessage();
		try {
			JSONObject responseObj;
			responseObj = JWTokenVerify.verifyJWT(token, secret);
			System.out.println(responseObj.getString("message"));
			if (responseObj.getString("message").equals("valid")) {
				templateDto=templateService.fetchValues(name, owner, createdDate, lastChange, action);
			//}
			statusMessage.setListData(templateDto.getTemplateModel());
			statusMessage.setSuccess(true);
			statusMessage.setTotalCount(templateDto.getSize());
			statusMessage.setMessage("Data Retrived Successfull");
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
	
}
