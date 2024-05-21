package com.example.springjpademo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    //WebSecurityConfigurerAdapter.configure 대응
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //spring security deprecated로 csrf생성이 안되는것같음, 화면에서도 널처리해놓고, 일단 권장사항으로 csrf disable
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
