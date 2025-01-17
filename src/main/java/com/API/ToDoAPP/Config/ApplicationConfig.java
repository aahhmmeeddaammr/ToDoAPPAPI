package com.API.ToDoAPP.Config;

import com.API.ToDoAPP.Models.Admin;
import com.API.ToDoAPP.Repositories.AdminRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class ApplicationConfig {

    private final AdminRepository adminRepository;
    public ApplicationConfig( AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            try {
                return (UserDetails) findUserByEmail(username).orElseThrow(() -> new IllegalAccessException());
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Invalid Email");
            }
        };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration) throws  Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    private Optional<Admin> findUserByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
