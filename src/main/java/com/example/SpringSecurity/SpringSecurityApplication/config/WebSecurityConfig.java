package com.example.SpringSecurity.SpringSecurityApplication.config;

import com.example.SpringSecurity.SpringSecurityApplication.filter.JwtAuthFilter;
import com.example.SpringSecurity.SpringSecurityApplication.handler.Oauth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.SpringSecurity.SpringSecurityApplication.entities.enums.Permission.POST_CREATE;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final Oauth2SuccessHandler oauth2SuccessHandler;

    public static final String[] publicRoutes = {
             "/error", "/auth/**", "/home.html"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicRoutes).permitAll()
                        .requestMatchers(HttpMethod.GET,"/posts/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/posts/**")
                            .hasAnyAuthority(POST_CREATE.name())
                        .anyRequest().authenticated())
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2config -> oauth2config
                        .failureUrl("/login?error=true")
                        .successHandler(oauth2SuccessHandler)
                );
//                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    UserDetailsService myInMemoryUserDetailsService(){
//
//        UserDetails normalUser = User
//                .withUsername("Taksh")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//
//        UserDetails adminUser = User
//                .withUsername("Admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }


}
