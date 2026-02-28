package me.dkcdev.task_management_api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptBean {
    @Bean
    BCryptPasswordEncoder getPasswordEncoder(){
        return  new BCryptPasswordEncoder(12);
    }
}
