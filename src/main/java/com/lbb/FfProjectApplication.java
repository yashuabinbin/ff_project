package com.lbb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lbb.dao")
public class FfProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FfProjectApplication.class, args);
    }

}
