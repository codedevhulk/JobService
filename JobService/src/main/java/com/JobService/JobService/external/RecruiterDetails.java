package com.JobService.JobService.external;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecruiterDetails {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	long recruiterId;
	String userName;
	String firstName;
	String lastName;
	String email;
	String mobileNumber;
	String password;
	String address;
	
}
