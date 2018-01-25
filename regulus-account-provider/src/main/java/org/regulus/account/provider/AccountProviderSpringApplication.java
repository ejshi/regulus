package org.regulus.account.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AccountProviderSpringApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AccountProviderSpringApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(AccountProviderSpringApplication.class);
		springApplication.run(args);
		System.out.println("===========start account provider success======");
	}
}
