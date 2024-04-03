package com.consola.lis.configuration;

import com.consola.lis.jwt.JwtAuthenticationFilter;
import com.consola.lis.model.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/admin/**").hasRole(UserRole.ADMIN.name()) // Ejemplo de autorización basada en roles
                                .requestMatchers("/api/user/**").hasAnyRole(UserRole.AUXADMI.name(), UserRole.ADMIN.name(), UserRole.AUXPROG.name()) // Ejemplo de autorización basada en roles
                                .requestMatchers("/api/console-lis/auth/**").permitAll()
                                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sesionManager ->
                        sesionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter,  UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                    response.getWriter().write("1020 - Access Denied");
                                }))
                .build();
    }
}
