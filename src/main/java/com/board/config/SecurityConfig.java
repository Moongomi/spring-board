package com.board.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http.authorizeRequests()
                //.antMatchers("/register").authenticated()
                .antMatchers("/board").authenticated()
                .antMatchers("/admin/**")
                .hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();*/

        //http.authorizeRequests().antMatchers("/**").permitAll();

        http.authorizeRequests().antMatchers("/post").authenticated()
                .anyRequest().permitAll();


        http.csrf().disable();
        http.headers()
                .addHeaderWriter(
                        new XFrameOptionsHeaderWriter(
                                new WhiteListedAllowFromStrategy(Arrays.asList("localhost"))    // 여기!
                        )
                )
                .frameOptions().sameOrigin();


    }
}