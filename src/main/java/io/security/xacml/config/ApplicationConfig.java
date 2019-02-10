package io.security.xacml.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate(AppCredentials appCredentials){
        return new RestTemplateBuilder()
                .basicAuthentication(appCredentials.getUsername(),appCredentials.getPassword()).build();
    }
}
