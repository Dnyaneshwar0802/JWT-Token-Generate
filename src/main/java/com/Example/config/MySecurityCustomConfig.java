package com.Example.config;

import com.Example.filter.JWTTokenVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class MySecurityCustomConfig {
    @Autowired
    UserDetailsService userDetailsService;
    @Bean
    PasswordEncoder passwordEncoder(){
        //Bcrypt class will hash password so no one can see and cant be reverse and see orignal pass
        return new BCryptPasswordEncoder(10);
    }
    /*
    Insted of accesing secured url by default username and pass
    i want url fetch by DB users
    */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
       /*
       Providing password encoder so password can do hashing and match with same strength
       */
        provider.setPasswordEncoder(passwordEncoder());
/*
        IT needed USerDeails Service so we have to implement our own userDetails Service
        Check config pkg MyCustomUserDetailsService which implement UserDetailsService
*/
        provider.setUserDetailsService(userDetailsService);
    return provider;
    }
   /*
    Taking Control over own filter Chain instead of using default one
    allowing few url public few secured
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Just disabling CSRF so we can do post requests
        http.csrf(csrf -> csrf.disable())
                //making some url private/public/authorized
                .authorizeHttpRequests(a -> a.requestMatchers("/personRestController/saveData","/personRestController/signin").permitAll().requestMatchers("/personRestController/getAllData").hasRole("ADMIN").anyRequest().authenticated())
                .cors(a -> a.disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .addFilterBefore(new JWTTokenVerify(), BasicAuthenticationFilter.class)
               /* making Sesssion stateless
                why ? we dont need Session id for mentaining session
                We use JWT token instead of session id for verification
                */
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
