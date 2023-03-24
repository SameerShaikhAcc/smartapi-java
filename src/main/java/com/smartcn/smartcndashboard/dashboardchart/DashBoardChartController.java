package com.smartcn.smartcndashboard.dashboardchart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.smartcn.smartcndashboard.dashboard.DashBoardModelChart;
import com.smartcn.smartcndashboard.utils.JWTokenVerify;
import com.smartcn.smartcndashboard.utils.StatusMessage;

@CrossOrigin(origins = "*")
@Transactional
@RestController
@RequestMapping(path = "/v1/dashboardchart")
public class DashBoardChartController {

	@Value("${jwt.secret}")
	private String secret;
	@Autowired
	private DashBoardChartService dashBoardChartService;

	public DashBoardChartController(DashBoardChartService dashBoardChartService) {
		super();
		this.dashBoardChartService = dashBoardChartService;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/fetch/{yearSearched}/{segmentName}")
	public ResponseEntity<StatusMessage> fetchData(@PathVariable("yearSearched") String yearSearched,
												   @PathVariable("segmentName") String segmentName, @RequestHeader("x-access-token") String token) {
		StatusMessage statusMessage = new StatusMessage<>();
		try {
			JSONObject responseObj;
			responseObj = JWTokenVerify.verifyJWT(token, secret);
			if (responseObj.getString("message").equals("valid")) {
			AmazonDynamoDB dbclient = AmazonDynamoDBClientBuilder.standard().build();
			DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dbclient);
			List<DashBoardModelChart> yearList = new ArrayList<DashBoardModelChart>();
			List<DashBoardChartDTO> monthList = new ArrayList<DashBoardChartDTO>();

			if (segmentName.equals("null")) {
				Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
				eav.put(":val1", new AttributeValue().withS(yearSearched));

				DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
						.withFilterExpression("yearSearched =:val1").withExpressionAttributeValues(eav);

				yearList = dynamoDBMapper.scan(DashBoardModelChart.class, scanExpression);
			
			} else if (!(segmentName.equals("null"))) {
				Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
				eav.put(":val1", new AttributeValue().withS(yearSearched));
				eav.put(":val2", new AttributeValue().withS(segmentName));

				DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
						.withFilterExpression("yearSearched =:val1 and segmentName =:val2")
						.withExpressionAttributeValues(eav);

				yearList = dynamoDBMapper.scan(DashBoardModelChart.class, scanExpression);
			}
					
			monthList = dashBoardChartService.fetchRecords(yearList);
			
			
			if (yearList.size() > 0) {
				statusMessage.setMessage("Data Fetch SuccessFul");
				statusMessage.setSuccess(true);
				statusMessage.setListData(monthList);
			} else {
				statusMessage.setMessage("Data not Present");
				statusMessage.setSuccess(true);
				statusMessage.setListData(monthList);
			}
			return new ResponseEntity<>(statusMessage, HttpStatus.OK);
		} else {
			statusMessage.setMessage("Token Invalid");
			statusMessage.setSuccess(false);
			return new ResponseEntity<>(statusMessage, HttpStatus.UNAUTHORIZED);
		}
		} catch (Exception e) {
			statusMessage.setMessage(e.getMessage());
			statusMessage.setSuccess(false);
			e.printStackTrace();
			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
		}
	}
}