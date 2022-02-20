package com.lenovo.productservice;

import com.github.cloudyrock.spring.v5.EnableMongock;
import com.lenovo.configuration.WebSecurityConfig;
import com.lenovo.productservice.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableMongock
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
