
package com.tiendasurtida.service;

import com.tiendasurtida.entity.Usuario;
import com.tiendasurtida.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsernameUsuario(username);
    }
}
