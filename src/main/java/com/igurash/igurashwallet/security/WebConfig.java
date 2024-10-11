/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.security;

import com.igurash.igurashwallet.service.IgurashUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author User
 */
@Configuration
@EnableWebSecurity
public class WebConfig {
      @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private IgurashUserDetailService userDetailsService;
      private static final String[] SECURED_USERURLs = {"/iguras/walletuser/**"};
     private static final String[] SECURED_USERADMINURLs = {"/iguras/editgunasho/{id}","/iguras/getallgunasho"};
     private static final String[] UN_SECURED_URLs = {
            
            
        "/iguras/authenticate/**",
      
    };
      private static final String[] SECURED_URLs = {"/iguras/admin/**"};
   
      @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
       @Bean
    public AuthenticationProvider authenticationProvider(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService( userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
    
       return authenticationProvider;
    }
    
    @Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        

                http.securityMatcher("/**")
				.authorizeHttpRequests(user->user.requestMatchers(UN_SECURED_URLs).permitAll()
                        .requestMatchers(SECURED_USERURLs).
                                 hasAnyAuthority("wallet_user","wallet_merchant").requestMatchers(SECURED_USERADMINURLs).
                                     hasAnyAuthority("wallet_admin","wallet_user").
                                       
                                requestMatchers(SECURED_URLs).hasAuthority("wallet_admin").anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable()).formLogin(form->form.disable())
                        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider()).addFilterBefore( authenticationFilter, UsernamePasswordAuthenticationFilter.class);
				
				
                
        
        return http.build(); 
    }
}
