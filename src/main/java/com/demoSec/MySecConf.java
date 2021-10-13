package com.demoSec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
@Configuration
public class MySecConf extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@Autowired
	private MyAuthProvider myAuthPro;
	//This method allows to override authentication manager
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		//BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		InMemoryUserDetailsManager UserDetailsManager = new InMemoryUserDetailsManager();
//		UserDetails user = User.withUsername("tom").password(bCryptPasswordEncoder.encode("pass")).authorities("read").build();
//		UserDetailsManager.createUser(user);
//		
//		auth.userDetailsService(UserDetailsManager).passwordEncoder(bCryptPasswordEncoder);
//		
//		/*
//		 * 
//		 * We can create a method that return password encoder and annotate it as bean and autowire to this class also.
//		 * */
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(myAuthPro);
	
		/*
		 * 
		 * We can create a method that return password encoder and annotate it as bean and autowire to this class also.
		 * */
	}
	
	
	
	//Here we can define what kind of authentication is required. - httpBasic, FormLogin etc.
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.addFilterBefore(new MyFilter(), BasicAuthenticationFilter.class);
    	//http.formLogin();
		http.authorizeRequests().anyRequest().authenticated();
    	//http.authorizeRequests().antMatchers("/hello").authenticated().anyRequest().denyAll();
	}
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
}
