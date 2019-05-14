package com.service.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Integration {
//HRSS APP Code
	public static void main(String[] args) {
		SpringApplication.run(Integration.class, args);
	}

}
