package be.thomasmore.germantrio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            @Value("${security.h2-console-needed:true}") boolean h2ConsoleNeeded) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    if (h2ConsoleNeeded) {
                        auth.requestMatchers("/h2-console/**").permitAll();
                    }

                    auth.requestMatchers(HttpMethod.GET,
                                "/",
                                "/index",
                                "/brands/**",
                                "/carmodels/**",
                                "/compare",
                                "/contact",
                                "/about",
                                "/login",
                                "/register",
                                "/styles.css",
                                "/foto/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/contact",
                                "/register"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/favorites/**",
                                "/carmodels/*/comments",
                                "/carmodels/*/comments/*/replies"
                        ).authenticated()
                        .requestMatchers(
                                "/profile/**",
                                "/favorites/**",
                                "/notifications/**"
                        ).authenticated()
                        .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        if (h2ConsoleNeeded) {
            http
                    .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                    .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
        }

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
