package com.sms.configuration;

import com.sms.filters.JwtRequestFilter;
import com.sms.repositories.UserRepository;
import com.sms.sevices.User.UserService;
import com.sms.utils.Jwtutil;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final JwtRequestFilter jwtRequestFilter;
    @Autowired
    private Jwtutil jwtutil;
    @Autowired
    private UserService userService;

    public WebSecurityConfiguration(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("Configuring security filter chain...");  // Debugging the start of filter chain configuration
        httpSecurity
                .csrf(csrf -> csrf.disable())  // Disable CSRF for stateless APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/authenticate").permitAll()  // Allow unauthenticated access to /authenticate
                        .requestMatchers("/api/admin/**").hasRole("admin")   // Only allow access to /api/admin/** if user has "ADMIN" role
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session management
                )
                .addFilterAfter(jwtRequestFilter, CsrfFilter.class);  // Add JWT filter
        System.out.println("Security filter chain configured successfully.");  // Debugging after configuring the filter chain

        return httpSecurity.build();  // Build and return the SecurityFilterChain configuration
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCryptPasswordEncoder for password hashing
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();  // AuthenticationManager bean
    }
}
