package com.smartcn.smartcndashboard.listprocessstatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.smartcn.smartcndashboard.dashboard.DashBoardModel;

@Service
public class ProcessStatusReportServiceImpl implements ProcessStatusReportService {
	AmazonDynamoDB dbclient = AmazonDynamoDBClientBuilder.standard().build();
	DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dbclient);

	@Override
	public ProcessStatusDTO fetchWOValues(long skip, long limit) {
		Date logsNow = new Date();
		String activityDate = new SimpleDateFormat("yyyy-MM-dd").format(logsNow);
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(activityDate));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("activityDate =:val1")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();
		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});
		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchAll(String segmentName, String processStatus, String startDate,
			String endDate, long skip, long limit) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//		eav.put(":val1", new AttributeValue().withS(fileName));
		eav.put(":val2", new AttributeValue().withS(segmentName));
		eav.put(":val3", new AttributeValue().withS(processStatus));
		eav.put(":val4", new AttributeValue().withS(startDate));
		eav.put(":val5", new AttributeValue().withS(endDate));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression(
				"segmentName =:val2 and processStatus =:val3 and activityDate between :val4 and :val5")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		arrayFinalList.addAll(betweenRecords);

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWODate(String segmentName, String processStatus, long skip,
			long limit) {

		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//		eav.put(":val1", new AttributeValue().withS(fileName));
		eav.put(":val2", new AttributeValue().withS(segmentName));
		eav.put(":val3", new AttributeValue().withS(processStatus));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("segmentName =:val2 and processStatus =:val3")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWOSegment(String processStatus, String startDate, String endDate,
			long skip, long limit) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//		eav.put(":val1", new AttributeValue().withS(fileName));
		eav.put(":val3", new AttributeValue().withS(processStatus));
		eav.put(":val4", new AttributeValue().withS(startDate));
		eav.put(":val5", new AttributeValue().withS(endDate));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression(
						"processStatus =:val3 and activityDate between :val4 and :val5")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWOfileName(String segmentName, String processStatus, String startDate, String endDate,
			long skip, long limit) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(segmentName));
		eav.put(":val3", new AttributeValue().withS(processStatus));
		eav.put(":val4", new AttributeValue().withS(startDate));
		eav.put(":val5", new AttributeValue().withS(endDate));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression(
						"segmentName =:val1 and processStatus =:val3 and activityDate between :val4 and :val5")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWOStatus(String segmentName, String startDate, String endDate,
			long skip, long limit) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//		eav.put(":val1", new AttributeValue().withS(fileName));
		eav.put(":val2", new AttributeValue().withS(segmentName));
		eav.put(":val4", new AttributeValue().withS(startDate));
		eav.put(":val5", new AttributeValue().withS(endDate));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("segmentName =:val2 and activityDate between :val4 and :val5")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWithFN(long skip, long limit) {

		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//		eav.put(":val1", new AttributeValue().withS(fileName));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWithStatus(String processStatus, long skip, long limit) {

		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(processStatus));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("processStatus =:val1").withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWithSegment(String segmentName, long skip, long limit) {

		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(segmentName));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("segmentName =:val1")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWithDate(String startDate, String endDate, long skip, long limit) {

		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val4", new AttributeValue().withS(startDate));
		eav.put(":val5", new AttributeValue().withS(endDate));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("activityDate between :val4 and :val5").withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		for (DashBoardModel a : betweenRecords) {
			System.out.println("____________________________________________________");
			System.out.println(a);
			System.out.println("____________________________________________________");
		}
		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWOFNSG(String processStatus, String startDate, String endDate, long skip, long limit) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val3", new AttributeValue().withS(processStatus));
		eav.put(":val4", new AttributeValue().withS(startDate));
		eav.put(":val5", new AttributeValue().withS(endDate));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("processStatus =:val3 and activityDate between :val4 and :val5")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWOSGST(String startDate, String endDate, long skip, long limit) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//		eav.put(":val1", new AttributeValue().withS(fileName));
		eav.put(":val4", new AttributeValue().withS(startDate));
		eav.put(":val5", new AttributeValue().withS(endDate));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("activityDate between :val4 and :val5")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWOSGDate(String processStatus, long skip, long limit) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//		eav.put(":val1", new AttributeValue().withS(fileName));
		eav.put(":val3", new AttributeValue().withS(processStatus));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("processStatus =:val3").withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWOFNST(String segmentName, String startDate, String endDate, long skip, long limit) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(segmentName));
		eav.put(":val4", new AttributeValue().withS(startDate));
		eav.put(":val5", new AttributeValue().withS(endDate));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("segmentName =:val2 and activityDate between :val4 and :val5")
				.withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWOFNDate(String segmentName, String processStatus, long skip, long limit) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(segmentName));
		eav.put(":val3", new AttributeValue().withS(processStatus));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("segmentName =:val2 and processStatus =:val3").withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

	@Override
	public ProcessStatusDTO fetchWOSTDate(String segmentName, long skip, long limit) {
		List<DashBoardModel> betweenRecords = new ArrayList<DashBoardModel>();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//		eav.put(":val1", new AttributeValue().withS(fileName));
		eav.put(":val2", new AttributeValue().withS(segmentName));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("segmentName =:val2").withExpressionAttributeValues(eav);
		betweenRecords = dynamoDBMapper.scan(DashBoardModel.class, scanExpression);

		List<DashBoardModel> arrayFinalList = new ArrayList<DashBoardModel>();

		Stream<DashBoardModel> stream = betweenRecords.stream();
		Comparator<? super DashBoardModel> comparator = new Comparator<DashBoardModel>() {
			public int compare(DashBoardModel m1, DashBoardModel m2) {
				return m2.getActivityDate().compareTo(m1.getActivityDate());
			}
		};
		stream.skip(skip).limit(limit).sorted(comparator).forEach((a) -> {
			arrayFinalList.add(a);
		});

		ProcessStatusDTO dto = new ProcessStatusDTO();
		dto.setDashBoardModel(arrayFinalList);
		dto.setSize(betweenRecords.size());
		return dto;
	}

}
