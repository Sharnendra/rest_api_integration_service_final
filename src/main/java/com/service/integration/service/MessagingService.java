package com.service.integration.service;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.integration.config.ConfigMessenger;

@Service
public class MessagingService {
	
	@Autowired
	private ConfigMessenger smsConfig;
	
	public String sendSMSOnPhone(String phonenumber) {
		try {
			// Send data
			return "http://api.textlocal.in/send/?" 
			+ "apikey=" + URLEncoder.encode(smsConfig.getApiKey(), "UTF-8") 
			+ "&numbers=" + URLEncoder.encode("static value", "UTF-8") 
			+ "&message=" + URLEncoder.encode("static value", "UTF-8");
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
	
	

}
