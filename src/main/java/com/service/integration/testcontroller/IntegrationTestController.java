package com.service.integration.testcontroller;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.service.integration.service.JSONValidationService;

@RestController
public class IntegrationTestController {
	
	@Autowired
	private JSONValidationService jsonValidationService;
	
	@GetMapping("/validateJson")
	public String data() throws ProcessingException, IOException, AddressException, MessagingException{
		File schemaFile = ResourceUtils.getFile("classpath:input-schema/schema.json");
	    String jsonFile = "{\r\n" + 
	    		"    \"id\": 1,\r\n" + 
	    		"    \"name\": \"A green door\",\r\n" + 
	    		"    \"price\": 12.50,\r\n" + 
	    		"    \"tags\": [\"home\", \"green\"]\r\n" + 
	    		"}";
	    	
	    if (jsonValidationService.isJsonValid(schemaFile, jsonFile)){
	    	System.out.println("Valid! ");
	    	return "Valid!";
	    }else{
	    	System.out.println("NOT valid! ");
	    	return "NOT valid!";
	    }
    }
	
	@GetMapping("/validateJson2")
	public  @ResponseBody ResponseEntity<?> data2() throws ProcessingException, IOException, AddressException, MessagingException{
		File schemaFile = ResourceUtils.getFile("classpath:input-schema/data.json");
	   	    	
	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response=null;
		try{
		response=restTemplate.exchange("http://localhost:9001/service-instances/get",
				HttpMethod.GET, getHeaders(),String.class);
		}catch (Exception ex)
		{
			System.out.println(ex);
		}
		
		System.err.println(response.getBody());
		
		if (jsonValidationService.isJsonValid(schemaFile, response.getBody())){
	    	System.out.println("Valid! ");
	    }else{
	    	System.out.println("NOT valid! ");
	    }
        return response;
    }
	
	private static org.springframework.http.HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new org.springframework.http.HttpEntity<>(headers);
	}
}
