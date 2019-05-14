package com.service.integration.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.integration.constraint.Secured;
import com.service.integration.modal.JwtUser;
import com.service.integration.security.JwtGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

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
    @Secured(value={"ADMIN"},indentify="GET_TOKEN_DETAILS")
    public String tokendetails(HttpServletRequest httpServletRequest)
    {
    	String header = httpServletRequest.getHeader("Authorization");
    	Claims body = Jwts.parser()
                .setSigningKey("#21@365")
                .parseClaimsJws(header)
                .getBody();
		return header+" data "+body;
    }
}
