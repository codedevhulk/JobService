package com.JobService.JobService.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDetailsResponse {
	
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
}
