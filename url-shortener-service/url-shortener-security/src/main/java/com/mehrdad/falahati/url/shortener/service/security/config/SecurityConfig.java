package com.mehrdad.falahati.url.shortener.service.security.config;

import com.mehrdad.falahati.common.security.config.AbstractSecurityConfig;
import com.mehrdad.falahati.common.security.filter.JwtTokenFilter;
import com.mehrdad.falahati.url.shortener.service.security.UserManagementImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig extends AbstractSecurityConfig {

    private final UserManagementImpl userManagement;
    private final JwtTokenFilter jwtTokenFilter;

    @PostConstruct
    public void init() {
        setUserManagement(userManagement);
    }

    @Bean
    @Override
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/**")
                .permitAll()
                .requestMatchers("/api-docs/**", "/swagger-ui/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
