package com.JobService.JobService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobService.JobService.ResponseTemplateVO.JobApplication;

@Repository
public interface JobApplicationRepo extends JpaRepository<JobApplication,Integer> {

	
	
	List<JobApplication> findByRecruiterId(int id);
	List<JobApplication> findByJobSeekerId(long id);
	
}
