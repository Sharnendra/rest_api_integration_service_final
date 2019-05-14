package com.service.integration.controller;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.service.integration.config.CommonConfigurations;
import com.service.integration.config.ConfigService;
import com.service.integration.service.JSONValidationService;

@RestController
@RequestMapping("/unsecure")
public class IntegrationController {
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private JSONValidationService jsonValidationService;
	
	
	@RequestMapping("/header")
	public @ResponseBody CommonConfigurations checkHeader(@RequestHeader(value="service_type",required=true) String service_type) {
		CommonConfigurations commonConfigurations=null;
		if(configService.getServicemapper().containsKey(service_type))
		{
			commonConfigurations=configService.getServicemapper().get(service_type);
		}
		return commonConfigurations;
	}
	
	@PostMapping("/get-service")
	public  @ResponseBody ResponseEntity<?> data2(@RequestHeader(value="service_type",required=true) String service_type, @RequestBody String payload) throws ProcessingException, IOException, AddressException, MessagingException{
		
		ResponseEntity<String> response=null;
		CommonConfigurations commonConfigurations=null;
		if(configService.getServicemapper().containsKey(service_type))
		{
			commonConfigurations=configService.getServicemapper().get(service_type);
			File schemaFile = ResourceUtils.getFile("classpath:input-schema/"+commonConfigurations.getInputSchemaFile());
			response=new ResponseEntity<String>(payload, HttpStatus.OK);
			if (jsonValidationService.isJsonValid(schemaFile, payload)){
		    	System.out.println("Valid! ");
		    }else{
		    	System.out.println("NOT valid! ");
		    }
		}
		else
		{
			response=new ResponseEntity<String>("Service Not Found", HttpStatus.NOT_FOUND);
		}
        return response;
    }

}
