package com.javainuse.bootmysqlcrud.config;

import com.javainuse.bootmysqlcrud.config.JWTAuthorizationFilter;
import jakarta.servlet.Filter;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebSecurityConfig {
    private static final String[] WHITE_LIST_URL = new String[] {
            "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security", "/swagger-ui/**", "/webjars/**",
            "/swagger-ui.html", "/api/auth/**", "/api/test/**", "/api/v1/authentication/login", "/api/v1/authentication/validartoken", "/api/v1/authentication/validaciones" };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return (PasswordEncoder)new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)auth.requestMatchers(WHITE_LIST_URL)).permitAll().anyRequest()).authenticated())

                .cors().configurationSource(corsConfigurationSource());
        http.addFilterBefore((Filter)new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return (SecurityFilterChain)http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(new String[] { "http://localhost:4200", "http://localhost:9000", "http://miportal.mandalaacademy.pe", "https://miportal.mandalaacademy.pe", "http://apimiportal.mandalaacademy.pe", "https://miportal.mandalaacademy.pe", "http://*", "https://*" }));
        config.setAllowedMethods(Arrays.asList(new String[] { "GET", "POST", "PUT", "DELETE", "OPTIONS" }));
        config.setAllowCredentials(Boolean.valueOf(true));
        config.setAllowedHeaders(Arrays.asList(new String[] { "Content-Type", "Authorization" }));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return (CorsConfigurationSource)source;
    }
}
