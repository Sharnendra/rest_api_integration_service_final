package com.service.integration.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import org.simplejavamail.email.EmailBuilder;
//import org.simplejavamail.mailer.Mailer;
//import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.stereotype.Service;

@Service
public class MailingService {
	
	private Session mailSession;
	
	private void setMailServerProperties()
    {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        
		
		 emailProperties.put("mail.smtp.user", "sharnendradey@gmail.com");
		 emailProperties.put("mail.smtp.host", "smtp.gmail.com");
		 emailProperties.put("mail.smtp.starttls.enable","true");
		 emailProperties.put("mail.smtp.auth", "true");
		 emailProperties.put("mail.smtp.debug", "true");
		 emailProperties.put("mail.smtp.socketFactory.port", "465");
		 emailProperties.put("mail.smtp.socketFactory.class",
		 "javax.net.ssl.SSLSocketFactory");
		 emailProperties.put("mail.smtp.socketFactory.fallback", "false");
		         
        mailSession = Session.getInstance(emailProperties,  new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("Authenticating");
                return new PasswordAuthentication("sharnendradey@gmail.com", "kakkarotssj@6");
            }

        });
		mailSession.setDebug(true);
    }
	
	private MimeMessage draftEmailMessage() throws AddressException, MessagingException
    {
        String[] toEmails = { "sharnendradey@gmail.com" };
        String emailSubject = "Test email subject";
        String emailBody = "This is an email sent by <b>//Test For Email</b>.";
        MimeMessage emailMessage = new MimeMessage(mailSession);
        /**
         * Set the mail recipients
         * */
        for (int i = 0; i < toEmails.length; i++)
        {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }
        emailMessage.setSubject(emailSubject);
        /**
         * If sending HTML mail
         * */
        emailMessage.setContent(emailBody, "text/html");
        /**
         * If sending only text mail
         * */
        //emailMessage.setText(emailBody);// for a text email
        return emailMessage;
    }
	
	public String sendMail(String emailId)
	{
		setMailServerProperties();
		/**
         * Sender's credentials
         * */
        String fromUser = "sharnendradey@gmail.com";
        String fromUserEmailPassword = "kakkarotssj@6";
 
        String emailHost = "smtp.gmail.com";
        
        try {
        	Transport transport = mailSession.getTransport("smtp");
			transport.connect(emailHost, fromUser, fromUserEmailPassword);
			/**
	         * Draft the message
	         * */
	        MimeMessage emailMessage = draftEmailMessage();
	        /**
	         * Send the mail
	         * */
	        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
	        transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Email sent successfully.";
	}
	
	
	public void sendMail2()
	{
		final String username = "Sharnendra.Dey@outlook.com";
        final String password = "Kakkarotssj06";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("proxySet","true");
        props.setProperty("socksProxyHost","proxy.cognizant.com");
        props.setProperty("socksProxyPort","6050");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
        	String emailHost = "smtp.office365.com";
        	Transport transport = session.getTransport("smtp");
			transport.connect(emailHost, username, password);
			
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Sharnendra.Dey@outlook.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("sharnendradey@gmail.com"));
            message.setSubject("Test");
            message.setText("HI");

            transport.sendMessage(message, message.getAllRecipients());
	        transport.close();

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
}
