package br.costa.AghataEscada.config;

import br.costa.AghataEscada.security.service.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.security.autoconfigure.actuate.web.reactive.EndpointRequest;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer:: disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/employer/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/employer/{id}" ).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/v1/employer" ).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT).permitAll()
                        .requestMatchers(HttpMethod.PATCH).permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/product/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/product").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/product/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/v1/product/{empId}/product/{prodId}").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/v1/product/{empId}/product/{prodId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/manager").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/manager/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/v1/manager/{id}").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("""
                   {
                    "status" : 401,
                    "error" : "Unauthorized",
                    "message" : "User not allowed to access this resource"
                   }
                   """);
        };

    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write("""
                    {
                    "status" : 403,
                    "error" : "Forbidden",
                    "message" : "User dont have permission to access this resource"
                   }
                   """);
        };
    }

}
