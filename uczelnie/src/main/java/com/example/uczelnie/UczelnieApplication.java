package com.example.uczelnie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class UczelnieApplication {

    public static void main(String[] args) {
        SpringApplication.run(UczelnieApplication.class, args);
    }

}
