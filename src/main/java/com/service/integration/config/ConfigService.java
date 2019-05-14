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
@ConfigurationProperties(prefix="service")
@EnableConfigurationProperties
@Component
public class ConfigService implements InitializingBean{

	private String serviceTypeHeader;
	private List<ServiceBasedConfigurations> servicemap = new ArrayList<ServiceBasedConfigurations>();
	private Map<String,CommonConfigurations> servicemapper = new HashMap<String,CommonConfigurations>();
	
	public Map<String, CommonConfigurations> getServicemapper() {
		return servicemapper;
	}

	public void setServicemapper(Map<String, CommonConfigurations> servicemapper) {
		this.servicemapper = servicemapper;
	}

	public String getServiceTypeHeader() {
		return serviceTypeHeader;
	}

	public void setServiceTypeHeader(String serviceTypeHeader) {
		this.serviceTypeHeader = serviceTypeHeader;
	}

	public List<ServiceBasedConfigurations> getServicemap() {
		return servicemap;
	}

	public void setServicemap(List<ServiceBasedConfigurations> servicemap) {
		this.servicemap = servicemap;
	}
	
	@Override
	public String toString() {
		return "ConfigService [serviceTypeHeader=" + serviceTypeHeader + ", servicemap=" + servicemap + "]";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.err.println(toString());
		
		setServicemapper(servicemap.stream()
						.collect(
						   Collectors.toMap(ServiceBasedConfigurations::getServicename,ServiceBasedConfigurations::getCommonConfigurations)
						));
	}
}
