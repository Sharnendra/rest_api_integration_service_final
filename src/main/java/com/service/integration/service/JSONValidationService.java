package com.service.integration.service;

import java.io.IOException;
import java.net.URL;
import java.io.File;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.service.integration.utility.ValidationUtils;
import com.github.fge.jsonschema.main.JsonSchema;

@Service
public class JSONValidationService {
	
	/* 
	 * 
	 * Method: isJsonValid
	 * Type: Override
	 * Use: Call this function when you send schema in form of JsonSchema and data in form of JsonNode
	 * 
	 **/
	public boolean isJsonValid(JsonSchema jsonSchemaNode, JsonNode jsonNode) throws ProcessingException
    {
        return ValidationUtils.isJsonValid(jsonSchemaNode, jsonNode);
    }
	
	/* 
	 * 
	 * Method: isJsonValid
	 * Type: Override
	 * Use: Call this function when you send schema in form of String and data in form of String
	 * 
	 **/
	public boolean isJsonValid(String schemaText, String jsonText) throws ProcessingException, IOException
    {
        return ValidationUtils.isJsonValid(schemaText, jsonText);
    }
	
	/* 
	 * 
	 * Method: isJsonValid
	 * Type: Override
	 * Use: Call this function when you send schema in form of File and data in form of File
	 * 
	 **/
	public boolean isJsonValid(File schemaFile, File jsonFile) throws ProcessingException, IOException
    {   
		return ValidationUtils.isJsonValid(schemaFile, jsonFile);
    }
	
	/* 
	 * 
	 * Method: isJsonValid
	 * Type: Override
	 * Use: Call this function when you send schema in form of URL and data in form of URL
	 * 
	 **/
	public boolean isJsonValid(URL schemaURL, URL jsonURL) throws ProcessingException, IOException
    {   
		return ValidationUtils.isJsonValid(schemaURL, jsonURL);
    }
	
	/* 
	 * 
	 * Method: isJsonValid
	 * Type: Override
	 * Use: Call this function when you send schema in form of File and data in form of String
	 * 
	 **/
	public boolean isJsonValid(File schemaText, String jsonText) throws ProcessingException, IOException
    {   
		return ValidationUtils.isJsonValid(schemaText, jsonText);
    }
}
