package com.service.integration.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.integration.config.ConfigMethodSecurity;
import com.service.integration.constant.GenericConstants;
import com.service.integration.rbac.Secured;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Aspect
@Component
public class ServiceAspect {
	
	@Autowired
	private ConfigMethodSecurity configMethodSecurity;

	//@After(value = "execution(* com.service.security.jwtsecurity.aspect.EmployeeService.*(..)) and args(name,empId,ls) && @annotation(com.service.security.jwtsecurity.constraint.Roles)")
	//@Before(value = "execution(* *.*.*.*.*.*(..)) and args(httpServletRequest) && @annotation(com.service.integration.rbac.Secured)")
	@Before(value = "args(httpServletRequest) && @annotation(com.service.integration.rbac.Secured)")
	public void afterAdvice(JoinPoint joinPoint,HttpServletRequest httpServletRequest) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(Secured.class)) 
        {
        	Annotation annotation = method.getAnnotation(Secured.class);
            Secured secured = (Secured) annotation;
            
        	if(configMethodSecurity.getRoleBasedSecurity().containsKey(secured.indentify().trim()))
    		{
        		String header = httpServletRequest.getHeader(GenericConstants.AUTHORIZATION);
        		Claims body = Jwts.parser()
                        .setSigningKey("#21@365")
                        .parseClaimsJws(header)
                        .getBody();
        		
        		List<String> identifier=configMethodSecurity.getRoleBasedSecurity().get(secured.indentify().trim());
    			if(identifier.stream().filter(tag->tag.equalsIgnoreCase((String)body.get("role"))).count()<=0)
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
