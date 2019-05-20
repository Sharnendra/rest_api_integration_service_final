package com.service.integration.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.integration.modal.JwtUser;
import com.service.integration.rbac.Secured;
import com.service.integration.security.JwtGenerator;

@RestController

public class TokenController {

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/token")
    public String generate(@RequestBody final JwtUser jwtUser) {

        return jwtGenerator.generate(jwtUser);

    }
    
    @PostMapping("/details")
    @Secured(indentify="GET_TOKEN_DETAILS_TEST")
    public String tokendetails(HttpServletRequest httpServletRequest)
    {
    	String header = httpServletRequest.getHeader("Authorization");
		return header;
    }
}
