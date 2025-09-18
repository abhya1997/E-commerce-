package com.e_commerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.e_commerce.jwt.AuthTokenFilter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,
	                                       UserDetailsService userDetailsService,
	                                       PasswordEncoder passwordEncoder) throws Exception {
	    http
	      .csrf(csrf -> csrf.disable())
	      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	      .authorizeHttpRequests(auth -> auth
	          .requestMatchers("/user/login", "/user/registration", "/user/get","/error", "/swagger-ui/**",
	                           "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
	          .permitAll()
	          .anyRequest().authenticated()
	      )
	      .formLogin(form -> form.disable())
	      .httpBasic(basic -> basic.disable())
	      .authenticationProvider(authenticationProvider(userDetailsService, passwordEncoder))
	      .exceptionHandling(ex -> ex.accessDeniedHandler((req, res, excep) ->
	          res.sendError(HttpServletResponse.SC_FORBIDDEN, "Custom 403 – Access Denied")
	      ))
	      .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    
   

 
    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>(dispatcherServlet, "/");
        
        return registration;
    }

}
