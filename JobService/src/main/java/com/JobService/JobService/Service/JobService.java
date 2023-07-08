package com.JobService.JobService.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
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
//<<<<<<< HEAD
import com.JobService.JobService.external.JobseekerService;
import com.JobService.JobService.external.RecruiterDetails;
import com.JobService.JobService.external.RecruiterService;
import com.google.common.net.HttpHeaders;
//=======
import com.JobService.JobService.emailsender.EmailSender;
//>>>>>>> b226de438bdebd1c827c4282c87d0f1acc635cea

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Service
//@Slf4j
@Log4j2
public class JobService {

	@Autowired
	EmailSender emailSender;

	@Autowired
	JobDetailsRepo jobDetailsRepo;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	JobseekerService jobSeekerService;

	@Autowired
	RecruiterService recruiterService;

	@Autowired
	JobApplicationRepo jobApplicationRepo;

//	@Autowired
//	JobApplication jobApplication;

//	List<JobApplication> jobApplications=new ArrayList<>();

	public JobDetails jobPost(JobDetailsRequest jobDetailsRequest) {
		// TODO Auto-generated method stub

//		if(Objects.isNull(jobDetailsRequest.getRecruiterId())) {
//			throw new CustomException("Recruiter ID is mandatory to post a job, please provide the recruiter ID","BAD_REQUEST",400);
//		}

		// RecruiterDetails
		// recruiterDetails=restTemplate.getForObject("http://RECRUITERSERVICE/recruiter/getbyid/"+jobDetailsRequest.getRecruiterId(),
		// RecruiterDetails.class);
		// recruiterService.getRecruiterById(jobDetailsRequest.getRecruiterId());
		log.info("Checking whether recruiter Id present in the job post details or not as it's mandatory");

		RecruiterDetails recruiterDetails = recruiterService.getRecruiterById(jobDetailsRequest.getRecruiterId());
		// System.out.println(recruiterDetailss.toString());
//			if(Objects.isNull(recruiterDetails.getEmail())) {
//				throw new CustomException("Email details not updated for recruiter "+recruiterDetails.getUserName()+" ,Please update the email to proceed for posting a job","EMAIL_DOESN'T EXIST",404);
//				
//			}

		JobDetails jobDetails = JobDetails.builder().jobTitle(jobDetailsRequest.getJobTitle())
				.jobDescription(jobDetailsRequest.getJobDescription()).experience(jobDetailsRequest.getExperience())
				.jobType(jobDetailsRequest.getJobType()).location(jobDetailsRequest.getLocation())
				.qualification(jobDetailsRequest.getQualification()).salary(jobDetailsRequest.getSalary())
				.skillSet(jobDetailsRequest.getSkillSet()).vacancies(jobDetailsRequest.getVacancies())
				.recruiterId(jobDetailsRequest.getRecruiterId()).companyName(jobDetailsRequest.getCompanyName())
				.build();

		jobDetailsRepo.save(jobDetails);
		log.info("Saving job post object to the database");

		return jobDetails;

	}

	public List<JobDetails> allJobs() {
		// TODO Auto-generated method stub
		List<JobDetails> jobDetails = jobDetailsRepo.findAll();
		if (jobDetails.isEmpty()) {
			throw new CustomException("No Job details found", "NOT_FOUND", 404);
		}

		log.info("fetching the job details from the database");
		return jobDetails;
	}

	public String updateJobPost(int id, JobDetailsRequest updatedJobDetailsRequest) {
		// TODO Auto-generated method stub
		log.info("Checking if job exists with ID");
		JobDetails existingJobDetails = jobDetailsRepo.findById(id)
				.orElseThrow(() -> new CustomException("Job details not found with ID: " + id, "NOT_FOUND", 404));

		System.out.println(existingJobDetails.toString());

		if (Objects.nonNull(updatedJobDetailsRequest.getExperience())) {

			existingJobDetails.setExperience(updatedJobDetailsRequest.getExperience());
		}
		if (Objects.nonNull(updatedJobDetailsRequest.getJobDescription())) {

			existingJobDetails.setJobDescription(updatedJobDetailsRequest.getJobDescription());
		}
		if (Objects.nonNull(updatedJobDetailsRequest.getJobTitle())) {

			existingJobDetails.setJobTitle(updatedJobDetailsRequest.getJobTitle());
		}
		if (Objects.nonNull(updatedJobDetailsRequest.getJobType())) {

			existingJobDetails.setJobType(updatedJobDetailsRequest.getJobType());
		}
		if (Objects.nonNull(updatedJobDetailsRequest.getLocation())) {

			existingJobDetails.setLocation(updatedJobDetailsRequest.getLocation());
		}
		if (Objects.nonNull(updatedJobDetailsRequest.getQualification())) {

			existingJobDetails.setQualification(updatedJobDetailsRequest.getQualification());
		}
		if (Objects.nonNull(updatedJobDetailsRequest.getSalary())) {

			existingJobDetails.setSalary(updatedJobDetailsRequest.getSalary());
		}
		if (Objects.nonNull(updatedJobDetailsRequest.getSkillSet())) {

			existingJobDetails.setSkillSet(updatedJobDetailsRequest.getSkillSet());
		}
		if (Objects.nonNull(updatedJobDetailsRequest.getVacancies())) {

			existingJobDetails.setVacancies(updatedJobDetailsRequest.getVacancies());
		}
		if (Objects.nonNull(updatedJobDetailsRequest.getCompanyName())) {
			existingJobDetails.setCompanyName(updatedJobDetailsRequest.getCompanyName());
		}

		jobDetailsRepo.save(existingJobDetails);
		log.info("updated the job details");
		
		return "Job details have been updated succesfully";

	}
	
	

	public String deleteJobPost(int id) {
		// TODO Auto-generated method stub

		log.info("Checking whether job details present or not, proceeds to delete if found or throws an error");
		JobDetails jobDetails = jobDetailsRepo.findById(id)
				.orElseThrow(() -> new CustomException("Job details with ID: " + id + " Not found", "NOT_FOUND", 404));
		jobDetailsRepo.deleteById(id);
return "Job details have been removed with ID "+id;
	}
	

	public JobApplication apply(JobApplicationRequest jobApplicationRequest) {
		// TODO Auto-generated method stub

//		
//		com.JobService.JobService.external.JobSeekerDetails jobSeekerDetails=jobSeekerService.getJobSeekerById(jobApplicationRequest.getJobSeekerId());
//		if(Objects.isNull(jobSeekerDetails)) {
//			
//			throw new CustomException("Job seeker details not found","NOT_FOUND",404);
//			
//		}

		 JobSeekerDetails jobSeekerDetails=restTemplate.getForObject("http://JOBSEEKERSERVICE/jobseeker/getbyid/"+jobApplicationRequest.getJobSeekerId(),JobSeekerDetails.class);

		log.info("Fetching jobseeker details");
//		com.JobService.JobService.external.JobSeekerDetails jobSeekerDetails = jobSeekerService
//				.getJobSeekerById(jobApplicationRequest.getJobSeekerId());
		log.info("Fetching Job details");
		JobDetails jobDetails = jobDetailsRepo.findById(jobApplicationRequest.getJobId()).orElseThrow(
				() -> new CustomException("Job details with ID " + jobApplicationRequest.getJobId() + " Not found",
						"NOT_FOUND", 404));

//		
//	RecruiterDetails recruiterDetails=	recruiterService.getRecruiterById(jobDetails.getRecruiterId());
//		if(Objects.isNull(recruiterDetails.getEmail())) {
//			
//			throw new CustomException("Recruiter email does not exist, please update the recruiter profile","RECRUITER_EMAIL_NOT_FOUND",404);
//			
//		}

		// RecruiterDetails
		// recruiterDetails=restTemplate.getForObject("http://RECRUITERSERVICE/recruiter/getbyid/"+jobDetails.getRecruiterId(),
		// RecruiterDetails.class);

		log.info("Fetching recruiter details from recruiter service");

		RecruiterDetails recruiterDetails = recruiterService.getRecruiterById(jobDetails.getRecruiterId());

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

		JobApplication jobApplication = JobApplication.builder().jobSeekerId(jobSeekerDetails.getJobSeekerId())
				.email(jobSeekerDetails.getEmail()).firstName(jobSeekerDetails.getFirstName())
				.lastName(jobSeekerDetails.getLastName()).jobSeekerAddress(jobSeekerDetails.getAddress())
				.jobSeekerQualification(jobSeekerDetails.getQualification())
				.jobSeekerSkillSet(jobSeekerDetails.getSkillSet()).jobId(jobDetails.getId())
				.jobDescription(jobDetails.getJobDescription()).jobTitle(jobDetails.getJobTitle())
				.requiredExperience(jobDetails.getQualification()).requiredExperience(jobDetails.getExperience())
				.jobSeekerExperience(jobSeekerDetails.getExperience()).recruiterId(jobDetails.getRecruiterId())
				.applicationStatus("Pending").build();

		log.info("Creating the Job application object with details and saving it to the database");
		jobApplicationRepo.save(jobApplication);

		emailSender.sendApplicationNotificationToRecruiter(recruiterDetails.getEmail(), jobApplication.getFirstName(),
				jobApplication.getJobTitle(), recruiterDetails.getUserName());

		return jobApplication;

	}

	public JobDetails getJobById(int id) {

		log.info("Fetching job details, if not found throwing an error");

		return jobDetailsRepo.findById(id)
				.orElseThrow(() -> new CustomException("Job details with ID: " + id + " Not found", "NOT_FOUND", 404));

		// System.out.println(jobDetailsById.toString());
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

		log.info("Fetching all the job applications");

		List<JobApplication> jobApplications = jobApplicationRepo.findAll();
		if (jobApplications.isEmpty()) {
			throw new CustomException("Job Applications not found", "NOT_FOUND", 404);
		}
		return jobApplications;
	}

	public List<JobApplication> getJobApplicationsByRecruiter(long id) {
		// TODO Auto-generated method stub
		log.info("Finding the recruiter details with Id");

		RecruiterDetails recruiterDetails = recruiterService.getRecruiterById(id);
		// RecruiterDetails
		// recruiterDetails=restTemplate.getForObject("http://RECRUITERSERVICE/recruiter/getbyid/"+id,
		// RecruiterDetails.class);
		log.info("Finding the applications of a particular recruiter");
		List<JobApplication> jobApplications = jobApplicationRepo.findByRecruiterId(id);
		if (Objects.isNull(recruiterDetails)) {
			throw new CustomException("Recruiter with ID: " + id + " not found", "NOT_FOUND", 404);
		} else if (jobApplications.isEmpty()) {
			throw new CustomException("Job applications for recruiter: " + id + " not found", "NOT_FOUND", 404);
		} else {

			log.info("Returning the applications");
			return jobApplications;

		}

	}

	public List<JobApplication> getJobApplicationsOfJobSeeker(long id) {
		// TODO Auto-generated method stub

		log.info("Getting the job seeker details");
		 JobSeekerDetails jobSeekerDetails=restTemplate.getForObject("http://JOBSEEKERSERVICE/jobseeker/getbyid/"+id,JobSeekerDetails.class);
		//com.JobService.JobService.external.JobSeekerDetails jobSeekerDetails = jobSeekerService.getJobSeekerById(id);

		log.info("Getting the job applications of a job seeker");

		List<JobApplication> jobApplicationsOfJobSeeker = jobApplicationRepo.findByJobSeekerId(id);
		if (jobApplicationsOfJobSeeker.isEmpty()) {
			throw new CustomException("No Applications found for " + id + "", "NOT_FOUND", 404);
		} else {
			return jobApplicationsOfJobSeeker;
		}
	}

	public void acceptApplicationByApplicationId(int id) {
		// TODO Auto-generated method stub

		log.info("Getting the job application with ID,if not found throwing an error");
		JobApplication jobApplication = jobApplicationRepo.findById(id)
				.orElseThrow(() -> new CustomException("Job Application not found with ID: " + id, "NOT_FOUND", 404));
		// log.info(jobApplication.toString());

		log.info("accepting the application, changing the status to Accepted ");

		jobApplication.setApplicationStatus("Accepted");
		jobApplicationRepo.save(jobApplication);
		RecruiterDetails recruiterDetails = restTemplate.getForObject(
				"http://RECRUITERSERVICE/recruiter/getbyid/" + jobApplication.getRecruiterId(), RecruiterDetails.class);

		log.info("Sending acceptance e-mail to the Job seeker");

		emailSender.sendAcceptedMail(jobApplication.getEmail(), jobApplication.getFirstName(),
				recruiterDetails.getUserName(), jobApplication.getJobTitle());
//>>>>>>> b226de438bdebd1c827c4282c87d0f1acc635cea
	}

	public void rejectApplicationByApplicationId(int id) {
		// TODO Auto-generated method stub
		JobApplication jobApplication = jobApplicationRepo.findById(id)
				.orElseThrow(() -> new CustomException("Job Application not found with ID: " + id, "NOT_FOUND", 404));
		RecruiterDetails recruiterDetails = restTemplate.getForObject(
				"http://RECRUITERSERVICE/recruiter/getbyid/" + jobApplication.getRecruiterId(), RecruiterDetails.class);
		jobApplication.setApplicationStatus("Rejected");
		jobApplicationRepo.save(jobApplication);
		log.info("Sending rejection e-mail to the Job seeker");
		emailSender.sendRejectedMail(jobApplication.getEmail(), jobApplication.getFirstName(),
				recruiterDetails.getUserName(), jobApplication.getJobTitle());
	}

	public void deleteApplicationById(int id) {
		// TODO Auto-generated method stub

		JobApplication jobApplication = jobApplicationRepo.findById(id).orElseThrow(
				() -> new CustomException("Job application with ID: " + id + " Not found", "NOT_FOUND", 404));
		log.info("Deleting the job application");
		jobApplicationRepo.deleteById(id);
	}

	public List<JobDetails> getJobsOfRecruiter(int id) {
		// TODO Auto-generated method stub
		
		List<JobDetails> jobDetails=jobDetailsRepo.findAll();
		
		
		return jobDetails;
	}

}
