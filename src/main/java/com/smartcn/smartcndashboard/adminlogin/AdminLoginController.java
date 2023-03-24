package com.smartcn.smartcndashboard.adminlogin;

import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.smartcn.smartcndashboard.dashboard.DashBoardModel;
import com.smartcn.smartcndashboard.dashboard.DashBoardModelChart;
import com.smartcn.smartcndashboard.utils.JWTokenVerify;
import com.smartcn.smartcndashboard.utils.Masking;
import com.smartcn.smartcndashboard.utils.StatusMessage;
import com.smartcn.smartcndashboard.utils.StatusResponse;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "*")
@Transactional
@RestController
@RequestMapping(path = "/v1/admin")
public class AdminLoginController implements RequestHandler<AdminLoginModel, Object> {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${maskSecretKey}")
	private String maskSecretKey;

	@Autowired
	private AdminLoginService adminLoginService;

	public AdminLoginController(AdminLoginService adminLoginService) {
		super();
		this.adminLoginService = adminLoginService;
	}

	@PostMapping("/user-registration")
	public ResponseEntity<StatusResponse> saveUser(@RequestBody AdminLoginModel adminLoginModel) {
		StatusResponse statusMessage = new StatusResponse();

		try {
			// encryptedPassword
			String password = adminLoginModel.getPassword();
			String email = adminLoginModel.getEmail();
			String name = adminLoginModel.getName();
			String encryptedPassword = Masking.encrypt(password, maskSecretKey);
			String department = adminLoginModel.getDepartment();
			String createdOn = adminLoginModel.getCreatedOn();
			String createdBy = adminLoginModel.getCreatedBy();
			AdminLoginModel AdminLoginModelSave = new AdminLoginModel();
			AdminLoginModelSave.setEmail(email);
			AdminLoginModelSave.setPassword(encryptedPassword);
			AdminLoginModelSave.setName(name);
			AdminLoginModelSave.setDepartment(department);
			AdminLoginModelSave.setCreatedOn(createdOn);
			AdminLoginModelSave.setCreatedBy(createdBy);

			// check email already exist
			AdminLoginModel adminModelResponse = adminLoginService.getByEmail(email);
			if (adminModelResponse == null) {
				adminLoginService.save(AdminLoginModelSave);
				statusMessage.setMessage("save sucessfully");
				statusMessage.setSuccess(true);
				statusMessage.setStatusCode(200);
				return new ResponseEntity<>(statusMessage, HttpStatus.CREATED);
			} else {
				// return error message
				statusMessage.setMessage("Email id: " + email + " already exist"); // change
				statusMessage.setSuccess(false);
				statusMessage.setStatusCode(200);
				return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			statusMessage.setMessage(e.getMessage());
			statusMessage.setSuccess(false);
			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<StatusResponse> saveFields(@RequestBody DashBoardModel dashBoardModel) {
		StatusResponse statusMessage = new StatusResponse();
		try {
			String result = adminLoginService.saveFields(dashBoardModel);
			statusMessage.setMessage(result);
			statusMessage.setSuccess(true);
			return new ResponseEntity<>(statusMessage, HttpStatus.OK);
		} catch (Exception e) {
			statusMessage.setMessage(e.getMessage());
			statusMessage.setSuccess(false);
			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/savechart")
	public ResponseEntity<StatusResponse> saveFieldschart(@RequestBody DashBoardModelChart dashBoardModel) {
		StatusResponse statusMessage = new StatusResponse();

		try {

			String result = adminLoginService.saveFieldschart(dashBoardModel);

			statusMessage.setMessage(result);
			statusMessage.setSuccess(true);
			return new ResponseEntity<>(statusMessage, HttpStatus.OK);
		} catch (Exception e) {
			statusMessage.setMessage(e.getMessage());
			statusMessage.setSuccess(false);
			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<StatusResponse> loginUser(@RequestBody AdminLoginModel adminLoginModel) {
		StatusResponse statusMessage = new StatusResponse();

		try {
			// encryptedPassword
			String password = adminLoginModel.getPassword();
			String email = adminLoginModel.getEmail();
			// get data by email id
			AdminLoginModel adminModelResponse = adminLoginService.getByEmail(email);
			if (adminModelResponse != null) {
				String decryptedPassword = Masking.decrypt(adminModelResponse.getPassword(), maskSecretKey);
				if (decryptedPassword.equals(password)) {
					String token = createJWT(email, secret);
					System.out.println(token);
					statusMessage.setMessage("login successfully");
					statusMessage.setToken(token);
					statusMessage.setName(adminModelResponse.getName());
					statusMessage.setSuccess(true);
					statusMessage.setStatusCode(200);
					return new ResponseEntity<>(statusMessage, HttpStatus.CREATED);
				} else {
					statusMessage.setMessage("Password does not match ");
					statusMessage.setSuccess(false);
					statusMessage.setStatusCode(200);
					return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
				}

			} else {
				// return error message
				statusMessage.setMessage("Email id: " + email + " does not exist"); // change
				statusMessage.setSuccess(true);
				statusMessage.setStatusCode(200);
				return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			statusMessage.setMessage(e.getMessage() + " Outer Exception");
			statusMessage.setSuccess(false);
			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/forgetPassword")
	public ResponseEntity<StatusResponse> forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
		StatusResponse statusMessage = new StatusResponse();

		try {

			String redirectUrl = forgetPasswordDto.getRedirectUrl();
			String email = forgetPasswordDto.getEmail();
			// get data by email id

			AdminLoginModel adminModelResponse = adminLoginService.getByEmail(email);
//		System.out.println(adminModelResponse);
			if (adminModelResponse != null) {
				String genxAccessToken = createJWTForFP(email, redirectUrl, secret);

				String redirectUrlToken = redirectUrl + "/" + genxAccessToken;
				System.out.println("redirectUrlToken-->" + redirectUrlToken);

//        String token = createJWTForFP(email, redirectUrl, secret);
				@SuppressWarnings("unused")
				String result = adminLoginService.sendMail(email, redirectUrl, redirectUrlToken);

				statusMessage.setMessage("redirect successfully");
//        statusMessage.setToken(token);
				statusMessage.setSuccess(true);
				statusMessage.setStatusCode(200);
				return new ResponseEntity<>(statusMessage, HttpStatus.CREATED);

			} else {
				// return error message
				statusMessage.setMessage("Email id: " + email + " not exist"); // change
				statusMessage.setSuccess(false);
				statusMessage.setStatusCode(200);
				return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			statusMessage.setMessage(e.getMessage());
			statusMessage.setSuccess(false);
			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/forgetpasswordupdate")
	public ResponseEntity<StatusResponse> forgetPasswordUpdate(@RequestBody AdminLoginModel adminLoginModel,
			@RequestHeader("x-access-token") String token) {
		StatusResponse statusMessage = new StatusResponse();

		try {
			JSONObject responseObj;
			responseObj = JWTokenVerify.verifyJWT(token, secret);
			if (responseObj.getString("message").equals("valid")) {
				AdminLoginModel adminModelResponse = adminLoginService.getByEmail(adminLoginModel.getEmail());
				if (adminModelResponse != null) {
					String encryptedPassword = Masking.encrypt(adminLoginModel.getPassword(), maskSecretKey);
					adminModelResponse.setPassword(encryptedPassword);
					adminLoginService.save(adminModelResponse);
					statusMessage.setSuccess(true);
					statusMessage.setMessage("password updated successfully");
				} else {
					statusMessage.setSuccess(false);
					statusMessage.setMessage("Email id is not registered");
				}

			}
			return new ResponseEntity<>(statusMessage, HttpStatus.OK);
		} catch (Exception e) {
			statusMessage.setMessage(e.getMessage());
			statusMessage.setSuccess(false);
			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/fetchlogindetails/{name}")
	public ResponseEntity<StatusMessage> fetchLoginValues(@PathVariable("name") String name){
		//	@RequestHeader("x-access-token") String token) {
		StatusMessage statusMessage = new StatusMessage();
		ArrayList<AdminDTO> adminList = new ArrayList<AdminDTO>();
		AdminLoginModel adminLoginModel=new AdminLoginModel();

		try {
//			JSONObject responseObj;
//			responseObj = JWTokenVerify.verifyJWT(token, secret);
			//if (responseObj.getString("message").equals("valid")) {
	//			System.out.println("Inside response object");
				AdminDTO adminModelResponse = adminLoginService.fetchAdminValues(adminLoginModel.getAdminId(),
						adminLoginModel.getName(), adminLoginModel.getPassword(), adminLoginModel.getEmail(),
						adminLoginModel.getDepartment(), adminLoginModel.getCreatedOn(),
						adminLoginModel.getCreatedBy());
			//	if (adminModelResponse != null) {
					System.out.println("adminModelResponse : " +adminModelResponse);
					adminLoginService.save(adminModelResponse);
					AdminDTO adminDTO = new AdminDTO();
					adminList.add(adminDTO);
					statusMessage.setSuccess(true);
					statusMessage.setListData(adminList);
					statusMessage.setMessage("Users added successfully");
//				} else {
//					statusMessage.setSuccess(false);
//					statusMessage.setMessage("Users already present");
//				}

		//	}
			return new ResponseEntity<>(statusMessage, HttpStatus.OK);
		} catch (Exception e) {
			statusMessage.setMessage(e.getMessage());
			statusMessage.setSuccess(false);
			return new ResponseEntity<>(statusMessage, HttpStatus.BAD_REQUEST);
		}
	}

	// @PostMapping("/forgetPassword")
	// public ResponseEntity<StatusResponse> resetPassword(@RequestBody
	// AdminLoginModel
	// adminLoginModel) {
	// StatusResponse statusResponse = new StatusResponse();
	// try {
	// String password = adminLoginModel.getPassword();
	// String email = adminLoginModel.getEmail();
	// String encryptedPassword = Masking.encrypt(password, maskSecretKey);
	// AdminLoginModel adminLoginModelResponse =
	// adminLoginService.getByEmail(email);
	// if (adminLoginModelResponse != null) {
	// adminLoginService.changePassword(email, encryptedPassword);
	// statusResponse.setMessage("Password changed sucessfully");
	// statusResponse.setName(adminLoginModel.getName());
	// statusResponse.setSuccess(true);
	// statusResponse.setStatusCode(200);
	// return new ResponseEntity<>(statusResponse, HttpStatus.OK);
	// } else {
	// // return error message
	// statusResponse.setMessage("Email id: " + email + " not found"); // change
	// statusResponse.setSuccess(false);
	// statusResponse.setStatusCode(401);
	// return new ResponseEntity<>(statusResponse, HttpStatus.UNAUTHORIZED);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// statusResponse.setMessage(e.getMessage());
	// statusResponse.setSuccess(false);
	// statusResponse.setStatusCode(400);
	// return new ResponseEntity<>(statusResponse, HttpStatus.BAD_REQUEST);
	// }
	// }

	private String createJWTForFP(String email, String redirectUrl, String jwtSecretKey) {
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, 1);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(email).setIssuedAt(now).setIssuer(redirectUrl)
				.signWith(signatureAlgorithm, signingKey).setExpiration(cal.getTime()); // .

		return builder.compact();
	}

	private String createJWT(String email, String jwtSecretKey) {
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, 1);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(email).setIssuedAt(now).signWith(signatureAlgorithm, signingKey)
				.setExpiration(cal.getTime());

		return builder.compact();
	}

	@Override
	public Object handleRequest(AdminLoginModel arg0, Context arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
