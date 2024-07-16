package com.company.config;

import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Component
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity  http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry.
                    requestMatchers("/auth/login").permitAll().
                    requestMatchers("/auth/signup").hasAnyRole("ADMIN", "SUPERADMIN").
                    requestMatchers("/update/rsv").hasAnyRole("ADMIN", "SUPERADMIN").
                    requestMatchers("/read/**").hasAnyRole("ADMIN", "SUPERADMIN").
                    requestMatchers("/get_user/**").hasAnyRole("ADMIN", "SUPERADMIN").
                    requestMatchers("/test/***").hasAnyRole("ADMIN", "SUPERADMIN").
                    anyRequest().authenticated();
        });

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {

                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword,
                                   String encodedPassword) {

                return MD5Util.getMd5(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }
}
