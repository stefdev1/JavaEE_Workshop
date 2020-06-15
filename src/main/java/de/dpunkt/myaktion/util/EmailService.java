package de.dpunkt.myaktion.util;

import java.util.Properties;

import javax.enterprise.context.Dependent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Dependent
public class EmailService {
	
	public void sendEmail(String from, String to, String subject, String content) throws MessagingException {
		Properties properties =  new Properties();
		properties.put("mail.smtp.host", "mail.mailo.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setContent(content, "text/plain");
		message.setSubject(subject);
		//TODO: set username and password
		String username = null;
		String password = null;
		Transport.send(message, username, password);
	}

}
