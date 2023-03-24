package com.smartcn.smartcndashboard.adminlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.smartcn.smartcndashboard.dashboard.DashBoardModel;
import com.smartcn.smartcndashboard.dashboard.DashBoardModelChart;
import com.smartcn.smartcndashboard.utils.AmazonSES;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	@SuppressWarnings("unused")
	private JSONObject metadataJson;

	public AdminLoginServiceImpl() {
		super();

		// TODO Auto-generated constructor stub
	}

	public AdminLoginServiceImpl(DynamoDBMapper dynamoDBMapper) {
		super();
		System.out.println("In Service Impl");
		this.dynamoDBMapper = dynamoDBMapper;
	}

	@Override
	public String save(AdminLoginModel adminLoginModel) {

		dynamoDBMapper.save(adminLoginModel);
		return "Successfull";
	}

	@Override
	public AdminLoginModel getByEmail(String email) {
		List<AdminLoginModel> betweenRecords = new ArrayList<AdminLoginModel>();
		AdminLoginModel adminLoginModel = new AdminLoginModel();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(email));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("email =:val1")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(AdminLoginModel.class, scanExpression);
		if (betweenRecords.size() > 0) {
			adminLoginModel = betweenRecords.get(0);
			return adminLoginModel;
		} else {
			return null;
		}
	}

	@Override
	public String saveFields(DashBoardModel dashBoardModel) {
		dynamoDBMapper.save(dashBoardModel);

		return "Successful";
	}

	@Override
	public void changePassword(String email, String encryptedPassword) {
		AdminLoginModel adminLoginModel = new AdminLoginModel();
		adminLoginModel = getByEmail(email);
		adminLoginModel.setPassword(encryptedPassword);
		dynamoDBMapper.save(adminLoginModel);
	}

	@Override
	public String saveFieldschart(DashBoardModelChart dashBoardModel) {
		dynamoDBMapper.save(dashBoardModel);

		return "Successful";
	}

	@Override
	public String sendMail(String email, String redirectUrl, String token) {
		AmazonSES amazonSES = new AmazonSES();
		String htmlTemplate = "" + token;
		String subjectLine = "Reset Password";
		String result = amazonSES.sendMail(email, subjectLine, htmlTemplate);
		return result;
	}

	@Override
	public AdminDTO fetchAdminValues(String adminId, String name, String password, String email, String department,
			String createdOn, String createdBy) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		
		
		//eav.put(":val1", new AttributeValue().withS(adminId));
		eav.put(":val1", new AttributeValue().withS(name));
//		eav.put(":val3", new AttributeValue().withS(password));
//		eav.put(":val4", new AttributeValue().withS(email));
//		eav.put(":val5", new AttributeValue().withS(department));
//		eav.put(":val6", new AttributeValue().withS(createdOn));
//		eav.put(":val7", new AttributeValue().withS(createdBy));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("name =:val1")
//						"adminId =:val1 and name =:val2 and password =:val3 and email =:val4 and department =:val5 and createdOn = val6 and createdBy =:val7")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<AdminLoginModel> arrayFinalList = new ArrayList<AdminLoginModel>();

		AdminDTO dto = new AdminDTO();
		dto.setAdminModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		System.out.println("Size : "+dto);
		return dto;
	}

	@Override
	public String save(AdminDTO adminModelResponse) {
		// TODO Auto-generated method stub
		dynamoDBMapper.save(adminModelResponse);

		return "Successful";
		
	}

}
