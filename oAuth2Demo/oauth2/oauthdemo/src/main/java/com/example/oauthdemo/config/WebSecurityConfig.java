package com.example.oauthdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//                http.csrf().disable().exceptionHandling().authenticationEntryPoint(
//                        (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
//                        .authorizeRequests().antMatchers("/**").authenticated().and().httpBasic();
//        http.csrf().disable()
//                .authorizeRequests().antMatchers("/user").authenticated();
        super.configure(http);


//        http.csrf().disable();
//        http
//                .requestMatchers().antMatchers("/oauth/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**").authenticated()
//                .and()
//                .formLogin().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("reader").password("reader").authorities("USER_READ").and()
                .withUser("writer").password("writer").authorities("USER_READ", "USER_WRITE");
        //        auth.jdbcAuthentication();
    }
}
