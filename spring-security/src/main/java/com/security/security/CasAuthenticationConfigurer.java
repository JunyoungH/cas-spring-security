package com.security.security;

import org.apache.log4j.Logger;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.security.service.am.CustomUserDetailsServiceImpl;

@Configuration
public class CasAuthenticationConfigurer{
	
	private static final Logger logger = Logger.getLogger(CasAuthenticationConfigurer.class);
	
	@Autowired
	CustomUserDetailsServiceImpl userDetailServiceImpl;

	@Value("${cas.server.url}")
	private String casBaseUrl;
	
	@Value("${cas.service.url}")
	private String casServiceUrl;
	
	@Bean
	public ServiceProperties serviceProperties() {
		logger.info("serviceProperties");
		
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService(casServiceUrl+"/login/cas");
		serviceProperties.setSendRenew(false);

		return serviceProperties;
	}
	
	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		logger.info("casAuthenticationProvider");
		CasAuthenticationProvider casAuthenticationProvier = new CasAuthenticationProvider();
		casAuthenticationProvier.setAuthenticationUserDetailsService(authenticationUserDetailsService());
		casAuthenticationProvier.setServiceProperties(serviceProperties());
		casAuthenticationProvier.setTicketValidator(cas20ServiceTicketValidator());
		casAuthenticationProvier.setKey("casAuthenticationProvierKey");
	
		return casAuthenticationProvier;
	}
	
	@Bean
	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> authenticationUserDetailsService(){
		logger.info("authenticationUserDetailsService");
		
		return userDetailServiceImpl;
	}
	
	@Bean
	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
		logger.info("cas20ServiceTicketValidator");
		return new Cas20ServiceTicketValidator(casBaseUrl);
	}

	@Bean
	SimpleUrlAuthenticationFailureHandler simpleAuthenticationFailuareHandler() {
		logger.info("simpleAuthenticationFailuareHandler");

		SimpleUrlAuthenticationFailureHandler simpleAuthenticationFailuareHandler = new SimpleUrlAuthenticationFailureHandler();
		simpleAuthenticationFailuareHandler.setDefaultFailureUrl("/casfailed.jsp");
		return simpleAuthenticationFailuareHandler;
	}
	
	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
		logger.info("savedRequestAwareAuthenticationSuccessHandler");

		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setUseReferer(true);
		return successHandler;
	}
	
	
	@Bean
	CasAuthenticationEntryPoint casAuthenticationEntiryPoint() {
		logger.info("casAuthenticationEntiryPoint");
		
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(casBaseUrl+"/login");
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

/*	@Bean
	public LogoutFilter logoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter(casBaseUrl+"/logout", new SecurityContextLogoutHandler());
		logoutFilter.setFilterProcessesUrl("/j_spring_cas_security_logout");
		
		return logoutFilter;
	}
	
	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setProxyGrantingTicketStorage(proxyGrantingTicketStorage());
		casAuthenticationFilter.setProxyReceptorUrl("/j_spring_cas_security_proxyreceptor");
		
		return casAuthenticationFilter;
	}
	
	
	
	@Bean
	ProxyGrantingTicketStorageImpl proxyGrantingTicketStorage() {
		logger.info("proxyGrantingTicketStorage");

		
		return new ProxyGrantingTicketStorageImpl();
	}
	
	@Bean
	DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
		logger.info("defaultWebSecurityExpressionHandler");

		
		return new DefaultWebSecurityExpressionHandler();
	}*/
}
