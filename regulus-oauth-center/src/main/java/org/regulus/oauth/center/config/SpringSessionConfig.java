package org.regulus.oauth.center.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
/**
 * springSession配置session在redis中的存活时间
 * @author shijun
 */
@SpringBootConfiguration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=600)
public class SpringSessionConfig {

}
