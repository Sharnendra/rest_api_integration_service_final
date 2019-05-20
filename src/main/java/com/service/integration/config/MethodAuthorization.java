package com.service.integration.config;

import java.util.ArrayList;
import java.util.List;

public class MethodAuthorization {

	private String serviceId;
	private List<String> rolesAllowed=new ArrayList<String>();
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public List<String> getRolesAllowed() {
		return rolesAllowed;
	}
	public void setRolesAllowed(List<String> rolesAllowed) {
		this.rolesAllowed = rolesAllowed;
	}
	@Override
	public String toString() {
		return "MethodAuthorization [serviceId=" + serviceId + ", rolesAllowed=" + rolesAllowed + "]";
	}
	
}
