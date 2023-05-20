package com.JobService.JobService.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobDetails {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String jobTitle;
	String jobDescription;
	String location;
	String jobType;
	String experience;
	String salary;
	String qualification;
	String skillSet;
    int vacancies;
    int recruiterId;
    String companyName;
	
	
}
