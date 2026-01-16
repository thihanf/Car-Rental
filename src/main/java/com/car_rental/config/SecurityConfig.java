package com.car_rental.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

   @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

   http
        .csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/", 
                "/login",
                "/signup",
                "/gallery",      
                "/details/**",    
                "/about",          
                "/css/**",
                "/js/**",         
                "/images/**"
            ).permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )

        .formLogin(login -> login
            .loginPage("/login")
            .loginProcessingUrl("/login") // ✅ IMPORTANT
            .successHandler(loginSuccessHandler)
            .permitAll()
        )

        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        );

    return http.build();
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}