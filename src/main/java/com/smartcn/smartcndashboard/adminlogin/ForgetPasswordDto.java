package com.smartcn.smartcndashboard.adminlogin;

public class ForgetPasswordDto {

	private String redirectUrl;
	private String email;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ForgetPasswordDto [redirectUrl=" + redirectUrl + ", email=" + email + "]";
	}

}
