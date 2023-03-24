package com.smartcn.smartcndashboard.utils;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.JSONObject;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

public class AmazonSES {
	
//	static final String FROM = "digital@motilaloswal.com";
	static final String FROM = "vishakha.thokal@acc.ltd";
	static final String FROMNAME = "smart contract";
	static final String CONFIGSET = "contract-note-set";

	static final String TEXTBODY = "This email was sent through Amazon SES " + "using the AWS SDK for Java.";
	public String sendMailWithHeader(String emailId, String subject, String body,
			JSONObject metadataJson) {

		try {

			Session session = Session.getDefaultInstance(new Properties());

			// Create a new MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Add subject, from and to lines.
			message.setSubject(subject, "UTF-8");
			message.setFrom(new InternetAddress(FROM, FROMNAME));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(emailId));
			// message.addRecipients(RecipientType.TO,
			// InternetAddress.parse("aarti56proglobemedia@gmail.com,desaimj25@gmail.com"));
			String bcc = metadataJson.optString("bcc"); 
			if (!(bcc.isEmpty())) {
				message.setRecipients(RecipientType.BCC, InternetAddress.parse(bcc));
			}
			String cc = metadataJson.optString("cc");
			if (!(cc.isEmpty())) {
				message.setRecipients(RecipientType.CC, InternetAddress.parse(cc));
			}
			message.setHeader("metajson", metadataJson.toString());

			// Create a multipart/alternative child container.
			MimeMultipart msg_body = new MimeMultipart("alternative");

			// Create a wrapper for the HTML and text parts.
			MimeBodyPart wrap = new MimeBodyPart();

			// Define the HTML part.
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(body, "text/html; charset=UTF-8");

			// Add the text and HTML parts to the child container.
			// msg_body.addBodyPart(textPart);
			msg_body.addBodyPart(htmlPart);

			// Add the child container to the wrapper object.
			wrap.setContent(msg_body);

			// Create a multipart/mixed parent container.
			MimeMultipart msg = new MimeMultipart("mixed");

			// Add the parent container to the message.
			message.setContent(msg);

			// Add the multipart/alternative part to the message.
			msg.addBodyPart(wrap);

			

			AmazonSimpleEmailService sesClient = AmazonSimpleEmailServiceClientBuilder.standard()
					.withRegion(Regions.AP_SOUTH_1).build();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			message.writeTo(outputStream);
			RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

			SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage)
					.withConfigurationSetName(CONFIGSET);

			sesClient.sendRawEmail(rawEmailRequest);

			System.out.println("Email sent!");

			sesClient.shutdown();
			return "successful";

		} catch (Exception ex) {

			long startDateMilli = (new Date()).getTime(); //
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			dateFormatter.setTimeZone(TimeZone.getTimeZone("IST"));
			String activityDate = dateFormatter.format(startDateMilli);
			String partycode = metadataJson.optString("partycode");
			metadataJson.put("activityDate", activityDate);

			metadataJson.put("boReason", ex.getMessage());
			String emailStatus = "failed";

			metadataJson.put("emailStatus", emailStatus);
			final AmazonSQS sqsClient = AmazonSQSClientBuilder.defaultClient();
			String sqsQueueUrl = null;

			if (metadataJson.optString("").equals("")) {
				sqsQueueUrl = sqsClient.getQueueUrl(".fifo").getQueueUrl();
			} 

			final SendMessageRequest sendMessageRequest = new SendMessageRequest(sqsQueueUrl, metadataJson.toString());
			sendMessageRequest.setMessageGroupId(metadataJson.optString(""));
			final SendMessageResult sendMessageResult = sqsClient.sendMessage(sendMessageRequest);
			final String sequenceNumber = sendMessageResult.getSequenceNumber();
			final String messageId = sendMessageResult.getMessageId();
			System.out.println(
					"SendMessage succeed with messageId " + messageId + ", sequence number " + sequenceNumber + "\n");

			sqsClient.shutdown();
			System.out.println("The email was not sent. Error message: " + ex.getMessage());
			return ex.getMessage();
		}
	}
	
	 public String sendMail(String emailId, String subject, String body ) {

	    try {

	      Session session = Session.getDefaultInstance(new Properties());

	      // Create a new MimeMessage object.
	      MimeMessage message = new MimeMessage(session);

	      // Add subject, from and to lines.
	      message.setSubject(subject, "UTF-8");
	      message.setFrom(new InternetAddress(FROM, FROMNAME));

	      // message.setFrom(new InternetAddress(FROM));
	      message.setRecipients(RecipientType.TO, InternetAddress.parse(emailId));

	      // Create a multipart/alternative child container.
	      MimeMultipart msg_body = new MimeMultipart("alternative");

	      // Create a wrapper for the HTML and text parts.
	      MimeBodyPart wrap = new MimeBodyPart();

	      // Define the HTML part.
	      MimeBodyPart htmlPart = new MimeBodyPart();
	      htmlPart.setContent(body, "text/html; charset=UTF-8");

	      // Add the text and HTML parts to the child container.
	      // msg_body.addBodyPart(textPart);
	      msg_body.addBodyPart(htmlPart);

	      // Add the child container to the wrapper object.
	      wrap.setContent(msg_body);

	      // Create a multipart/mixed parent container.
	      MimeMultipart msg = new MimeMultipart("mixed");

	      // Add the parent container to the message.
	      message.setContent(msg);

	      // Add the multipart/alternative part to the message.
	      msg.addBodyPart(wrap);

	      AmazonSimpleEmailService sesClient =
	          AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
	      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	      message.writeTo(outputStream);
	      RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

	      SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage).withConfigurationSetName(CONFIGSET);

	      sesClient.sendRawEmail(rawEmailRequest);

	      System.out.println("Email sent!");

	      sesClient.shutdown();
	      return "successful";

	    } catch (AmazonSimpleEmailServiceException ex) {

	      System.out.println("The email was not sent. Error message: " + ex.getErrorCode());
	      System.out.println("The email was not sent. Error message: " + ex.getMessage());
	      return ex.getMessage();
	    } catch (Exception ex) {

	      System.out.println("The email was not sent. Error message: " + ex.getMessage());
	      return ex.getMessage();
	    }
	  }


}
