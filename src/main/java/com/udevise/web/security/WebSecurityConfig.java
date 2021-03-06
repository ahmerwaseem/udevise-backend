package com.udevise.web.security;
import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import com.udevise.web.filters.JWTAuthorizationFilter;
import com.udevise.web.services.UserService;
import com.udevise.web.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private Auth0Properties auth0Properties;

  @Autowired
  private UserService userService;

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    //default react port and location
    configuration.setAllowedOrigins(Arrays.asList(
      "http://localhost:3000",
      "http://udevise.com",
      "https://udevise.com",
      "http://www.udevise.com",
      "https://www.udevise.com"
      ));
    //configure all type of restful calls we make here
    configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT"));
    configuration.setAllowCredentials(true);
    //need that sweet jwt
    configuration.addAllowedHeader  ("Authorization");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().and().authorizeRequests()
      .antMatchers(HttpMethod.GET, "/api/v1/questionnaire").authenticated()
      .antMatchers(HttpMethod.GET, "/api/v1/questionnaire/survey/{id}").permitAll()
      .antMatchers(HttpMethod.GET, "/api/v1/questionnaire/quiz/{id}").authenticated()
      .antMatchers(HttpMethod.POST, "/api/v1/questionnaire").authenticated()
      .antMatchers(HttpMethod.GET, "/api/v1/questionnaire/{id}/detail").authenticated()
      .antMatchers(HttpMethod.POST, "/api/v1/response").permitAll()
      .antMatchers(HttpMethod.PUT, "/api/v1/response/**").authenticated()
      .antMatchers(HttpMethod.GET, "/api/v1/response/**").authenticated()
      .and()
      .addFilter(new JWTAuthorizationFilter(authenticationManager(),auth0Properties, userService)) ;
  }

}
