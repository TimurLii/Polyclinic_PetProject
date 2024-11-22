package com.example.polyclinic_petproject.config;

import com.example.polyclinic_petproject.enums.Role;
import com.example.polyclinic_petproject.service.DoctorDetailService;
import com.example.polyclinic_petproject.service.PatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private final DoctorDetailService doctorDetailService;

    @Autowired
    private final PatientDetailsService patientDetailsService;
    public WebSecurityConfig(DoctorDetailService doctorDetailService, PatientDetailsService patientDetailsService) {
        this.doctorDetailService = doctorDetailService;
        this.patientDetailsService = patientDetailsService;
    }

    @Autowired

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/login").permitAll()
                        .requestMatchers("/appointments", "/bookings", "/doctors", "/patients").hasAuthority(Role.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .userDetailsService(doctorDetailService)
                .userDetailsService(patientDetailsService)
                .build();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/h2-console/**");
    }
}