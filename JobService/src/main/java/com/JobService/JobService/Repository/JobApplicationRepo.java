package com.JobService.JobService.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobService.JobService.ResponseTemplateVO.JobApplication;

@Repository
public interface JobApplicationRepo extends JpaRepository<JobApplication,Integer> {

	
	@Transactional
	List<JobApplication> findByRecruiterId(long id);
	@Transactional
	List<JobApplication> findByJobSeekerId(long id);
	
}
