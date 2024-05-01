package com.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static void SendEmail(String to, String name, String password) {

		String msg = "Hello Dear " + name + ",\n \nYour Password : " + password;
		String subject = "Forgot Password";
		String from = "Forgot Password <no-reply@gmail.com>";

		// variable gmail host
		String host = "smtp.gmail.com";

		// get system properties
		Properties properties = System.getProperties();

		// setting important information to properties object
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step-1 get session
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("avinaykumar1904@gmail.com", "cvjsyrpwctemzojk");
			}
		});
		//session.setDebug(true);

		// step-2 compose msg
		MimeMessage m = new MimeMessage(session);
		try {
			// from email
			m.setFrom(from);

			// add reciepnt
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// add subject
			m.setSubject(subject);
			m.setText(msg);

			// step-3 send messgae using transport class
			Transport.send(m);
			

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
