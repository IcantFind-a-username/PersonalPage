package com.myself.franz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.franz.mapper")
public class FranzApplication {

    public static void main(String[] args) {
        SpringApplication.run(FranzApplication.class, args);
    }

}
