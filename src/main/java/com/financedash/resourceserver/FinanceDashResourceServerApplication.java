package com.financedash.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableResourceServer
@EnableCaching
public class FinanceDashResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceDashResourceServerApplication.class, args);
    }

}
