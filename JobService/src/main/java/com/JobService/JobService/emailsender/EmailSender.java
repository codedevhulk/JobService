package com.JobService.JobService.emailsender;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailSender {
	  private final JavaMailSender mailSender;

	    public EmailSender(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    }

	    public void sendAcceptedMail(String email) {
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	 message.setTo(email);
	         message.setSubject("Your application is accepted");
	         message.setText("Congrats! Your application for this job is accepted..! ");
	         mailSender.send(message);
	    	
	    }

		public void sendRejectedMail(String email) {
			SimpleMailMessage message = new SimpleMailMessage();
	    	 message.setTo(email);
	         message.setSubject("Your application is Rejected");
	         message.setText("Regret! ");
	         mailSender.send(message);
		}
}
