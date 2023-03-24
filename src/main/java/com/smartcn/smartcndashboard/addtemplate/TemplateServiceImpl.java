package com.smartcn.smartcndashboard.addtemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.stereotype.Service;


@Service
public class TemplateServiceImpl implements TemplateService {

	@Override
	public TemplateModel save(TemplateModel templateModel) {
		AmazonDynamoDB dbclient = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDBMapper mapper = new DynamoDBMapper(dbclient);
		mapper.save(templateModel);
		dbclient.shutdown();
		return templateModel;
	}
	
	
	@Override
	public TemplateDTO fetchValues(String name,String owner,String createdDate,String lastChange,String action) {
		Date logsNow = new Date();
		createdDate = new SimpleDateFormat("yyyy-MM-dd").format(logsNow);
		AmazonDynamoDB dbclient = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDBMapper mapper = new DynamoDBMapper(dbclient);
	
		List<TemplateModel> records = new ArrayList<TemplateModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		Map<String, String> ean = new HashMap<String, String>(); ean.put("#n", "name");
		Map<String, String> ean1 = new HashMap<String, String>(); ean1.put("#a", "action");
		Map<String, String> ean2 = new HashMap<String, String>(); ean2.put("#o", "owner");


		
		eav.put(":val1",new AttributeValue().withS(name));
		eav.put(":val2", new AttributeValue().withS(owner));
		eav.put(":val3", new AttributeValue().withS(createdDate));
		eav.put(":val4", new AttributeValue().withS(lastChange));
		eav.put(":val5", new AttributeValue().withS(action));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("#n =:val1 and #o =:val2 and createdDate =:val3 and lastChange =:val4 and #a =:val5")
				.withExpressionAttributeValues(eav).withExpressionAttributeNames(ean);
		records = mapper.scan(TemplateModel.class, scanExpression);

		List<TemplateModel> arrayFinalList = new ArrayList<TemplateModel>();
	
		TemplateDTO dto = new TemplateDTO();
		dto.setTemplateModel(arrayFinalList);
		dto.setSize(records.size());
		return dto;
		
		
	}

	}

