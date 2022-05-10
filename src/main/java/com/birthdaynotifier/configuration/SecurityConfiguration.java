package com.birthdaynotifier.configuration;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.constant.RequestPathConstants;
import com.birthdaynotifier.filter.JWTFilter;
import com.birthdaynotifier.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTFilter jwtFilter;
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfiguration(CustomUserDetailsService userService, BCryptPasswordEncoder bCryptPasswordEncoder, JWTFilter jwtFilter, JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtFilter = jwtFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(RequestPathConstants.auth_paths).permitAll()
                .antMatchers(RequestPathConstants.user_permitted_paths).hasAnyRole(Constants.user, Constants.admin)
                .antMatchers(RequestPathConstants.admin_permitted_paths).hasRole(Constants.admin)
                .antMatchers(RequestPathConstants.frontend_permitted_paths).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
