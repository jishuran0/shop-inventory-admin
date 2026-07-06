package com.shop;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.shop.mapper")
@SpringBootApplication
public class ShopBackedApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopBackedApplication.class, args);
    }
}