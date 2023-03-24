package com.smartcn.smartcndashboard.dashboard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.smartcn.smartcndashboard.utils.JWTokenVerify;
import com.smartcn.smartcndashboard.utils.StatusMessage;

@CrossOrigin(origins = "*")
@Transactional
@RestController
@RequestMapping(path = "/v1/dashboard")
public class DashBoardController {
	@Autowired
	private DashBoardService dashBoardService;

	public DashBoardController(DashBoardService dashBoardService) {
		super();
		this.dashBoardService = dashBoardService;
	}

	@Value("${jwt.secret}")
	private String secret;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/fetch/{segmentName}/{sentDate}")
	public ResponseEntity<StatusMessage> fetchData(@PathVariable("segmentName") String segmentName,
												   @PathVariable("sentDate") String sentDate, @RequestHeader("x-access-token") String token) {

		StatusMessage statusMessage = new StatusMessage<>();
		try {
			AmazonDynamoDB dbclient = AmazonDynamoDBClientBuilder.standard().build();
			DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dbclient);
			JSONObject responseObj;
			responseObj = JWTokenVerify.verifyJWT(token, secret);
			if (responseObj.getString("message").equals("valid")) {
				Date logsNow = new Date();
				System.out.println(logsNow);
				String activityDate = new SimpleDateFormat("yyyy-MM-dd").format(logsNow);
				System.out.println(activityDate);
				String[] dateArray = activityDate.split("-");
				String year = dateArray[0];

				List<DashBoardModel> displayList = new ArrayList<DashBoardModel>();
				List<DashBoardModelChart> compareOneList = new ArrayList<DashBoardModelChart>();
				List<DashBoardModelChart> compareTwoList = new ArrayList<DashBoardModelChart>();
				ArrayList<DashBoardModelDTO> finalList = new ArrayList<DashBoardModelDTO>();
				DashBoardModel display = new DashBoardModel();
				if (segmentName.equals("null") && sentDate.equals("null")) {

					DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.setTime(sourceFormat.parse(activityDate));
					cal.add(Calendar.MONTH, -1);
					Date result = cal.getTime();
					String oneMonth = new SimpleDateFormat("MM").format(result);
					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(sourceFormat.parse(activityDate));
					cal2.add(Calendar.MONTH, -2);
					Date result2 = cal2.getTime();
					String twoMonth = new SimpleDateFormat("MM").format(result2);

					Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
					eav.put(":val1", new AttributeValue().withS(activityDate));
					DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
							.withFilterExpression("activityDate =:val1").withExpressionAttributeValues(eav);

					displayList = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);
					Map<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
					eav1.put(":val1", new AttributeValue().withS(year));
					eav1.put(":val2", new AttributeValue().withS(oneMonth));
					DynamoDBScanExpression scanExpression1 = new DynamoDBScanExpression()
							.withFilterExpression("yearSearched =:val1 and monthSearched =:val2")
							.withExpressionAttributeValues(eav1);
					compareOneList = dynamoDBMapper.scan(DashBoardModelChart.class, scanExpression1);
					Map<String, AttributeValue> eav2 = new HashMap<String, AttributeValue>();
					eav2.put(":val1", new AttributeValue().withS(year));
					eav2.put(":val2", new AttributeValue().withS(twoMonth));
					DynamoDBScanExpression scanExpression2 = new DynamoDBScanExpression()
							.withFilterExpression("yearSearched =:val1 and monthSearched =:val2")
							.withExpressionAttributeValues(eav2);
					compareTwoList = dynamoDBMapper.scan(DashBoardModelChart.class, scanExpression2);
				} else if (sentDate.equals("null") && !(segmentName.equals("null"))) {
					DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.setTime(sourceFormat.parse(activityDate));
					cal.add(Calendar.MONTH, -1);
					Date result = cal.getTime();
					String oneMonth = new SimpleDateFormat("MM").format(result);
					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(sourceFormat.parse(activityDate));
					cal2.add(Calendar.MONTH, -2);
					Date result2 = cal2.getTime();
					String twoMonth = new SimpleDateFormat("MM").format(result2);
					Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
					eav.put(":val1", new AttributeValue().withS(activityDate));
					eav.put(":val2", new AttributeValue().withS(segmentName));
					DynamoDBScanExpression scanExpression0 = new DynamoDBScanExpression()
							.withFilterExpression("activityDate =:val1 and segmentName =:val2")
							.withExpressionAttributeValues(eav);
					displayList = dynamoDBMapper.scan(DashBoardModel.class, scanExpression0);
					Map<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
					eav1.put(":val1", new AttributeValue().withS(year));
					eav1.put(":val2", new AttributeValue().withS(oneMonth));
					eav1.put(":val3", new AttributeValue().withS(segmentName));
					DynamoDBScanExpression scanExpression1 = new DynamoDBScanExpression()
							.withFilterExpression("yearSearched =:val1 and monthSearched =:val2 and segmentName =:val3")
							.withExpressionAttributeValues(eav1);
					compareOneList = dynamoDBMapper.scan(DashBoardModelChart.class, scanExpression1);
					Map<String, AttributeValue> eav2 = new HashMap<String, AttributeValue>();
					eav2.put(":val1", new AttributeValue().withS(year));
					eav2.put(":val2", new AttributeValue().withS(twoMonth));
					eav2.put(":val3", new AttributeValue().withS(segmentName));
					DynamoDBScanExpression scanExpression2 = new DynamoDBScanExpression()
							.withFilterExpression("yearSearched =:val1 and monthSearched =:val2 and segmentName =:val3")
							.withExpressionAttributeValues(eav2);
					compareTwoList = dynamoDBMapper.scan(DashBoardModelChart.class, scanExpression2);
				} else if (!(sentDate.equals("null")) && segmentName.equals("null")) {

					String[] sentDateArray = sentDate.split("-");
					String sentYear = sentDateArray[0];
					DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.setTime(sourceFormat.parse(sentDate));
					cal.add(Calendar.MONTH, -1);
					Date result = cal.getTime();
					String oneMonth = new SimpleDateFormat("MM").format(result);
					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(sourceFormat.parse(sentDate));
					cal2.add(Calendar.MONTH, -2);
					Date result2 = cal2.getTime();
					String twoMonth = new SimpleDateFormat("MM").format(result2);
					Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
					eav.put(":val1", new AttributeValue().withS(sentDate));
					DynamoDBScanExpression scanExpression0 = new DynamoDBScanExpression()
							.withFilterExpression("activityDate =:val1").withExpressionAttributeValues(eav);
					displayList = dynamoDBMapper.scan(DashBoardModel.class, scanExpression0);
					Map<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
					eav1.put(":val1", new AttributeValue().withS(sentYear));
					eav1.put(":val2", new AttributeValue().withS(oneMonth));
					DynamoDBScanExpression scanExpression1 = new DynamoDBScanExpression()
							.withFilterExpression("yearSearched =:val1 and monthSearched =:val2")
							.withExpressionAttributeValues(eav1);
					compareOneList = dynamoDBMapper.scan(DashBoardModelChart.class, scanExpression1);
					Map<String, AttributeValue> eav2 = new HashMap<String, AttributeValue>();
					eav2.put(":val1", new AttributeValue().withS(sentYear));
					eav2.put(":val2", new AttributeValue().withS(twoMonth));
					DynamoDBScanExpression scanExpression2 = new DynamoDBScanExpression()
							.withFilterExpression("yearSearched =:val1 and monthSearched =:val2")
							.withExpressionAttributeValues(eav2);
					compareTwoList = dynamoDBMapper.scan(DashBoardModelChart.class, scanExpression2);
				} else if (!(sentDate.equals("null")) && !(segmentName.equals("null"))) {
					String[] sentDateArray = sentDate.split("-");
					String sentYear = sentDateArray[0];
					DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.setTime(sourceFormat.parse(sentDate));
					cal.add(Calendar.MONTH, -1);
					Date result = cal.getTime();
					String oneMonth = new SimpleDateFormat("MM").format(result);
					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(sourceFormat.parse(sentDate));
					cal2.add(Calendar.MONTH, -2);
					Date result2 = cal2.getTime();
					String twoMonth = new SimpleDateFormat("MM").format(result2);
					Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
					eav.put(":val1", new AttributeValue().withS(sentDate));
					eav.put(":val2", new AttributeValue().withS(segmentName));
					DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
							.withFilterExpression("activityDate =:val1 and segmentName =:val2")
							.withExpressionAttributeValues(eav);
					displayList = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);
					Map<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
					eav1.put(":val1", new AttributeValue().withS(sentYear));
					eav1.put(":val2", new AttributeValue().withS(oneMonth));
					eav1.put(":val3", new AttributeValue().withS(segmentName));
					DynamoDBScanExpression scanExpression1 = new DynamoDBScanExpression()
							.withFilterExpression("yearSearched =:val1 and monthSearched =:val2 and segmentName =:val3")
							.withExpressionAttributeValues(eav1);
					compareOneList = dynamoDBMapper.scan(DashBoardModelChart.class, scanExpression1);
					Map<String, AttributeValue> eav2 = new HashMap<String, AttributeValue>();
					eav2.put(":val1", new AttributeValue().withS(sentYear));
					eav2.put(":val2", new AttributeValue().withS(twoMonth));
					eav2.put(":val3", new AttributeValue().withS(segmentName));
					DynamoDBScanExpression scanExpression2 = new DynamoDBScanExpression()
							.withFilterExpression("yearSearched =:val1 and monthSearched =:val2 and segmentName =:val3")
							.withExpressionAttributeValues(eav2);
					compareTwoList = dynamoDBMapper.scan(DashBoardModelChart.class, scanExpression2);
				}
				display = dashBoardService.fetchDailyRecords(displayList);
				DashBoardModelDTO dashBoardModelDTO = new DashBoardModelDTO();
				dashBoardModelDTO.setDisplay(display);
				if (compareOneList.size() > 0) {
					dashBoardModelDTO.setCompareOne(compareOneList.get(0));
				} else {
					dashBoardModelDTO.setCompareOne(null);
				}
				if (compareTwoList.size() > 0) {
					dashBoardModelDTO.setCompareTwo(compareTwoList.get(0));
				} else {
					dashBoardModelDTO.setCompareTwo(null);
				}
				finalList.add(dashBoardModelDTO);
				if (displayList.size() > 0) {
					statusMessage.setMessage("Data Fetch SuccessFul");
					statusMessage.setSuccess(true);
					statusMessage.setListData(finalList);
				} else {
					statusMessage.setMessage("Data not Present");
					statusMessage.setSuccess(true);
					statusMessage.setListData(finalList);
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