package com.bank.util;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {
	
	private static final String FROM_MAIL = "harikrishnahh403@gmail.com";
	private static final String APP_PASSWORD = "rgwb fphd ucks xlab";
	
	public static void sendEmail(String email,String subject,String body){
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM_MAIL,APP_PASSWORD);
			}
		});
		
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_MAIL));
			//message.setRecipients(, null);
			
			
			
			
		} catch (MessagingException e) {
			 
			e.printStackTrace();
		}
	}

}
