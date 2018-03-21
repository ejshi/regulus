package org.regulus.oauth.center.config;

import java.util.Properties;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@SpringBootConfiguration
public class KaptchaConfig {
	
	@Bean(name = "kaptchaProducer")
	public DefaultKaptcha getKaptchaBean() {
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
		properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "105,179,90");
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "30");
		//properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,\u5b8b\u4f53");
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "ABCDE2345678YNMkPWX");
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
		properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "150");
		properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "50");
		properties.setProperty(Constants.KAPTCHA_NOISE_COLOR, "blue");
		properties.setProperty(Constants.KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");//去掉干扰线
		properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");//阴影渲染效果
		properties.setProperty(Constants.KAPTCHA_SESSION_KEY, "kaptcha.code");
		properties.setProperty(Constants.KAPTCHA_SESSION_DATE, "kaptcha.date");
		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}
}
