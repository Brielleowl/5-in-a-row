package com.example.server;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import com.example.server.controller.*;
import jakarta.ws.rs.core.Application;

@SpringBootApplication
public class ServerApplication {

	// @Bean
	// public ResourceConfig resourceConfig(){
	// 	return new ResourceConfig();
	// }

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(ServerApplication.class, args);

		

	}



}
