package demoapp.newjwtspringsecurity.security.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import demoapp.newjwtspringsecurity.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session ->
                session.sessionCreationPolicy(STATELESS))
            .addFilterBefore(
                jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
            )
            .authenticationProvider(authenticationProvider)
            .authorizeHttpRequests(req ->
                req.requestMatchers(
                        "/error",
                        "/api/v1/auth/**",
                        "/api/v1/demo-controller/sayEmail",
                        "/api/v1/**")
                    .permitAll()
//                    .requestMatchers("/api/v1/**")
//                    .authenticated()
            );

        return http.build();
    }
}
