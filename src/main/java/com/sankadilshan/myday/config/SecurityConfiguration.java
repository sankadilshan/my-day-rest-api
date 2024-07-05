package com.sankadilshan.myday.config;

import com.sankadilshan.myday.Constants;
import com.sankadilshan.myday.dao.impl.MyDayUserDetailsService;
import com.sankadilshan.myday.security.JwtVerifierFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private JwtVerifierFilter jwtVerifierFilter;
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public SecurityConfiguration(JwtVerifierFilter jwtVerifierFilter, @Qualifier("customAuthenticationEntryPoint") AuthenticationEntryPoint authenticationEntryPoint){
        this.jwtVerifierFilter = jwtVerifierFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyDayUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(
                                "/api/v1/auth/sign",
                                "/api/v1/auth/signup",
                                "/actuator/health/expense",
                                "/api-docs/**",
                                "/swagger-ui/**")
                                .permitAll()
                                .requestMatchers("/api/v1/auth/token").hasRole(Constants.ADMIN)
                        .anyRequest().authenticated()
                )
                .httpBasic(basic ->  basic.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtVerifierFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .exceptionHandling(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
