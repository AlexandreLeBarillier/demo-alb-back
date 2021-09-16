package com.alb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class DemoAlbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAlbApplication.class, args);
	}

}
