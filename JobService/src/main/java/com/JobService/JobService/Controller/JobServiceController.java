package com.JobService.JobService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobService.JobService.Entity.JobDetails;
import com.JobService.JobService.Model.JobApplicationRequest;
import com.JobService.JobService.Model.JobDetailsRequest;
import com.JobService.JobService.Model.JobDetailsResponse;
import com.JobService.JobService.ResponseTemplateVO.JobApplication;
import com.JobService.JobService.ResponseTemplateVO.JobSeekerDetails;
import com.JobService.JobService.Service.JobService;
import com.JobService.JobService.emailsender.EmailSender;
import com.JobService.JobService.response.ApplicationStatus;

@RestController
@CrossOrigin
@RequestMapping("/jobservice")
public class JobServiceController {
	
	
	
	@Autowired
	JobService jobService;
	
	@Autowired
	EmailSender emailSender;
	
	
	
	// Post a job
	
	@PostMapping("/recruiter/jobpost")
	public ResponseEntity<String> jobPost(@RequestBody JobDetailsRequest jobDetailsRequest){
		
		jobService.jobPost(jobDetailsRequest);
		
		
		return new ResponseEntity<>("Job has been posted succesfully",HttpStatus.OK);
	}
	
	
	
	// To get a specific Job
	
	@GetMapping("/job/{id}")
	public ResponseEntity<JobDetails> getJobById(@PathVariable int id){
		
		
		
		
		JobDetails jobDetails=jobService.getJobById(id);
		return new ResponseEntity<>(jobDetails,HttpStatus.OK);
		
	}
	
	
	
	// View All Jobs
	
	@GetMapping("/alljobs")
	public List<JobDetails> allJobs(){
		
		List<JobDetails> jobDetails=jobService.allJobs();
		return jobDetails;
		
	}
	
	//  Update a job
	
	@PutMapping("/recruiter/updatejobpost/{id}")
	public ResponseEntity<String> updateJobPost(@PathVariable("id") int id,@RequestBody JobDetailsRequest updatedJobDetailsRequest){
		
		jobService.updateJobPost(id,updatedJobDetailsRequest);
		
		return new ResponseEntity<>("Job details have been updated succesfully",HttpStatus.OK);
		
		
		
	}
	
	// Delete a Job
	
	@DeleteMapping("/recruiter/deletejobpost/{id}")
	public ResponseEntity<String> deleteJobPost(@PathVariable("id") int id){
		
		jobService.deleteJobPost(id);
		return new ResponseEntity<>("Job details with Job ID:"+id+"Has been deleted succesfully",HttpStatus.OK);
		
	}
	
	
	

	// Apply for a job
	@PostMapping("/jobseeker/apply/")
	public ResponseEntity<JobApplication> apply(@RequestBody JobApplicationRequest jobApplicationRequest){
		JobApplication jobApplication=jobService.apply(jobApplicationRequest);
		
		return new ResponseEntity<>(jobApplication,HttpStatus.OK);
	}
	
	
	
	// To Get all the applications of all the applicants for all the jobs
	
	@GetMapping("/applications")
	public List<JobApplication> getAllApplications(){
		return jobService.getAllApplications();
	}
	
	
	
	// To get all the applications who applied for the jobs posted a recruiter
	@GetMapping("/recruiter/applications/{id}")
	public List<JobApplication> getJobApplicationsPostedByRecruiter(@PathVariable long id){
		return jobService.getJobApplicationsByRecruiter(id);
	}
	
	
	
	
	// To get the past applications of a Jobseeker
	
	@GetMapping("/jobseeker/applications/{id}")
	public List<JobApplication> getJobApplicationsOfJobSeeker(@PathVariable int id){
		return jobService.getJobApplicationsOfJobSeeker(id);
	}
	
	
	// To Accept the application 
	
	@PutMapping("/recruiter/application/accept/{id}")
	public ApplicationStatus acceptApplicationByApplicationId(@PathVariable int id){
	jobService.acceptApplicationByApplicationId(id);
	ApplicationStatus status=new ApplicationStatus();
	status.setMessage("Job application has been Accepted for Application ID: "+id);
	return status;
	
	
	}
	
	
	// To Reject the application
	
	@PutMapping("/recruiter/application/reject/{id}")
	public ApplicationStatus rejectApplicationByApplicationId(@PathVariable int id){
	jobService.rejectApplicationByApplicationId(id);
	
	ApplicationStatus status=new ApplicationStatus();
	status.setMessage("Job application has been rejected for Application ID: "+id);
	return status;
	}
	

}
