package com.JobService.JobService.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name="JOBSEEKERSERVICE/jobseeker")
public interface JobseekerService {

	@GetMapping("/getbyid/{id}")
	public JobSeekerDetails getJobSeekerById(@PathVariable long id);
	
	
	
}
