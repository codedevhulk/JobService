package com.JobService.external.decoder;

import java.io.IOException;

import com.JobService.JobService.Exception.CustomException;
import com.JobService.JobService.response.ErrorMessage;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class CustomErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String s, Response response) {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper=new ObjectMapper();
		log.info("::{}",response.request().url());
		log.info("::{}",response.request().headers());
		try {
			ErrorMessage errorMessage=objectMapper.readValue(response.body().asInputStream(), ErrorMessage.class);
			return new CustomException(errorMessage.getErrorMessage(),errorMessage.getErrorCode(),response.status());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CustomException("Internal Server Issue","Internal server Issue",500);
		}
	
		
	}

}
