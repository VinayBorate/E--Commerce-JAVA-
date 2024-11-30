package com.vinay.service;

import jakarta.mail.MessagingException;

public interface MailService {
	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
	public void sendEmail(String destionationemail, String messageBody) throws MessagingException;
}
