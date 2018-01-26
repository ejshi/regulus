package org.regulus.account.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AccountProviderApplication extends SpringBootServletInitializer{
    
    private static Logger LOGGER = LoggerFactory.getLogger(AccountProviderApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AccountProviderApplication.class);
	}

	public static void main(String[] args) {
	    SpringApplication springApplication = new SpringApplicationBuilder(AccountProviderApplication.class).web(true).build();
		springApplication.run(args);
		LOGGER.info("======== start account provider success ======");
	}
}
