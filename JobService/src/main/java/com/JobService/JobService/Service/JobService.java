package com.JobService.JobService.Service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobService.JobService.Entity.JobDetails;
import com.JobService.JobService.Model.JobDetailsRequest;
import com.JobService.JobService.Repository.JobDetailsRepo;

@Service
public class JobService {

	
	@Autowired
	JobDetailsRepo jobDetailsRepo;
	
	
	public void jobPost(JobDetailsRequest jobDetailsRequest) {
		// TODO Auto-generated method stub
		
		JobDetails jobDetails=JobDetails.builder()
				.jobTitle(jobDetailsRequest.getJobTitle())
				.jobDescription(jobDetailsRequest.getJobDescription())
				.experience(jobDetailsRequest.getExperience())
				.jobType(jobDetailsRequest.getJobType())
				.location(jobDetailsRequest.getLocation())
				.qualification(jobDetailsRequest.getQualification())
				.salary(jobDetailsRequest.getSalary())
				.skillSet(jobDetailsRequest.getSkillSet())
				.vacancies(jobDetailsRequest.getVacancies())
				.build();
		
		jobDetailsRepo.save(jobDetails);
		
				
		
		
		
	}


	public List<JobDetails> allJobs() {
		// TODO Auto-generated method stub
		return jobDetailsRepo.findAll();
	}


	public void updateJobPost(int id, JobDetailsRequest updatedJobDetailsRequest) {
		// TODO Auto-generated method stub
		
		JobDetails existingJobDetails=jobDetailsRepo.findById(id).get();
		
		
		if(Objects.nonNull(updatedJobDetailsRequest.getExperience())) {
			
			existingJobDetails.setExperience(updatedJobDetailsRequest.getExperience());
		}
        if(Objects.nonNull(updatedJobDetailsRequest.getJobDescription())) {
			
			existingJobDetails.setExperience(updatedJobDetailsRequest.getJobDescription());
		}
        if(Objects.nonNull(updatedJobDetailsRequest.getJobTitle())) {
	
	        existingJobDetails.setJobTitle(updatedJobDetailsRequest.getJobTitle());
             }
         if(Objects.nonNull(updatedJobDetailsRequest.getJobType())) {
	
	        existingJobDetails.setJobType(updatedJobDetailsRequest.getJobType());
           }
         if(Objects.nonNull(updatedJobDetailsRequest.getLocation())) {
	
	         existingJobDetails.setLocation(updatedJobDetailsRequest.getLocation());
              }
         if(Objects.nonNull(updatedJobDetailsRequest.getQualification())) {
	
	       existingJobDetails.setQualification(updatedJobDetailsRequest.getQualification());
            }
         if(Objects.nonNull(updatedJobDetailsRequest.getSalary())) {
	
	        existingJobDetails.setSalary(updatedJobDetailsRequest.getSalary());
           }
         if(Objects.nonNull(updatedJobDetailsRequest.getSkillSet())) {
	
        	existingJobDetails.setSkillSet(updatedJobDetailsRequest.getSkillSet());
           }
         if(Objects.nonNull(updatedJobDetailsRequest.getVacancies())) {
        		
         	existingJobDetails.setVacancies(updatedJobDetailsRequest.getVacancies());
            }
         
         
         jobDetailsRepo.save(existingJobDetails);
         

		
		
	}


	public void deleteJobPost(int id) {
		// TODO Auto-generated method stub
		jobDetailsRepo.deleteById(id);
		
		
	}

	
	
	
	
	
	
}
