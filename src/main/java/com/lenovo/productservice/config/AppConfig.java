package com.lenovo.productservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AppConfig {

  @Value("${account-service.url}")
  private String accountServiceUrl;
}
