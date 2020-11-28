package com.pinxv.hackathon2020_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author fengguohao
 */
@SpringBootApplication
@EnableScheduling
public class Hackathon2020BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hackathon2020BackendApplication.class, args);
    }

}
