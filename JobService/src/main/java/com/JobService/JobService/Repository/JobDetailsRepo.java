package com.JobService.JobService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobService.JobService.Entity.JobDetails;

@Repository
public interface JobDetailsRepo extends JpaRepository<JobDetails, Integer>{

	
	
}
