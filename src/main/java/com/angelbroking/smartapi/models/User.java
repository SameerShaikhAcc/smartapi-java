package com.angelbroking.smartapi.models;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * A wrapper for user and session details.
 */

@Data
public class User {

    @SerializedName("name")
    public String userName;

    @SerializedName("clientcode")
    public String userId;

    @SerializedName("mobileno")
    public String mobileNo;

    @SerializedName("broker")
    public String brokerName;

    @SerializedName("email")
    public String email;

    @SerializedName("lastlogintime")
    public Date lastLoginTime;

    @SerializedName("accessToken")
    public String accessToken;

    @SerializedName("refreshToken")
    public String refreshToken;

    public String[] products;
    public String[] exchanges;

    @SerializedName("feedToken")
    public String feedToken;

    /**
     * Parses user details response from server.
     *
     * @param response is the json response from server.
     * @return User is the parsed data.
     * @throws JSONException is thrown when there is error while parsing response.
     */
    public User parseResponse(JSONObject response) throws JSONException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement jsonElement, Type type,
                                    JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return format.parse(jsonElement.getAsString());
                } catch (ParseException e) {
                    return null;
                }
            }
        });
        Gson gson = gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        User user = gson.fromJson(String.valueOf(response.get("data")), User.class);
        user = parseArray(user, response.getJSONObject("data"));
        return user;
    }

    /**
     * Parses array details of product, exchange and order_type from json response.
     *
     * @param response is the json response from server.
     * @param user     is the object to which data is copied to from json response.
     * @return User is the pojo of parsed data.
     */
    public User parseArray(User user, JSONObject response) throws JSONException {
        JSONArray productArray = response.getJSONArray("products");
        user.products = new String[productArray.length()];
        for (int i = 0; i < productArray.length(); i++) {
            user.products[i] = productArray.getString(i);
        }

        JSONArray exchangesArray = response.getJSONArray("exchanges");
        user.exchanges = new String[exchangesArray.length()];
        for (int j = 0; j < exchangesArray.length(); j++) {
            user.exchanges[j] = exchangesArray.getString(j);
        }

//		JSONArray orderTypeArray = response.getJSONArray("order_types");
//		user.orderTypes = new String[orderTypeArray.length()];
//		for (int k = 0; k < orderTypeArray.length(); k++) {
//			user.orderTypes[k] = orderTypeArray.getString(k);
//		}

        return user;
    }


    @Override
    public String toString() {
        return "User [userName=" + userName + ", userId=" + userId + ", mobileNo=" + mobileNo + ", brokerName="
                + brokerName + ", email=" + email + ", lastLoginTime=" + lastLoginTime + ", accessToken=" + accessToken
                + ", refreshToken=" + refreshToken + ", products=" + Arrays.toString(products) + ", exchanges="
                + Arrays.toString(exchanges) + ", feedToken=" + feedToken + "]";
    }

}
