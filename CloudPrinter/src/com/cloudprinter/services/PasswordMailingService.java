package com.cloudprinter.services;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PasswordMailingService {
	private static final Logger log = Logger.getLogger(PasswordMailingService.class.getName());
	public boolean sendMail(String emailId, String loginId, String password)
			throws MessagingException, UnsupportedEncodingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "Mail From Cloud Printer Hmritm....\n"
				+ "Your Login Id & Password is :\n" + loginId + "\n" + password;

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("cloudprinthmr@gmail.com",
				"Cloud Printer Hmritm"));

		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
		msg.setSubject("Login Information");
		msg.setText(msgBody);
		Transport.send(msg);
		log.info("Mail Sent to User...");
		return true;

	}
}
