package me.dkcdev.task_management_api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import me.dkcdev.task_management_api.common.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class AuthenticationProviderConfig {
    
    @Bean
    AuthenticationProvider authProvider(UserDetailsServiceImpl service, PasswordEncoder encoder){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(service);
        provider.setPasswordEncoder(encoder);
        return provider;
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }
}
