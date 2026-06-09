/*¿Quién puede entrar?
¿Quién debe inicciar sesión?
¿Qué páginas son públicas?
¿Qué páginas requieren ro DUEÑA?
¿Qué páginas requieren rol ADMINISTRADR*/

package com.tiendasurtida.config;

import com.tiendasurtida.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                       // .requestMatchers("/").authenticated()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/css/**","/js/**").permitAll()
                        .requestMatchers("/duena/**").hasRole("Dueña")
                        .requestMatchers("/admin/**").hasAnyRole("Dueña","Administrador")
                        .requestMatchers("/ventas/**").hasAnyRole("Dueña","Vendedor", "Administrador")
                        .requestMatchers("/socio/**").hasAnyRole("Dueña","Socio")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/",true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}