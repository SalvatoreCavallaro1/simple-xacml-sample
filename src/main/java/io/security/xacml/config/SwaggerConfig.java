package io.security.xacml.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .groupName("Sample api with xacml authorization")
      .select()
      .apis(RequestHandlerSelectors.basePackage("io.security.xacml"))
      .paths(regex("/.*"))
      .build()
      .apiInfo(metaData())
      .securitySchemes(Collections.singletonList(new BasicAuth("sampleBasicAuth")))
      .securityContexts(Collections.singletonList(SecurityContext.builder()
        .securityReferences(
          Collections
            .singletonList(new SecurityReference("sampleBasicAuth", new AuthorizationScope[0])))
        .build()));
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
      .title("How to use XACML 3.0 JSON Profile")
      .description(
        "APIs with access control in terms of XACML 3.0 Json Profile")
      .build();
  }
}
