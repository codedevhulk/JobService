package com.JobService.JobService.ResponseTemplateVO;

import com.JobService.JobService.Model.JobDetailsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplication {

	
	private JobSeekerDetails jobSeekerDetails;
	private JobDetailsResponse jobDetails;
	
	
}
