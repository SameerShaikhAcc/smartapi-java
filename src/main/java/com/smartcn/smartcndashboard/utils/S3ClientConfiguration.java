package com.smartcn.smartcndashboard.utils;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3ClientConfiguration {

    public static AmazonS3 getClient() {
        ClientConfiguration config = new ClientConfiguration();
        config.setConnectionTimeout(900000);
        config.setSocketTimeout(900000);
        return AmazonS3ClientBuilder.standard().withClientConfiguration(config).build();
    }
}
