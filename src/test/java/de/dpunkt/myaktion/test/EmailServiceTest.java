package de.dpunkt.myaktion.test;

import javax.mail.MessagingException;

import org.junit.Test;

import de.dpunkt.myaktion.util.EmailService;

public class EmailServiceTest {
	
	@Test
	public void testSendEmail() {
		EmailService emailService = new EmailService();
		String msg = "Dies ist eine Testnacchricht";
		try {
			emailService.sendEmail("stefdev@mailo.com", "stefan.timpen@gmail.com", "Spendenziel erreicht", msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
