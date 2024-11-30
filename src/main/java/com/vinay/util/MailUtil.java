package com.vinay.util;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailUtil {
	
	static String  recipientEmail = "vinayborate11@gmail.com";
	static String emailBody = "<h1>Order Confirmation</h1><p>Thank you for your purchase!</p>";
 

	private static final String EMAIL_FROM = "vijayborate56@gmail.com";
	    private static final String APP_PASSWORD = "lflo tuui kntn ihob";

	    public static void main(String[] args) throws Exception {
	        // Example usage:
	       
	        sendEmail(recipientEmail, emailBody);
	    }

	    // New function to send email
	    public static void sendEmail(String EMAIL_TO, String messageBody) throws MessagingException {
	        try {
		    	Message message = new MimeMessage(getEmailSession());
		        message.setFrom(new InternetAddress(EMAIL_FROM));
		        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO));
		        message.setSubject("Your Order Details");

		        // Set the content of the email as HTML
		        message.setContent(messageBody, "text/html");

		        // Send the email
		        Transport.send(message);
	        }
	        	catch(MessagingException e)
	        {
	        		System.out.print("ERRORRRRRRRRRR");
	        		e.printStackTrace();
	        }
	    }

	    // Helper method to configure email session
	    private static Session getEmailSession() {
	        return Session.getInstance(getGmailProperties(), new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
	            }
	        });
	    }

	    // Helper method to define email properties
	    private static Properties getGmailProperties() {
	        Properties prop = new Properties();
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true");
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	        return prop;
	    }
}
