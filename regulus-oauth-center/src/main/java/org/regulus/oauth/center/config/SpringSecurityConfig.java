package org.regulus.oauth.center.config;

import javax.servlet.http.HttpServletRequest;

import org.regulus.common.enums.ResponseCodeEnum;
import org.regulus.common.model.ResponseJson;
import org.regulus.oauth.center.security.CustomAuthenticationDetailsSource;
import org.regulus.oauth.center.security.CustomAuthenticationFailureHandler;
import org.regulus.oauth.center.security.CustomAuthenticationSuccessHandler;
import org.regulus.oauth.center.security.CustomDaoAuthenticationProvider;
import org.regulus.oauth.center.security.CustomUserDetailsService;
import org.regulus.oauth.center.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@SpringBootConfiguration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * rememberMe记住我session的有效时长，默认30天
     */
    @Value("${spring.security.config.validitySeconds:2592000}")
    private int validitySeconds;
    /**
     * 用户同时在线最大人数，默认1个人
     */
    @Value("${spring.security.config.maximumSessions:1}")
    private int maximumSessions;
    /**
     * 密码加盐，盐默认regulus
     */
    @Value("${spring.security.config.password.salt:regulus}")
    private String salt;
    /**
     * md5次数，默认1次
     */
    @Value("${spring.security.config.password.md5.times:1}")
    private int times;
    
    @SuppressWarnings("rawtypes")
    @Autowired
    private FindByIndexNameSessionRepository redisSessionRepository;
    
    @SuppressWarnings("unchecked")
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //解决'X-Frame-Options' to 'DENY'错误
            .headers().frameOptions().disable()
            .and()
            //禁用CSRF保护
            .csrf().disable()
            //用户登录后可访问
            .authorizeRequests()
            
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/v2/api-docs").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/**/*.html").permitAll()
            .antMatchers("/**/*.js").permitAll()
            .antMatchers("/**/*.css").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers("/**/hello/**").permitAll()
            .antMatchers("/**/guest/**").permitAll()
            .antMatchers("/**/kaptcha/**").permitAll()
            
            .anyRequest().authenticated()
            .and()
            //允许通过remember me登录的用户访问
            .rememberMe()
            .rememberMeServices(initRememberMeServices())
            .and()
            .formLogin()
            .loginProcessingUrl("/login").permitAll()
            //自定义用户登录请求数据
            .authenticationDetailsSource(initAuthenticationDetailsSource())
            //登录成功回调处理
            .successHandler(initAuthenticationSuccessHandler())
            //登录失败回调处理
            .failureHandler(initAuthenticationFailureHandler())
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler((request, response,authentication) -> {
                WebUtil.send(response, new ResponseJson<String>(true,ResponseCodeEnum.REQUEST_SUCCESS));
            })
            .and()
            //匿名用户禁止访问,游客不能访问，即使配置permitAll也不能够访问
            //.anonymous().disable()
            //认证不通过后的处理
            .exceptionHandling()
            //规则1. 如果异常是 AuthenticationException，使用 AuthenticationEntryPoint 处理  
            //规则2. 如果异常是 AccessDeniedException 且用户是匿名用户，使用 AuthenticationEntryPoint 处理  
            .authenticationEntryPoint((request, response,authException) -> {
                WebUtil.send(response, new ResponseJson<String>(false,ResponseCodeEnum.OAUTH_NOT_LOGIN));
            })
            //规则3. 如果异常是 AccessDeniedException 且用户不是匿名用户，使用 AccessDeniedHandler 处理
            .accessDeniedHandler((request, response,accessDeniedException) -> {
                WebUtil.send(response, new ResponseJson<String>(false,ResponseCodeEnum.OAUTH_NOT_AUTHORITY));
            })
            .and()
            //最多只允许几个用户在线
            .sessionManagement().maximumSessions(maximumSessions)
            .sessionRegistry(new SpringSessionBackedSessionRegistry(redisSessionRepository))
            .expiredSessionStrategy(eventØ ->{
                WebUtil.send(eventØ.getResponse(),new ResponseJson<String>(false,ResponseCodeEnum.OAUTH_SESSION_EXPIRED));
            })
        ;
    }
    
    @Bean
    public RememberMeServices initRememberMeServices(){
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        rememberMeServices.setAlwaysRemember(false);
        rememberMeServices.setRememberMeParameterName("rememberMe");
        rememberMeServices.setValiditySeconds(validitySeconds);
        return rememberMeServices;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder build) throws Exception {
        build.authenticationProvider(initAuthenticationProvider());
    }
    
    @Bean
    public DaoAuthenticationProvider initAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new CustomDaoAuthenticationProvider();
        authenticationProvider.setHideUserNotFoundExceptions(false);
        authenticationProvider.setUserDetailsService(initUserDetailsService());
        authenticationProvider.setPasswordEncoder(initMd5PasswordEncoder());
        authenticationProvider.setSaltSource(initSaltSource());
        return authenticationProvider;
    }
    
    @Bean
    public UserDetailsService initUserDetailsService(){
        return new CustomUserDetailsService();
    }
    /**
     * Md5加密,加密方式：password + "{" + salt.toString() + "}"以及MD5次数，具体代码参考
     * @see org.springframework.security.authentication.encoding.BasePasswordEncoder.mergePasswordAndSalt(String, Object, boolean)
     * <p>if you want to update this encrypt method ,you should extend Md5PasswordEncoder and @Override mergePasswordAndSalt方法</p>
     */
    @Bean
    public Md5PasswordEncoder initMd5PasswordEncoder(){
        Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
        passwordEncoder.setIterations(times);
        return passwordEncoder;
    }
    
    @Bean
    public SaltSource initSaltSource(){
        SystemWideSaltSource saltSource = new SystemWideSaltSource();
        saltSource.setSystemWideSalt(salt);
        return saltSource;
    }

    @Bean
    public AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> initAuthenticationDetailsSource(){
        return new CustomAuthenticationDetailsSource();
    }
    
    @Bean
    public AuthenticationSuccessHandler initAuthenticationSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();
    }
    
    @Bean
    public AuthenticationFailureHandler initAuthenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler();
    }
}
