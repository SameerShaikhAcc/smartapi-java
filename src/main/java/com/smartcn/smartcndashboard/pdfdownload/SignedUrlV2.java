package com.smartcn.smartcndashboard.pdfdownload;

import java.io.File;
import java.io.IOException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.amazonaws.services.cloudfront.CloudFrontUrlSigner;
import com.amazonaws.services.cloudfront.util.SignerUtils.Protocol;

public class SignedUrlV2 {
	public String getSignedUrl(String s3Key, String distributionDomainName) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			// the DNS name of your CloudFront distribution, or a registered alias
			File cloudFrontPrivateKeyFile = new File("private_key.der");
			// the unique ID assigned to your CloudFront key pair in the console
			String cloudFrontKeyPairId = "K1EUFQ82X2LKBI";

			// url expire time
			Date expirationDate = new Date(System.currentTimeMillis() + 300 * 1000);

			String signedUrl = CloudFrontUrlSigner.getSignedURLWithCannedPolicy(Protocol.https, distributionDomainName,
					cloudFrontPrivateKeyFile, s3Key, // the resource path to our content
					cloudFrontKeyPairId, expirationDate);


			return signedUrl;
		} catch (InvalidKeySpecException | IOException e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

}
