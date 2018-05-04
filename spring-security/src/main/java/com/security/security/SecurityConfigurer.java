package com.security.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = Logger.getLogger(SecurityConfigurer.class);

	@Autowired
	CasAuthenticationConfigurer casAuthenticationConfigurer;	

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception{
		
		logger.info("casAuthenticationFilter");

		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		
		casAuthenticationFilter.setAuthenticationManager( authenticationManager() );
		casAuthenticationFilter.setAuthenticationFailureHandler(casAuthenticationConfigurer.simpleAuthenticationFailuareHandler());
		casAuthenticationFilter.setAuthenticationSuccessHandler(casAuthenticationConfigurer.savedRequestAwareAuthenticationSuccessHandler());
		
		return casAuthenticationFilter;
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("configure(AuthenticationManagerBuilder auth)");
		auth.authenticationProvider(casAuthenticationConfigurer.casAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("configure(HttpSecurity http)");
		
		http.httpBasic().authenticationEntryPoint(casAuthenticationConfigurer.casAuthenticationEntiryPoint())
		.and().logout().logoutSuccessUrl("/logout")
		.and().addFilterAfter(casAuthenticationFilter(), CasAuthenticationFilter.class);
		
	}
	
}
