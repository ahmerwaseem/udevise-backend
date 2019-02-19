package com.udevise.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Value("${spring.profiles.active}")
  public String env;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiDetails())
      .enable(!env.equals("prod"))
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.udevise.web.controllers.rest"))
      .paths(PathSelectors.any())
      .build();
  }
  private ApiInfo apiDetails() {
    return new ApiInfoBuilder()
      .title("Udevise")
      .build();
  }
}