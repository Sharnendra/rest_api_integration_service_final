package com.service.integration.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.integration.config.ConfigMethodSecurity;
import com.service.integration.constraint.Secured;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Aspect
@Component
public class ServiceAspect {
	
	@Autowired
	private ConfigMethodSecurity configMethodSecurity;

	//@After(value = "execution(* com.service.security.jwtsecurity.aspect.EmployeeService.*(..)) and args(name,empId,ls) && @annotation(com.service.security.jwtsecurity.constraint.Roles)")
	@After(value = "execution(* com.service.integration.controller.*.*(..)) and args(httpServletRequest) && @annotation(com.service.integration.constraint.Secured)")
	public void afterAdvice(JoinPoint joinPoint,HttpServletRequest httpServletRequest) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(Secured.class)) 
        {
        	Annotation annotation = method.getAnnotation(Secured.class);
            Secured roles = (Secured) annotation;
            
        	if(configMethodSecurity.getRoleBasedSecurity().containsKey(roles.indentify().trim()))
    		{
        		Boolean authorize= false;
        		String header = httpServletRequest.getHeader("Authorization");
        		Claims body = Jwts.parser()
                        .setSigningKey("#21@365")
                        .parseClaimsJws(header)
                        .getBody();
        		
        		List<String> identifier=configMethodSecurity.getRoleBasedSecurity().get(roles.indentify().trim());
        		for (String tag : identifier) 
        		{ 
        			if(tag.equalsIgnoreCase((String)body.get("role")))
        			{
        				authorize=true;
        			} 
        		}
        		if(!authorize)
        		{
        			throw new RuntimeException("USER IS NOT AUTHORIZED TO ACCESS THIS METHOD!!");
        		}
    		}
        	else
        	{
        		throw new RuntimeException("ILLEGAL USE OF ANNOTATION SECURED AS NO ROLES OR AUTHORIZATION OF ROLES IS BEEN SPECIFIED!!");
        	}
        }
	}
	
}
