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
                       // .requestMatchers("/").authenticated() //PERMISOS  QUE TENDRAN LOS ROLES
                       /* .requestMatchers("/login").permitAll()
                        .requestMatchers("/css/**","/js/**").permitAll()
                        .requestMatchers("/usuarios/**").hasAnyRole("Dueña","Administrador")
                        .requestMatchers("/productos/**").hasAnyRole("Dueña","Administrador")
                        .requestMatchers("/ventas/**").hasAnyRole("Dueña","Vendedor", "Administrador")
                        .requestMatchers("/socio/**").hasAnyRole("Dueña","Socio")

                        .anyRequest().authenticated()*/
                    //    .authorizeHttpRequests(auth -> auth


                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/css/**","/js/**","/img/**").permitAll()

//dashboard
                                .requestMatchers("/dashboard/**")
                                .hasAnyRole("Duena","Administrador","Vendedor")

//usuarios
                                .requestMatchers("/usuarios/**")
                                .hasAnyRole("Duena","Administrador")

//productos
                                .requestMatchers("/productos/**")
                                .hasAnyRole("Duena","Administrador","Vendedor")

                                .requestMatchers("/categorias/**")
                                .hasAnyRole("Duena","Administrador")

                                .requestMatchers("/unidades/**")
                                .hasAnyRole("Duena","Administrador")

                                .requestMatchers("/vencimientos/**")
                                .hasAnyRole("Duena","Administrador","Vendedor")

                                .requestMatchers("/historial-precios/**")
                                .hasAnyRole("Duena","Administrador")

//comoras
                               .requestMatchers("/compras/**")
                                .hasAnyRole("Duena","Administrador")

                                .requestMatchers("/proveedores/**")
                                .hasAnyRole("Duena","Administrador")

//pedidos
                                .requestMatchers("/pedidos/generar")
                                .hasAnyRole("Duena","Administrador","Vendedor")

                                .requestMatchers("/pedidos/aprobar/**")
                                .hasRole("Duena")

                                .requestMatchers("/pedidos/rechazar/**")
                                .hasRole("Duena")

                                .requestMatchers("/pedidos/**")
                                .hasAnyRole("Duena","Administrador","Vendedor")

//ventas
                                .requestMatchers("/ventas/**")
                                .hasAnyRole("Duena","Administrador","Vendedor")

                                .requestMatchers("/ventas/historial/**")
                                .hasAnyRole("Duena","Administrador","Vendedor")
                                .requestMatchers("/ventas/mas-vendidos/**")
                                .hasAnyRole("Duena","Administrador","Vendedor")

//cjas
                                .requestMatchers("/caja/**")
                                .hasAnyRole("Duena","Administrador")

                                .requestMatchers("/reportes/**")
                                .hasAnyRole("Duena","Administrador")

///socios
                                .requestMatchers("/socio/**")
                                .hasAnyRole("Duena","Socio")

//para socios
                                .anyRequest().authenticated()

                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard",true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}