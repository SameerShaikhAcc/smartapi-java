package com.smartcn.smartcndashboard.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.core.io.ClassPathResource;

import com.amazonaws.services.cloudfront.CloudFrontUrlSigner;
import com.amazonaws.services.cloudfront.util.SignerUtils.Protocol;

public class SignedUrlOneDay {

	public String getSignedUrl(String s3Key, String distributionDomainName)
	{
		try{
			Security.addProvider(new BouncyCastleProvider());
			//the DNS name of your CloudFront distribution, or a registered alias
			//String distributionDomainName = "d3mfryn5dycr06.cloudfront.net";
			//the private key you created in the AWS Management Console
			//ClassLoader classLoader = SignedUrl.class.getClassLoader();
			//File cloudFrontPrivateKeyFile = new
			//File(classLoader.getResource("private_key.der").getPath());
			ClassPathResource classPathResource = new ClassPathResource("/private_key.der");
			InputStream inputStream = classPathResource.getInputStream();
			File cloudFrontPrivateKeyFile = File.createTempFile("private_key", ".der");
			try {
				FileUtils.copyInputStreamToFile(inputStream, cloudFrontPrivateKeyFile);
				}
			finally {
				IOUtils.closeQuietly(inputStream);
			} // the unique ID assigned to your CloudFront key pair in the console
			String cloudFrontKeyPairId = "K3UT5J7IAF5B8K"; // url expire time
			// Date expirationDate = new Date(System.currentTimeMillis() + 300 * 1000);
			Date expirationDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
			String signedUrl = CloudFrontUrlSigner.getSignedURLWithCannedPolicy(Protocol.https, distributionDomainName,
							cloudFrontPrivateKeyFile, s3Key, // the resource path to our content
							cloudFrontKeyPairId, expirationDate);
			return signedUrl;
		} catch (InvalidKeySpecException | IOException e)
		{
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
			return null;	
		}
	}
}