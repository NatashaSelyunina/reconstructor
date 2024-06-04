package reConstructor.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import reConstructor.security.access_check.CustomAccessDeniedHandler;
import reConstructor.security.access_check.CustomAuthenticationEntryPoint;
import reConstructor.security.jwt.services.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    private final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(
                        manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(x -> x
                        .requestMatchers(new AntPathRequestMatcher(AUTH_WHITELIST[0])).permitAll()
                        .requestMatchers(new AntPathRequestMatcher(AUTH_WHITELIST[1])).permitAll()
                        .requestMatchers(new AntPathRequestMatcher(AUTH_WHITELIST[2])).permitAll()
                        .requestMatchers(new AntPathRequestMatcher(AUTH_WHITELIST[3])).permitAll()
                        .requestMatchers(new AntPathRequestMatcher(AUTH_WHITELIST[4])).permitAll()
                        .requestMatchers("/api/auth/login", "/api/auth/logout", "/api/moderator/registration",
                                "/api/moderator/activate", "/api/moderator/reset-password")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/menu/**")
                        .permitAll()
                        .requestMatchers("/api/moderator", "/api/moderator/**", "/api/restaurant",
                                "/api/restaurant/**", "/api/generation").hasAuthority("ROLE_MODERATOR")
                        .requestMatchers("/api/dish", "/api/dish/**", "/api/image", "/api/image/**",
                                "/api/menu/**", "/api/table/**", "/api/staff", "/api/staff/**")
                        .hasAnyAuthority("ROLE_MODERATOR", "ROLE_ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/restaurant/{id}").hasAuthority("ROLE_ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/table/all/{restaurant-id}", "/api/table/{id}",
                                "/api/dish/{id}", "/api/menu/{restaurant-id}")
                        .hasAnyAuthority("ROLE_WAITER", "ROLE_CHEF", "ROLE_BARTENDER")
                        .requestMatchers(HttpMethod.PUT, "/api/staff/password/{id}")
                        .hasAnyAuthority("ROLE_WAITER", "ROLE_CHEF", "ROLE_BARTENDER")
                        .requestMatchers(new AntPathRequestMatcher("picture/**")).permitAll()
                        .anyRequest().authenticated())
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.defaultAuthenticationEntryPointFor(new CustomAuthenticationEntryPoint(),
                        new AntPathRequestMatcher("/**"))
                        .accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }
}
