package com.JobService.JobService.external;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name="RECRUITERSERVICE/recruiter")
public interface RecruiterService {

	@GetMapping("/getbyid/{id}")
	public RecruiterDetails getRecruiterById(@PathVariable long l);
	
}
