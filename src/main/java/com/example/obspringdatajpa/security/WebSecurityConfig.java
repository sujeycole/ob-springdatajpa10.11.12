package com.example.obspringdatajpa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.stereotype.Controller;

@Controller
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncolder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/hola").permitAll()
                .antMatchers("/api/laptops1").permitAll()//buscar todas las laptop
                .antMatchers("/api/laptop2/{id}").permitAll()//buscar una laptop por el ID
                .antMatchers("/api/laptops3").permitAll() //crear una laptop en base de datos
                .antMatchers("/api/laptops4").hasAnyRole("admin") // actualizar una laptop existente en base de datos
                .antMatchers("/api/laptops5/{id}").hasAnyRole("admin")//borrar una laptop en base de datos por el ID
                .antMatchers("/api/laptops6").hasAnyRole("admin")//eliminar todas las laptop
                .anyRequest().authenticated().
                and().formLogin()
                .and().httpBasic();
    }
    public HttpFirewall looseHttpFirewall(){
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowBackSlash(true);
        firewall.setAllowSemicolon(true);
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncolder())
                .withUser("user").password(passwordEncolder().encode("1234")).roles("user")
                .and()
                .withUser("admin").password(passwordEncolder().encode("12345")).roles("admin");
    }


}
