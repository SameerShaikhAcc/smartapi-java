package com.smartcn.smartcndashboard.utils;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONException;
import org.json.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTokenVerify {

	public static JSONObject verifyJWT(String xAccessToken, String secretKey) {

		JSONObject response = new JSONObject();

		try {

			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
					.parseClaimsJws(xAccessToken).getBody();

			if (claims != null && !(claims.isEmpty())) {
				response.put("claims", claims);
				response.put("message", "valid");
				response.put("statusCode", 200);
			} else {
				response.put("message", "invalid");
				response.put("statusCode", 400);

			}

		} catch (Exception e) {
			try {
				response.put("message", e.getMessage());
				response.put("statusCode", 400);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return response;

	}

}
