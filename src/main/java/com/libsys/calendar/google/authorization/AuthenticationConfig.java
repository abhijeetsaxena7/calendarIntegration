package com.libsys.calendar.google.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Configuration
public class AuthenticationConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	ClientRegistrationRepository clientRegistrationRepository;
	
	@Override	
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.antMatcher("/**")
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and()
				.oauth2Login()/*
								 * .authorizationEndpoint().authorizationRequestResolver(new
								 * CustomAuthorizationRequestResolver(clientRegistrationRepository))
								 */
		/* .and().and().rememberMe() */;
		
		
	}
	
	
}
