package com.example.blog.config

import com.example.blog.config.auth.CustomOAuth2UserService
import com.example.blog.domain.user.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var customOAuth2UserService: CustomOAuth2UserService

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name)
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService)
    }
}