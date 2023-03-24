package com.smartcn.smartcndashboard.adminlogin;

import com.smartcn.smartcndashboard.dashboard.DashBoardModel;
import com.smartcn.smartcndashboard.dashboard.DashBoardModelChart;
import com.smartcn.smartcndashboard.listprocessstatus.ProcessStatusDTO;

public interface AdminLoginService {

	String save(AdminLoginModel adminLoginModel);

	AdminLoginModel getByEmail(String email);

	String saveFields(DashBoardModel dashBoardModel);

	String sendMail(String email, String redirectUrl, String token);

	void changePassword(String email, String encryptedPassword);

	String saveFieldschart(DashBoardModelChart dashBoardModel);

	AdminDTO fetchAdminValues(String adminId, String name, String password, String email, String department,
			String createdOn, String createdBy);

	String save(AdminDTO adminModelResponse);

	//AdminDTO fetchAdminValues(AdminLoginModel adminLoginModel);
}
