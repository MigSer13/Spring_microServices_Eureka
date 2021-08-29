package com.myhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClientFrontApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientFrontApp.class, args);
    }


}
