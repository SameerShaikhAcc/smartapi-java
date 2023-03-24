package com.smartcn.smartcndashboard.listprocessstatus;

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

import com.smartcn.smartcndashboard.utils.JWTokenVerify;
import com.smartcn.smartcndashboard.utils.StatusMessage;

@CrossOrigin(origins = "*")
@Transactional
@RestController
@RequestMapping(path = "/v1/list")
public class ProcessStatusReportController {
	@Value("${jwt.secret}")
	private String secret;
	@Autowired
	private ProcessStatusReportService ProcessStatusReportService;

	public ProcessStatusReportController(ProcessStatusReportService processStatusReportService) {
		super();
		ProcessStatusReportService = processStatusReportService;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/filter/{segmentName}/{status}/{startDate}/{endDate}/{skip}/{limit}")
	public ResponseEntity<StatusMessage> listData(@PathVariable("segmentName") String segmentName,
			@PathVariable("status") String status, @PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate, @PathVariable("skip") long skip, @PathVariable("limit") long limit,
			@RequestHeader("x-access-token") String token) {
//		List<DashBoardModel> dashBoardModel = new ArrayList<DashBoardModel>();
		ProcessStatusDTO dashBoardModel = new ProcessStatusDTO();
		StatusMessage statusMessage = new StatusMessage();
		try {
			JSONObject responseObj;
			responseObj = JWTokenVerify.verifyJWT(token, secret);
			System.out.println(responseObj.getString("message"));
			if (responseObj.getString("message").equals("valid")) {
//				String startDate = "";
//				String endDate = "";
//				if (!(sentDate.equals("null"))) {
//					String dateArray[] = sentDate.split("~");
//					startDate = dateArray[0];
//					endDate = dateArray[1];
//				}

//				String sentDate = startDate;
				if (segmentName.equals("null") && status.equals("null") && startDate.equals("null")
						&& endDate.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWOValues(skip, limit);
				} else if (segmentName.equals("null") && status.equals("null") && startDate.equals("null")
						&& endDate.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWithFN(skip, limit);
				} else if (segmentName.equals("null") && startDate.equals("null") && endDate.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWithStatus(status, skip, limit);
				} else if (status.equals("null") && startDate.equals("null") && endDate.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWithSegment(segmentName, skip, limit);
				} else if (segmentName.equals("null") && status.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWithDate(startDate, endDate, skip, limit);
				} else if (segmentName.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWOFNSG(status, startDate, endDate, skip, limit);
				} else if (segmentName.equals("null") && status.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWOSGST(startDate, endDate, skip, limit);
				} else if (segmentName.equals("null") && startDate.equals("null") && endDate.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWOSGDate(status, skip, limit);
				} else if (status.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWOFNST(segmentName, startDate, endDate, skip,
							limit);
				} else if (startDate.equals("null") && endDate.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWOFNDate(segmentName, status, skip, limit);
				} else if (status.equals("null") && startDate.equals("null") && endDate.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWOSTDate(segmentName, skip, limit);
				} else if (status.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWOStatus(segmentName, startDate, endDate, skip,
							limit);
				} else if (segmentName.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWOSegment(status, startDate, endDate, skip, limit);
				} else if (startDate.equals("null") && endDate.equals("null")) {
					dashBoardModel = ProcessStatusReportService.fetchWODate(segmentName, status, skip, limit);
				} else {
					dashBoardModel = ProcessStatusReportService.fetchAll(segmentName, status, startDate, endDate, skip,
							limit);
				}
				statusMessage.setListData(dashBoardModel.getDashBoardModel());
				statusMessage.setSuccess(true);
				statusMessage.setTotalCount(dashBoardModel.getSize());
				statusMessage.setMessage("Data Retrived Successfull");
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