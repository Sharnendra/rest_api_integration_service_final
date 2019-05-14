package com.service.integration.config;

import java.util.ArrayList;
import java.util.List;

public class MethodAuthorization {

	private String Identifier;
	private List<String> rolesallowed=new ArrayList<String>();
	
	public String getIdentifier() {
		return Identifier;
	}
	public void setIdentifier(String identifier) {
		Identifier = identifier;
	}
	public List<String> getRolesallowed() {
		return rolesallowed;
	}
	public void setRolesallowed(List<String> rolesallowed) {
		this.rolesallowed = rolesallowed;
	}
	
	@Override
	public String toString() {
		return "MethodAuthorization [Identifier=" + Identifier + ", rolesallowed=" + rolesallowed + "]";
	}
}
