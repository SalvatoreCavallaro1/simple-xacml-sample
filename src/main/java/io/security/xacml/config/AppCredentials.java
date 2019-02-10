package io.security.xacml.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "appcredentials")
public class AppCredentials {

  private String username;
  private String password;
}
