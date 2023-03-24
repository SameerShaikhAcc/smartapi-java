package com.angelbroking.smartapi.sample;

import java.util.HashSet;
import java.util.Set;

import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.angelbroking.smartapi.models.User;
import com.angelbroking.smartapi.smartstream.SmartStreamListenerImpl;
import com.angelbroking.smartapi.smartstream.models.ExchangeType;
import com.angelbroking.smartapi.smartstream.models.SmartStreamSubsMode;
import com.angelbroking.smartapi.smartstream.models.TokenID;
import com.angelbroking.smartapi.smartstream.ticker.SmartStreamTicker;

public class Test {

	public static void main(String[] args) throws SmartAPIException {
		try {

			// Initialize SmartAPI
			String apiKey = "<apiKey>"; // PROVIDE YOUR API KEY HERE
			String clientId = "<clientId>"; // PROVIDE YOUR Client ID HERE
			String clientPin = "<clientPin>"; // PROVIDE YOUR Client PIN HERE
			String tOTP = "<tOTP>"; // PROVIDE THE CODE DISPLAYED ON YOUR AUTHENTICATOR APP - https://smartapi.angelbroking.com/enable-totp

			SmartConnect smartConnect = new SmartConnect(apiKey);

			// OPTIONAL - ACCESS_TOKEN AND REFRESH TOKEN
			/*
			 * SmartConnect smartConnect = new martConnect("<api_key>", "<YOUR_ACCESS_TOKEN>", "<YOUR_REFRESH_TOKEN>");
			 */

			// Set session expiry callback.
			/*
			 * smartConnect.setSessionExpiryHook(new SessionExpiryHook() {
			 * @Override 
			 * public void sessionExpired() {
			 * System.out.println("session expired"); 
			 * } 
			 * });
			 */

			// Generate User Session
			User user = smartConnect.generateSession(clientId, clientPin, tOTP);
			smartConnect.setAccessToken(user.getAccessToken());
			smartConnect.setUserId(user.getUserId());
			
			// SmartStreamTicker
			String feedToken = user.getFeedToken();
			SmartStreamTicker ticker = new SmartStreamTicker(clientId, feedToken, new SmartStreamListenerImpl());
			ticker.connect();
			ticker.subscribe(SmartStreamSubsMode.QUOTE, getTokens());
			Thread.currentThread().join();

			// token re-generate
			/*
			 * TokenSet tokenSet = smartConnect.renewAccessToken(user.getAccessToken(),
			 * user.getRefreshToken());
			 * smartConnect.setAccessToken(tokenSet.getAccessToken());
			 */

			/*
			 * Examples examples = new Examples(); 
			 * System.out.println("getProfile");
			 * examples.getProfile(smartConnect);
			 * 
			 * System.out.println("placeOrder"); 
			 * examples.placeOrder(smartConnect);
			 * 
			 * System.out.println("modifyOrder"); 
			 * examples.modifyOrder(smartConnect);
			 * 
			 * System.out.println("cancelOrder"); 
			 * examples.cancelOrder(smartConnect);
			 * 
			 * System.out.println("getOrder"); 
			 * examples.getOrder(smartConnect);
			 * 
			 * System.out.println("getLTP"); 
			 * examples.getLTP(smartConnect);
			 * 
			 * System.out.println("getTrades"); 
			 * examples.getTrades(smartConnect);
			 * 
			 * System.out.println("getRMS"); 
			 * examples.getRMS(smartConnect);
			 * 
			 * System.out.println("getHolding"); 
			 * examples.getHolding(smartConnect);
			 * 
			 * System.out.println("getPosition"); 
			 * examples.getPosition(smartConnect);
			 * 
			 * System.out.println("convertPosition");
			 * examples.convertPosition(smartConnect);
			 * 
			 * System.out.println("createRule"); 
			 * examples.createRule(smartConnect);
			 * 
			 * System.out.println("ModifyRule"); 
			 * examples.modifyRule(smartConnect);
			 * 
			 * System.out.println("cancelRule"); 
			 * examples.cancelRule(smartConnect);
			 * 
			 * System.out.println("Rule Details"); 
			 * examples.ruleDetails(smartConnect);
			 * 
			 * System.out.println("Rule List"); 
			 * examples.ruleList(smartConnect);
			 * 
			 * System.out.println("Historic candle Data");
			 * examples.getCandleData(smartConnect);
			 *
			 * System.out.println("logout"); 
			 * examples.logout(smartConnect);
			 */

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}

	}

	private static Set<TokenID> getTokens() {
		// find out the required token from
		// https://margincalculator.angelbroking.com/OpenAPI_File/files/OpenAPIScripMaster.json
		Set<TokenID> tokenSet = new HashSet<>();
		tokenSet.add(new TokenID(ExchangeType.NSE_CM, "26009")); // NIFTY BANK
		tokenSet.add(new TokenID(ExchangeType.NSE_CM, "1594")); // NSE Infosys
		tokenSet.add(new TokenID(ExchangeType.NCX_FO, "GUARGUM5")); // GUAREX (NCDEX)
		return tokenSet;
	}
}
