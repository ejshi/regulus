package org.regulus.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoWebApplication extends SpringBootServletInitializer{
    
    private static Logger LOGGER = LoggerFactory.getLogger(DemoWebApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemoWebApplication.class);
	}

	public static void main(String[] args) {
	    SpringApplication springApplication =
	            new SpringApplicationBuilder(DemoWebApplication.class).web(true).build();
		springApplication.run(args);
		LOGGER.info("======== start demo web success ======");
	}
}
