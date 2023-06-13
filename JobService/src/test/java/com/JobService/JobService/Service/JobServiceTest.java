package com.JobService.JobService.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.JobService.JobService.Entity.JobDetails;
import com.JobService.JobService.Exception.CustomException;
import com.JobService.JobService.Model.JobDetailsRequest;
import com.JobService.JobService.Repository.JobApplicationRepo;
import com.JobService.JobService.Repository.JobDetailsRepo;
import com.JobService.JobService.emailsender.EmailSender;
import com.JobService.JobService.external.JobseekerService;
import com.JobService.JobService.external.RecruiterService;
import com.google.common.base.Optional;
@SpringBootTest
class JobServiceTest {

	
	
	
	
	
	

	@Mock
    EmailSender emailSender;
	
	@Mock
	JobDetailsRepo jobDetailsRepo;
	
	@Mock
	RestTemplate restTemplate;
	
	
	
	@Mock
	JobseekerService jobSeekerService;
	
	
	@Mock
	RecruiterService recruiterService;
	
	@Mock
	JobApplicationRepo jobApplicationRepo;
	
	@InjectMocks
	JobService jobService=new JobService();
	
	    @DisplayName("Post Job - Success Scenario")
	    @Test
	    void test_When_postJob_isSuccess() {

		 JobDetailsRequest jobDetailsRequest = getMockJobPostRequest();
		 JobDetails jobDetails = getMockJobDetails();
            //Mocking the internal calls
	        
		 
		    Mockito.when(jobDetailsRepo.save(Mockito.any(JobDetails.class))).thenReturn(jobDetails);
           
		 
		 
		   //Actual call
	        int jobId =  jobService.jobPost(jobDetailsRequest);

	        
	        System.out.println(jobId);
	        
	        //Verification
	        
	        verify(jobDetailsRepo, times(1))
	                .save(any());
           //Assertion
	        
	        assertEquals(1, jobDetails.getId());
	    }
	 
	 
	 
	 
	 
	    @DisplayName("Jobs list empty - Scenario")
	    @Test
	    void test_When_jobPosts_not_available() {
	    	
	    	Mockito.when(jobDetailsRepo.findAll()).thenReturn(Collections.emptyList());
	    	
	    	
	    	CustomException exception=assertThrows(CustomException.class,()->jobService.allJobs());
	    	
	    	
	    	
	    	
	    	assertEquals("No Job details found",exception.getMessage());
	    }
	    
	    
	 
	 
	 

	    private JobDetails getMockJobDetails() {

	        return JobDetails.builder()
	        		.id(1)
	        		.jobTitle("Java full stack developer")
	                .jobDescription("Expected to have knowledge on both frontend and backend technologies")
	                .location(null)
	                .jobType(null)
	                .experience(null)
	                .salary(null)
	                .qualification(null)
	                .vacancies(0)
	                .recruiterId(0)
	                .companyName(null)
	                .build();

	    }
	 
	 
	 
	 private JobDetailsRequest getMockJobPostRequest() {
	        return JobDetailsRequest.builder()
	                .jobTitle("Java full stack developer")
	                .jobDescription("Expected to have knowledge on both frontend and backend technologies")
	                .location(null)
	                .jobType(null)
	                .experience(null)
	                .salary(null)
	                .qualification(null)
	                .vacancies(0)
	                .recruiterId(0)
	                .companyName(null)
	                .build();
	    }
	

}
