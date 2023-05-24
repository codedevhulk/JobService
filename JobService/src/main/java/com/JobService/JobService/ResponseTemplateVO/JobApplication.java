package com.JobService.JobService.ResponseTemplateVO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.JobService.JobService.Entity.JobDetails;
import com.JobService.JobService.Model.JobDetailsResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class JobApplication {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    long jobSeekerId;
    String firstName;
	String lastName;
	String mobileNumber;
	String email;
	
	String jobSeekerQualification;
	String jobSeekerSkillSet;
	String jobSeekerExperience;
	String jobSeekerSummary;
	String jobSeekerAddress;
	int jobId;
	long recruiterId;
	String jobTitle;
	String jobDescription;
	String location;
	String jobType;
	String requiredExperience;
	String salary;
	String requiredQualification;
	String requiredskillSet;
	String applicationStatus;
   
	
	
}
