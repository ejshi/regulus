package org.regulus.oauth.center;

import java.util.List;

import org.regulus.oauth.center.mapper.usercenter.UserMapper;
import org.regulus.oauth.center.model.usercenter.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;import org.springframework.session.FindByIndexNameSessionRepository;

import com.alibaba.fastjson.JSON;

@SpringBootApplication
public class OauthCenterApplication extends SpringBootServletInitializer{
    
    private static Logger LOGGER = LoggerFactory.getLogger(OauthCenterApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OauthCenterApplication.class);
	}

	public static void main(String[] args) {
	    SpringApplication springApplication = 
	            new SpringApplicationBuilder(OauthCenterApplication.class).web(true).build();
		ConfigurableApplicationContext context = springApplication.run(args);
		System.out.println(context.getBean(FindByIndexNameSessionRepository.class));
		UserMapper userMapper = context.getBean(UserMapper.class);
		List<User> users = userMapper.selectByIds("1,11,12");
		System.out.println(JSON.toJSONString(users));
		LOGGER.info("======== start oauth center success ======");
	}
}
