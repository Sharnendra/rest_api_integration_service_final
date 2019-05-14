package com.service.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigMessenger {

	@Value("${smsconfig-apikey}")
	private String apiKey;
	@Value(" Bhai Tough h bnanana")
	private String message;
	
	
	@Override
	public String toString() {
		return "SmsConfig [apiKey=" + apiKey + ", message=" + message +"]";
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
