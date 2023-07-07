package com.JobService.JobService.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



@FeignClient(name="JOBSEEKERSERVICE/jobseeker")
@CircuitBreaker(name="external",fallbackMethod = "fallback")
public interface JobseekerService {

	@GetMapping("/getbyid/{id}")
	public JobSeekerDetails getJobSeekerById(@PathVariable long id);
	

	default void fallback(Exception e) {
		throw new com.JobService.JobService.Exception.CustomException("Job seeker service is unavailable!","UNAVAILABLE",500);
	}
	
}
