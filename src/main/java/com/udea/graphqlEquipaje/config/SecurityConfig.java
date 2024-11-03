package com.udea.graphqlEquipaje.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll() // Permite el acceso a todas las rutas sin autenticación
                .and()
                .csrf().disable() // Desactiva la protección CSRF si es necesario
                .headers().frameOptions().disable(); // Desactiva la restricción de visualización en iframes
    }
}