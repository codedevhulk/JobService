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

	    public void sendAcceptedMail(String email,String applicantsName, String recruiterName, String jobTitle) {
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	 message.setTo(email);
	         message.setSubject("Congratulations!");
	         message.setText("Dear "+applicantsName+" ,\r\n"
	         		+ "\r\n"
	         		+ "We are delighted to inform you that your application for the "+jobTitle+" position has been accepted. Congratulations! We believe that your skills and experience will be a valuable asset to our team.\r\n"
	         		+ "\r\n"
	         		+ "Your starting date is 21/06/2023.\r\n"
	         		+ "\r\n"
	         		+ "To ensure a smooth onboarding process, please gather the necessary employment verification documents and bring them with you on your first day. Our HR department will provide you with the required tax and payroll forms to complete.\r\n"
	         		+ "\r\n"
	         		+ "We look forward to welcoming you and working together towards our shared success. If you have any questions, please feel free to reach out.\r\n"
	         		+ "\r\n"
	         		+ "Best regards,\r\n"
	         		+ "\r\n"
	         		+ ""+recruiterName+" \r\n"
	         		
	         		);
	         mailSender.send(message);
	    	
	    }

		public void sendRejectedMail(String email,String applicantsName, String recruiterName, String jobTitle) {
			SimpleMailMessage message = new SimpleMailMessage();
	    	 message.setTo(email);
	         message.setSubject("Job Application Status Update - Thank you for Your Interest");
	         message.setText("Dear "+applicantsName+", \r\n"
	         		+ "\r\n"
	         		+ "Thank you for taking the time to apply for the "+jobTitle+" position at PersistHire. We appreciate your interest in our organization and the effort you put into your application. After careful consideration and review, we regret to inform you that we have chosen to pursue other candidates whose qualifications more closely match our requirements.\r\n"
	         		+ "\r\n"
	         		+ "Please know that this decision was not made lightly, as we recognize the time and effort you invested in the application process. We genuinely appreciate your interest in joining our team, and we encourage you to explore future opportunities as they arise.\r\n"
	         		+ "\r\n"
	         		+ "While your application was not successful for this particular position, we encourage you to continue pursuing your career goals. Your skills and experience may be an excellent fit for other roles in different departments or future openings at PersistHire or other organizations.\r\n"
	         		+ "\r\n"
	         		+ "We genuinely appreciate your interest and wish you the best of luck in your job search. Thank you once again for considering us as a potential employer.\r\n"
	         		+ "\r\n"
	         		+ "Sincerely,\r\n"
	         		+ "\r\n"
	         		+ ""+recruiterName+" \r\n"
	         		);
	         mailSender.send(message);
		}

		public void sendApplicationNotificationToRecruiter(String email,String applicantsName, String jobTitle,String recruiterName) {
			// TODO Auto-generated method stub
			SimpleMailMessage message = new SimpleMailMessage();
	    	 message.setTo(email);
	         message.setSubject("New Job Application Received - "+jobTitle);
	         message.setText("Dear "+recruiterName+" ,\r\n"
	         		+ "\r\n"
	         		+ "This email is to notify you that a new job application has been received for the "+jobTitle+" position that you posted on PersistHire. We wanted to keep you informed about the progress of your job posting.\r\n"
	         		+ "\r\n"
	         		+ "The applicant, "+applicantsName+", has submitted their application for the position. Their qualifications and experience appear to align with the requirements outlined in the job description.\r\n"
	         		+ "\r\n"
	         		
	         		+ "If you have any questions or require further information, please do not hesitate to reach out to us.\r\n"
	         		+ "\r\n"
	         		+ "Thank you for your ongoing support in the hiring process.\r\n"
	         		+ "\r\n"
	         		+ "Best regards,\r\n"
	         		
	         		+ "PersistHire");
	         mailSender.send(message);
			
		}
}
