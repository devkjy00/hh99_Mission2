package com.jy.mission2.security;


import com.jy.mission2.security.filter.FormLoginFilter;
import com.jy.mission2.security.provider.FormLoginAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // AuthenticationManager가 Provider에게 인증을 위임할 수 있도록 provider를 연결
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(formLoginAuthProvider());
//                .authenticationProvider(jwtAuthProvider);
    }

    @Override
    public void configure(WebSecurity web){
             web
                     .ignoring()
                     .antMatchers("/h2-console/**");

    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();


//        http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class);
//                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)

        http.authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/forbidden.html");


    }

    @Bean
    public FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter formLoginFilter = new FormLoginFilter(authenticationManager());
        formLoginFilter.setFilterProcessesUrl("/user/login");
        formLoginFilter.setAuthenticationSuccessHandler(formLoginSuccessHandler());
        formLoginFilter.afterPropertiesSet();
        return formLoginFilter;
    }

    @Bean
    public AuthenticationSuccessHandler formLoginSuccessHandler() {
        return new FormLoginSuccessHandler();
    }

    @Bean
    public FormLoginAuthProvider formLoginAuthProvider() {
        return new FormLoginAuthProvider();
    }

}


