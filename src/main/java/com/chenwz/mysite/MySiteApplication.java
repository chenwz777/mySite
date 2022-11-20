package com.chenwz.mysite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chenwz.mysite.dao")
public class MySiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySiteApplication.class, args);
    }

}
