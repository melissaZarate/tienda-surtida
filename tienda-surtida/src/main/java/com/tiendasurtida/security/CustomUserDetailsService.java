//cuando alguien intenta iniciar sesion, buscar al usuri en la bse de datos
package com.tiendasurtida.security;

import com.tiendasurtida.entity.Usuario;
import com.tiendasurtida.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)

            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository
                .findByUsernameUsuario(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getUsernameUsuario())
                .password(usuario.getPasswordUsuario())
                .disabled(!usuario.getEstadoUsuario())
                .roles(usuario.getRol().getNombreRol())

                .build();


    }

}