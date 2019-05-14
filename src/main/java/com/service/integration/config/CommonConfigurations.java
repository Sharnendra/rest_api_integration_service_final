package com.service.integration.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonConfigurations {
	
	private String methodType;
	private String endpoint;
	private List<String> allowedQueryParams=new ArrayList<String>(); 
	private String inputSchemaFile; 
    private String outputSchemaFile;
	private Boolean inputValidationRequired;
    private Boolean outputValidationRequired;
    private String requiredHeaders[];
    private List<SecurityHeaders> securityHeaders=new ArrayList<SecurityHeaders>();
    
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public String getInputSchemaFile() {
		return inputSchemaFile;
	}
	public void setInputSchemaFile(String inputSchemaFile) {
		this.inputSchemaFile = inputSchemaFile;
	}
	public String getOutputSchemaFile() {
		return outputSchemaFile;
	}
	public void setOutputSchemaFile(String outputSchemaFile) {
		this.outputSchemaFile = outputSchemaFile;
	}
	public Boolean getInputValidationRequired() {
		return inputValidationRequired;
	}
	public void setInputValidationRequired(Boolean inputValidationRequired) {
		this.inputValidationRequired = inputValidationRequired;
	}
	public Boolean getOutputValidationRequired() {
		return outputValidationRequired;
	}
	public void setOutputValidationRequired(Boolean outputValidationRequired) {
		this.outputValidationRequired = outputValidationRequired;
	}
	public String[] getRequiredHeaders() {
		return requiredHeaders;
	}
	public void setRequiredHeaders(String[] requiredHeaders) {
		this.requiredHeaders = requiredHeaders;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public List<String> getAllowedQueryParams() {
		return allowedQueryParams;
	}
	public void setAllowedQueryParams(List<String> allowedQueryParams) {
		this.allowedQueryParams = allowedQueryParams;
	}
	public List<SecurityHeaders> getSecurityHeaders() {
		return securityHeaders;
	}
	public void setSecurityHeaders(List<SecurityHeaders> securityHeaders) {
		this.securityHeaders = securityHeaders;
	}
	@Override
	public String toString() {
		return "CommonConfigurations [methodType=" + methodType + ", endpoint=" + endpoint + ", allowedQueryParams="
				+ allowedQueryParams + ", inputSchemaFile=" + inputSchemaFile + ", outputSchemaFile=" + outputSchemaFile
				+ ", inputValidationRequired=" + inputValidationRequired + ", outputValidationRequired="
				+ outputValidationRequired + ", requiredHeaders=" + Arrays.toString(requiredHeaders)
				+ ", securityHeaders=" + securityHeaders + "]";
	}
	
}

class SecurityHeaders
{
	private String headername;
	private String headervalue;
	
	public String getHeadername() {
		return headername;
	}
	public void setHeadername(String headername) {
		this.headername = headername;
	}
	public String getHeadervalue() {
		return headervalue;
	}
	public void setHeadervalue(String headervalue) {
		this.headervalue = headervalue;
	}
	@Override
	public String toString() {
		return "SecurityHeaders [headername=" + headername + ", headervalue=" + headervalue + "]";
	}
}
