package com.example.singlecell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.example.singlecell.mapper"})
public class SinglecellApplication {

    public static void main(String[] args) {
        SpringApplication.run(SinglecellApplication.class, args);
    }

}
