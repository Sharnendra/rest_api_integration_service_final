package com.service.integration.testcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.service.integration.service.MailingService;

@Controller
public class MailingServiceController {

	@Autowired
	private MailingService mailingService;
	
	@PostMapping("/sendMail")
    public String sendMail() {
		//mailingService.sendMail("sharnendradey@gmail.com");
		mailingService.sendMail2();
        return "Success";
    }
}
