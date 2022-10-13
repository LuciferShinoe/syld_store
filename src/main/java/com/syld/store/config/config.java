package com.syld.store.config;

import com.syld.store.config.auth.HandleFailureAuthentication;
import com.syld.store.config.auth.HandleSuccessAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class config {
    private final HandleSuccessAuthentication handleSuccessAuthentication;
    private final HandleFailureAuthentication handleFailureAuthentication;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .antMatchers( "/admin/**").authenticated()
                .anyRequest().permitAll();

        http.formLogin(
                form -> form.loginPage("/auth/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(handleSuccessAuthentication)
                        .failureHandler(handleFailureAuthentication)
        );


        http.logout().logoutUrl("/logout");

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/assets/**");
    }
}
