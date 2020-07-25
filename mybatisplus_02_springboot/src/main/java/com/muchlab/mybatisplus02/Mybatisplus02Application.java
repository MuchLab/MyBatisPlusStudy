package com.muchlab.mybatisplus02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.muchlab.mybatisplus02.mapper"})
public class Mybatisplus02Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybatisplus02Application.class, args);
    }

}
