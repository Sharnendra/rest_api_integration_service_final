package com.service.integration.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix="methodsecurity")
@EnableConfigurationProperties
@Component
public class ConfigMethodSecurity implements InitializingBean{

	private List<MethodAuthorization> securitymap = new ArrayList<MethodAuthorization>();
	private Map<String,List<String>> roleBasedSecurity = new HashMap<String,List<String>>();
	
	public List<MethodAuthorization> getSecuritymap() {
		return securitymap;
	}
	
	public void setSecuritymap(List<MethodAuthorization> securitymap) {
		this.securitymap = securitymap;
	}
	
	public Map<String, List<String>> getRoleBasedSecurity() {
		return roleBasedSecurity;
	}
	
	public void setRoleBasedSecurity(Map<String, List<String>> roleBasedSecurity) {
		this.roleBasedSecurity = roleBasedSecurity;
	}

	@Override
	public String toString() {
		return "ConfigMethodSecurity [securitymap=" + securitymap + ", roleBasedSecurity=" + roleBasedSecurity + "]";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.err.println(toString());
		
		setRoleBasedSecurity(securitymap.stream()
						.collect(
						   Collectors.toMap(MethodAuthorization::getIdentifier,MethodAuthorization::getRolesallowed)
						));
	}
}
