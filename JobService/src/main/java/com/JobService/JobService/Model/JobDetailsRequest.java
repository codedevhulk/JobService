package com.JobService.JobService.Model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDetailsRequest {

	
	String jobTitle;
	String jobDescription;
	String location;
	String jobType;
	String experience;
	String salary;
	String qualification;
	String skillSet;
    int vacancies;
    
	
	
	
}
