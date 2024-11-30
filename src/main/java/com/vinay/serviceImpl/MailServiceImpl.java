package com.vinay.serviceImpl;

import com.vinay.service.MailService;
import com.vinay.util.MailUtil;

import jakarta.mail.MessagingException;

public class MailServiceImpl  implements MailService{

	
	/* -------CLOSE  DB   CONNECTION -----------   */
	
	
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendEmail(String destionationemail, String messageBody) throws MessagingException {
		// TODO Auto-generated method stub
		
	}
}
