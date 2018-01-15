package com.example.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/index.html", "/static/**");
	}
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub

		http.authorizeRequests().antMatchers("/home/**").hasRole("USER").antMatchers("/admin/**").hasRole("ADMIN");
		http.authorizeRequests()
				.antMatchers("/druid/**", "/**.js", "/**.gif", "/**.jpg","/login.html", "/**.bmp", "/**.png", "/**.css", "/**.ico")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login/v1").permitAll();

		/*
		 * http.authorizeRequests() .antMatchers("/druid/**", "/**.js", "/**.gif",
		 * "/**.jpg", "/**.bmp", "/**.png", "/**.css", "/**.ico")
		 * .permitAll().anyRequest().authenticated().and().formLogin().loginPage(
		 * "/login").defaultSuccessUrl("/") .permitAll().and().logout().permitAll();
		 * http.csrf().disable();
		 */
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER", "ADMIN");
	}

}
