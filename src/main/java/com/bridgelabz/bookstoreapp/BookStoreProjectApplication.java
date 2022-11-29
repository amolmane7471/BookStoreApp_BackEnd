package com.bridgelabz.bookstoreapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class BookStoreProjectApplication {

    public static void main(String[] args) {

       ApplicationContext context = SpringApplication.run(BookStoreProjectApplication.class, args);

        log.info("Book Store App Started in {} Environment",context.getEnvironment().getProperty("environment"));

        log.info("Book Store DB User is {} ",context.getEnvironment().getProperty("spring.datasource.username"));
    }

}
