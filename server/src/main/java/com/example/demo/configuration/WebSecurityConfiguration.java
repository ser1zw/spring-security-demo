package com.example.demo.configuration;

import com.example.demo.service.DemoUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.Serializable;
import java.io.Writer;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/api/free", "/h2-console/**").permitAll()
                .anyRequest().authenticated();
        httpSecurity.formLogin()
                .loginPage("/api/login")
                .successHandler(getSuccessHandler())
                .failureHandler(getFailureHandler())
                .permitAll();
        httpSecurity.logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(getLogoutSuccessHandler())
                .permitAll();

        httpSecurity.csrf().disable();
        httpSecurity.cors().configurationSource(corsConfigurationSource());

        // For H2 Console
        httpSecurity.headers().frameOptions().disable();
    }

    private AuthenticationSuccessHandler getSuccessHandler() {
        return (request, response, authentication) -> {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            Map<String, Serializable> responseMap = Map.of(
                    "username", authentication.getName(),
                    "roles", authentication.getAuthorities().stream().map(a -> a.getAuthority()).toArray()
            );

            try (Writer writer = response.getWriter()) {
                writer.write(objectMapper.writeValueAsString(responseMap));
            }
        };
    }

    private AuthenticationFailureHandler getFailureHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        };
    }

    private LogoutSuccessHandler getLogoutSuccessHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpStatus.OK.value());
        };
    }

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        DemoUserDetailsService demoUserDetailsService;

        @Override
        public void init(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder.userDetailsService(demoUserDetailsService)
                    .passwordEncoder(passwordEncoder);
        }
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedOriginPattern(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return urlBasedCorsConfigurationSource;
    }
}
