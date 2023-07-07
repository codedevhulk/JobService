package com.JobService.JobService.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.JobService.JobService.Entity.JobDetails;
import com.JobService.JobService.Exception.CustomException;
import com.JobService.JobService.Model.JobDetailsRequest;
import com.JobService.JobService.Repository.JobApplicationRepo;
import com.JobService.JobService.Repository.JobDetailsRepo;
import com.JobService.JobService.ResponseTemplateVO.JobApplication;
import com.JobService.JobService.emailsender.EmailSender;
import com.JobService.JobService.external.JobseekerService;
import com.JobService.JobService.external.RecruiterService;


@ExtendWith(MockitoExtension.class)
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
	JobService jobService = new JobService();
	
	
	private List<JobDetails> getMockJobs() {

		ArrayList<JobDetails> jobDetails = new ArrayList<>();

		JobDetails job1 = JobDetails.builder().id(1).jobTitle("Java full stack developer")
				.jobDescription("Expected to have knowledge on both frontend and backend technologies")
				.location("Hyderabad").jobType(null).experience(null).salary(null).qualification(null).vacancies(0)
				.recruiterId(0).companyName(null).skillSet("Java, Springboot").build();

		JobDetails job2 = JobDetails.builder().id(2).jobTitle("Python full stack developer")
				.jobDescription("Expected to have knowledge on both frontend and backend technologies")
				.location("Hyderabad").jobType(null).experience(null).salary(null).qualification(null).vacancies(0)
				.recruiterId(0).companyName(null).skillSet("Python, Django").build();
		jobDetails.add(job1);
		jobDetails.add(job2);
		
		return jobDetails;

	}
	
	
	
	List<JobDetails> mockJobs=getMockJobs();
	
	JobDetailsRequest jobDetailsRequest = getMockJobPostRequest();
	JobDetails jobDetailss = getMockJobDetails();
	
	
	
	
	@Test
	void test_When_deleteJobPost_success() {
		
		
		// Mocking the internal calls
		
		
		Mockito.doNothing().when(jobDetailsRepo).deleteById(anyInt());
		
		
		// Actual calls
		
		jobService.deleteJobPost(1);
		//Verification
		
		
		verify(jobDetailsRepo,times(1)).deleteById(anyInt());
		//Assertions
		
		
	}
	
	
	
	
	
	
	
	@DisplayName("When Update Job - success scenario")
	@Test
	void test_When_updateJobPostSuccess() {
		
		
		// Mocking the internal call
		
		when(jobDetailsRepo.findById(anyInt())).thenReturn(Optional.of(jobDetailss));
		
		//Actual call
	
	String updateStatus=	jobService.updateJobPost(1, getMockJobPostRequest());
		//Verification
		
		//Assertion
		Assertions.assertEquals("Job details have been updated succesfully",updateStatus);
		
	}
	
	
	
	
	
	
    @DisplayName("Find all Jobs - Success scenario")
	@Test
	void test_When_findAll_jobs_success() {
		
		// Mocking the internal calls
		
		Mockito.when(jobDetailsRepo.findAll()).thenReturn(mockJobs);
		
		//Actual call
		
		List<JobDetails> jobDetails=jobService.allJobs();
		
		
		
		// Verification
		
		
		verify(jobDetailsRepo,times(1)).findAll();
		
		
		// Assertion
		
		
		assertEquals(1,jobDetails.get(0).getId());
		assertThat(jobDetails).isNotEmpty();
		
		
		
	}

	@DisplayName("Post Job - Success Scenario")
	@Test
	void test_When_postJob_isSuccess() {

		JobDetailsRequest jobDetailsRequest = getMockJobPostRequest();
		JobDetails jobDetailss = getMockJobDetails();
		// Mocking the internal calls
		jobDetailss.setLocation("Hyderabad");
		jobDetailss.setId(1);

		Mockito.when(jobDetailsRepo.save(Mockito.any(JobDetails.class))).thenReturn(jobDetailss);

		// Actual call
		JobDetails jobDetails = jobService.jobPost(jobDetailsRequest);

		// System.out.println(jobId);

		// Verification

		verify(jobDetailsRepo, times(1)).save(any());
		// Assertion

		// assertEquals(1, jobDetails.getId());
		assertEquals("Java full stack developer", jobDetails.getJobTitle());
		assertEquals("Hyderabad", jobDetailss.getLocation());

	}

	@DisplayName("Jobs list empty - Scenario")
	@Test
	void test_When_jobPosts_not_available() {

		Mockito.when(jobDetailsRepo.findAll()).thenReturn(Collections.emptyList());

		CustomException exception = assertThrows(CustomException.class, () -> jobService.allJobs());

		assertEquals("No Job details found", exception.getMessage());

	}

	private JobDetails getMockJobDetails() {

		return JobDetails.builder().id(1).jobTitle("Java full stack developer")
				.jobDescription("Expected to have knowledge on both frontend and backend technologies")
				.location("Hyderabad").jobType(null).experience(null).salary(null).qualification(null).vacancies(0)
				.recruiterId(0).companyName(null).build();

	}

	private JobDetailsRequest getMockJobPostRequest() {
		return JobDetailsRequest.builder().jobTitle("Java full stack developer")
				.jobDescription("Expected to have knowledge on both frontend and backend technologies").location("Hyderabad")
				.jobType("FulTime").experience("Experience").salary("10LPA").qualification("Btech").vacancies(10).recruiterId(1)
				.companyName("Persistent").build();
	}
	
	
	

	

}
