package com.example.polyclinic_petproject.config;

import com.example.polyclinic_petproject.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    PatientService patientService;

    //
//        @Bean
//        public BCryptPasswordEncoder bCryptPasswordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//        protected void configure(HttpSecurity httpSecurity) throws Exception {
//            httpSecurity
//                    .csrf()
//                    .disable()
//                    .authorizeRequests()
//                    //Доступ только для не зарегистрированных пользователей
//                    .antMatchers("/registration").not().fullyAuthenticated()
//                    //Доступ только для пользователей с ролью Администратор
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/news").hasRole("USER")
//                    //Доступ разрешен всем пользователей
//                    .antMatchers("/", "/resources/**").permitAll()
//                    //Все остальные страницы требуют аутентификации
//                    .anyRequest().authenticated()
//                    .and()
//                    //Настройка для входа в систему
//                    .formLogin()
//                    .loginPage("/login")
//                    //Перенарпавление на главную страницу после успешного входа
//                    .defaultSuccessUrl("/")
//                    .permitAll()
//                    .and()
//                    .logout()
//                    .permitAll()
//                    .logoutSuccessUrl("/");
//        }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers("/patient", "/doctor", "/appointment").hasAuthority("admin")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .build();
    }

}
