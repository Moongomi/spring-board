package com.board.config;

import com.board.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeRequests().antMatchers("/board/list","/board/post","/board/post/**").authenticated()
                .anyRequest().permitAll()
                .and().headers().frameOptions().sameOrigin()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().formLogin().loginPage("/user/login").defaultSuccessUrl("/board/list")
                .and()
                .logout()
                //.logoutUrl("/user/logout")
                .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true); // 로그아웃 이후 세션 전체 삭제 여부
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}