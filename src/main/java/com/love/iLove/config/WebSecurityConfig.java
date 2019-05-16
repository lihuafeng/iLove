package com.love.iLove.config;

import com.love.iLove.filter.jwt.JWTAuthenticationFilter;
import com.love.iLove.handler.LoginSuccessHandler;
import com.love.iLove.service.impl.AnyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AnyUserDetailsService anyUserDetailsService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/register","/login","/login/qqLogin").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login")
                .successHandler(loginSuccessHandler)//添加自定义登录成功处理页面
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        // 在 UsernamePasswordAuthenticationFilter 前添加 JWTAuthenticationFilter
            http.addFilterAt(new JWTAuthenticationFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(anyUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder())
        ;
    }
}
