package com.scservice;
 
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication
//@MapperScan(basePackages = {"com.scservice.mapper","com.scservice.service"})
@MapperScan(basePackages = {"com.scservice.mapper"})
public class Application {
 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
 
}