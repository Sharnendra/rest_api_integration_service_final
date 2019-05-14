package com.service.integration.config;

public class ServiceBasedConfigurations{
	
	private String servicename;
	private CommonConfigurations commonConfigurations;
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public CommonConfigurations getCommonConfigurations() {
		return commonConfigurations;
	}
	public void setCommonConfigurations(CommonConfigurations commonConfigurations) {
		this.commonConfigurations = commonConfigurations;
	}
	@Override
	public String toString() {
		return "ServiceBasedConfig [servicename=" + servicename + ", commonConfigurations=" + commonConfigurations + "]";
	}
}
