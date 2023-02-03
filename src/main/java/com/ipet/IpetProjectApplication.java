package com.ipet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableMongoRepositories
@EnableGlobalMethodSecurity(prePostEnabled = true) // 讓方法的調用是有權限限制的，開啟相關註解使用，例如: @Secured
public class IpetProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(IpetProjectApplication.class, args);
    }

}
