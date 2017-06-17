package com.quebic.auth.api.common.util.email;

import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.quebic.auth.api.common.util.email.template.EmailTemplate;
import com.quebic.common.constants.ConfigConstants;

@Configuration
public class EmailUtil {

	private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	
	@Value(ConfigConstants.EMAIL_USERNAME)
	private String EMAIL_USERNAME;

	@Value(ConfigConstants.EMAIL_PASSWORD)
	private String EMAIL_PASSWORD;

	public void send(final String subject,final EmailTemplate emailTemplate, final String recipientAddress) throws Exception {
		
		final String htmlContent = emailTemplate.getTextContent();
		
		CompletableFuture.supplyAsync(new Supplier<String>() {
		    @Override
		    public String get() {
		        
		    	logger.info("start sending email -> " + recipientAddress);
		    	
		    	// Sender's email ID needs to be mentioned
				String from = EMAIL_USERNAME;
				final String username = EMAIL_USERNAME;// change accordingly
				final String password = EMAIL_PASSWORD;// change accordingly

				// Assuming you are sending email through relay.jangosmtp.net
				String host = "pop.gmail.com";

				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", "25");

				// Get the Session object.
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

				try {
					// Create a default MimeMessage object.
					Message message = new MimeMessage(session);

					// Set From: header field of the header.
					message.setFrom(new InternetAddress(from));

					// Set To: header field of the header.
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));

					// Set Subject: header field
					message.setSubject(subject);
					
					message.setContent(
							htmlContent,
				             "text/html");

					// Send message
					Transport.send(message);

					logger.info("sent message successfully -> " + recipientAddress);

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		    	
		        return "sent";
		    }
		}).exceptionally(new Function<Throwable, String>() {

			@Override
			public String apply(Throwable t) {
				logger.error(t.getMessage());
				return null;
			}
		});
		
		

	}
	
	public void send(final String subject,final  String messageText,final String htmlContent,final String recipientAddress) {
		
		CompletableFuture.supplyAsync(new Supplier<String>() {
		    @Override
		    public String get() {
		        
		    	logger.info("start sending email -> " + recipientAddress);
		    	
		    	// Sender's email ID needs to be mentioned
				String from = EMAIL_USERNAME;
				final String username = EMAIL_USERNAME;// change accordingly
				final String password = EMAIL_PASSWORD;// change accordingly

				// Assuming you are sending email through relay.jangosmtp.net
				String host = "pop.gmail.com";

				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", "25");

				// Get the Session object.
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

				try {
					// Create a default MimeMessage object.
					Message message = new MimeMessage(session);

					// Set From: header field of the header.
					message.setFrom(new InternetAddress(from));

					// Set To: header field of the header.
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));

					// Set Subject: header field
					message.setSubject(subject);

					// Now set the actual message
					message.setText(messageText);
					
					message.setContent(
							htmlContent,
				             "text/html");

					// Send message
					Transport.send(message);

					logger.info("sent message successfully -> " + recipientAddress);

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		    	
		        return "sent";
		    }
		}).exceptionally(new Function<Throwable, String>() {

			@Override
			public String apply(Throwable t) {
				logger.error(t.getMessage());
				return null;
			}
		});
		
		

	}

}
