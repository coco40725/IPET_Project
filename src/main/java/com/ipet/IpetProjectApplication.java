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
    @PostConstruct
    public void init(){
        // java 會自動幫我們把 時間  UTC+8hr，但mongodb 其實存的就是已經 UTC+8 ，所以不需要再加
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    public static void main(String[] args) {
        SpringApplication.run(IpetProjectApplication.class, args);
    }

}
