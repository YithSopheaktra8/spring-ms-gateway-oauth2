package com.example.gatewayserver.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.OrServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        final var clientRoutes = Stream.of("/oauth2/**", "/login/**")
                .map(PathPatternParserServerWebExchangeMatcher::new)
                .map(ServerWebExchangeMatcher.class::cast)
                .toList();

        http.securityMatcher(new OrServerWebExchangeMatcher(clientRoutes));


        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/oauth2/**", "/login/**").permitAll()
                        .pathMatchers("/cards-service/api/v1/cards/public").permitAll()
                        .anyExchange().permitAll()
                )
//                .oauth2Client(Customizer.withDefaults());
                .oauth2Login(Customizer.withDefaults());

        http.cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*", "http://localhost:3000")); //allows React to access the API from
        // origin
        // on port
        // 3000.
        // Change accordingly
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
