package com.oauth2.oauthstudy.security;

import com.oauth2.oauthstudy.security.jwt.CorsConfig;
import com.oauth2.oauthstudy.security.jwt.CustomOauth2UserService;
import com.oauth2.oauthstudy.security.jwt.OAuth2AuthenticationFailureHandler;
import com.oauth2.oauthstudy.security.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.OAuth2AuthorizationFailureHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    CorsConfig corsConfig;

    @Autowired
    OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    @Autowired
    OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeRequests(authRequest -> authRequest.antMatchers("/login").permitAll()
                                                             .antMatchers("/user").permitAll());
        http
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest)
                .permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(corsConfig.corsFilter())
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .oauth2Login()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler)
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return http.build();
    }

//    @Bean
//    public GrantedAuthoritiesMapper customAuthoritiesMapper() {
//        return new CustomAuthoritiesMapper();
//    }
}
