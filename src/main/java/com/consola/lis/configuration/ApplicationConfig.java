package com.consola.lis.configuration;

import com.consola.lis.jwt.CustomUserDetails;
import com.consola.lis.model.entity.UserHelloLis;
import com.consola.lis.model.entity.UserLis;
import com.consola.lis.model.repository.UserHelloLisRepository;
import com.consola.lis.model.repository.UserLisRepository;
import com.consola.lis.util.exception.UserAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserHelloLisRepository userHelloLisRepository;
    private final UserLisRepository userLisRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailService() {
        return username -> {
            UserLis userLis = userLisRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            UserHelloLis userHelloLis = userHelloLisRepository.findByUserLis(userLis)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            System.out.println("User details loaded successfully for: " + username);
            return new CustomUserDetails(userLis, userHelloLis);
        };
    }
}
