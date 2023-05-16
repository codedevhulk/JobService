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
import com.JobService.JobService.Service.JobService;

@RestController
@CrossOrigin
@RequestMapping("/jobservice")
public class JobServiceController {
	
	
	
	@Autowired
	JobService jobService;
	
	
	
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
	public ResponseEntity<String> apply(@RequestBody JobApplicationRequest jobApplicationRequest){
		jobService.apply(jobApplicationRequest);
		
		return new ResponseEntity<>("You have succesfully applied for the Job",HttpStatus.OK);
	}
	

}
