package com.service.integration.testcontroller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.service.integration.service.MessagingService;
@RestController
public class MessagingServiceController {
	
	@Autowired
	private MessagingService messageService;

	@PostMapping("/sendSMS")
    public ResponseEntity<String> sendSMSOnPhone(@RequestHeader(value="phone") String phonenumber) {
		SimpleClientHttpRequestFactory clientHttpReq = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.cognizant.com", 6050));
		clientHttpReq.setProxy(proxy);
		
		RestTemplate restTemplate = new RestTemplate(clientHttpReq);
		ResponseEntity<String> response=null;
		
		try{
		response=restTemplate.exchange(messageService.sendSMSOnPhone(phonenumber),
				HttpMethod.GET, getHeaders(),String.class);
		}catch (Exception ex)
		{
			System.out.println(ex);
		}
        return response;
    }
	
	private static org.springframework.http.HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new org.springframework.http.HttpEntity<>(headers);
	}

}
