	package com.JobService.JobService.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.JobService.JobService.Entity.JobDetails;
import com.JobService.JobService.Exception.CustomException;
import com.JobService.JobService.Model.JobApplicationRequest;
import com.JobService.JobService.Model.JobDetailsRequest;
import com.JobService.JobService.Model.JobDetailsResponse;
import com.JobService.JobService.Repository.JobApplicationRepo;
//import com.JobService.JobService.Repository.JobApplicationRepo;
import com.JobService.JobService.Repository.JobDetailsRepo;
import com.JobService.JobService.ResponseTemplateVO.JobApplication;
import com.JobService.JobService.ResponseTemplateVO.JobSeekerDetails;
import com.JobService.JobService.emailsender.EmailSender;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobService {
@Autowired
EmailSender emailSender;
	
	@Autowired
	JobDetailsRepo jobDetailsRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Autowired
	JobApplicationRepo jobApplicationRepo;
	
	
//	@Autowired
//	JobApplication jobApplication;
	
	
//	List<JobApplication> jobApplications=new ArrayList<>();
	
	
	
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
				.recruiterId(jobDetailsRequest.getRecruiterId())
				.companyName(jobDetailsRequest.getCompanyName())
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


	public JobApplication apply(JobApplicationRequest jobApplicationRequest) {
		// TODO Auto-generated method stub
		
		
		
		JobSeekerDetails jobSeekerDetails=restTemplate.getForObject("http://JOBSEEKERSERVICE/jobseeker/getbyid/"+jobApplicationRequest.getJobSeekerId(),JobSeekerDetails.class);
		JobDetails jobDetails= jobDetailsRepo.findById(jobApplicationRequest.getJobId()).get();
	
		
//		if(Objects.isNull(jobSeekerDetails)) {
//			throw new CustomException("Job seeker doesn't found with ID "+jobApplicationRequest.getJobSeekerId()+"not found","NOT_FOUND",404);
//		}
//		
//		if(Objects.isNull(jobDetails)) {
//			
//			throw new CustomException("Job details not found with ID "+jobApplicationRequest.getJobId()+"not found","NOT_FOUND",404);
//			
//			
//		}
		
//		
//		JobApplication jobApplication=JobApplication.builder()
//				.jobSeekerId(jobSeekerDetails.getId)
//		
		
		
//		jobApplication.setJobDetails(jobDetails);
//		jobApplication.setJobSeekerDetails(jobSeekerDetails);
		
		
		
		
		
		JobApplication jobApplication=JobApplication.builder()
				.jobSeekerId(jobSeekerDetails.getJobSeekerId())
				.email(jobSeekerDetails.getEmail())
				.firstName(jobSeekerDetails.getFirstName())
				.lastName(jobSeekerDetails.getLastName())
				.jobSeekerAddress(jobSeekerDetails.getAddress())
				.jobSeekerQualification(jobSeekerDetails.getQualification())
				.jobSeekerSkillSet(jobSeekerDetails.getSkillSet())
				.jobId(jobDetails.getId())
				.jobDescription(jobDetails.getJobDescription())
				.jobTitle(jobDetails.getJobTitle())
				.requiredExperience(jobDetails.getQualification())
				.requiredExperience(jobDetails.getExperience())
				.jobSeekerExperience(jobSeekerDetails.getExperience())
				.recruiterId(jobDetails.getRecruiterId())
				.applicationStatus("Pending")
				.build();	
		
		
		jobApplicationRepo.save(jobApplication);
		
		
		return jobApplication;
		
		
		
		
	}


	public JobDetails getJobById(int id) {
		
		
		
		
		
		
		return jobDetailsRepo.findById(id).orElseThrow(()->new CustomException("Job details with ID: "+id+" Not found","NOT_FOUND",404));

		
		
		//System.out.println(jobDetailsById.toString());
		// TODO Auto-generated method stub
//	if(Objects.isNull(jobDetailsById)) {
//			
//		
//		throw new CustomException("Job details with ID: "+id+" Not found","NOT_FOUND",404);}
//		else {
//			return jobDetailsById;
//		}
		}


	public List<JobApplication> getAllApplications() {
		// TODO Auto-generated method stub
		return jobApplicationRepo.findAll();
	}


	public List<JobApplication> getJobApplicationsByRecruiter(long id) {
		// TODO Auto-generated method stub
		List<JobApplication> jobApplications=jobApplicationRepo.findByRecruiterId(id);
		if(jobApplications.isEmpty()) {
			throw new CustomException("Recruiter with ID: "+id+" not found","NOT_FOUND",404);
		}
		else {
			return jobApplications;
		}
		
	}


	public List<JobApplication> getJobApplicationsOfJobSeeker(int id) {
		// TODO Auto-generated method stub
		
		
		
		List<JobApplication> jobApplicationsOfJobSeeker=jobApplicationRepo.findByJobSeekerId(id);
		if(jobApplicationsOfJobSeeker.isEmpty()) {
			throw new CustomException("Job seeker with ID: "+id+" not found","NOT_FOUND",404);
		}
		else {
			return jobApplicationsOfJobSeeker;
		}
	}


	public void acceptApplicationByApplicationId(int id) {
		// TODO Auto-generated method stub
		JobApplication jobApplication=jobApplicationRepo.findById(id).get();
		log.info(jobApplication.toString());
		jobApplication.setApplicationStatus("Accepted");
		jobApplicationRepo.save(jobApplication);
		
		emailSender.sendAcceptedMail(jobApplication.getEmail());
	}


	public void rejectApplicationByApplicationId(int id) {
		// TODO Auto-generated method stub
JobApplication jobApplication=jobApplicationRepo.findById(id).get();
		
		jobApplication.setApplicationStatus("Rejected");
		jobApplicationRepo.save(jobApplication);
	}




	
	
	
	
	
	
}
