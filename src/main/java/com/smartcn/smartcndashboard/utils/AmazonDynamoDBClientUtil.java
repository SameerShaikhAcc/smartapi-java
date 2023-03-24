package com.smartcn.smartcndashboard.utils;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class AmazonDynamoDBClientUtil {
    static AmazonDynamoDB dbclient;
    public static DynamoDBMapper getMapper() {


        dbclient  = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(dbclient);

        return mapper;
    }


}
