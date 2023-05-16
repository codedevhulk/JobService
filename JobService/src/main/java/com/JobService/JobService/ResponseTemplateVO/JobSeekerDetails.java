package com.JobService.JobService.ResponseTemplateVO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JobSeekerDetails {
	
	String firstName;
	String lastName;
	String mobileNumber;
	String email;
	String password;
	String qualification;
	String skillSet;
	String experience;
	String summary;
	String address;
}
