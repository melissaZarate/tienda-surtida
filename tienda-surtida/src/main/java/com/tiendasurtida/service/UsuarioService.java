
package com.tiendasurtida.service;

import com.tiendasurtida.entity.Usuario;
import com.tiendasurtida.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.Set;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsernameUsuario(username);
    }

    // listar usuario
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    //eliminar usuario eliminacion logica
    public void eliminarUsuario(Long id){
        Usuario usuario=buscarPorId(id);
        usuario.setEstadoUsuario(false);
        usuarioRepository.save(usuario);
    }

    // guardar suario
    public Usuario guardarUsuario(Usuario usuario) {
        if(usuario.getIdUsuario()!=null){
            Usuario existente=usuarioRepository.findById(usuario.getIdUsuario()).orElseThrow(()->new RuntimeException("usuario no encontrado"));
            existente.setUsernameUsuario(usuario.getUsernameUsuario());
            //aqui si vinene contraseña nueva lo va a encriptar
            if(usuario.getPasswordUsuario()!=null && !usuario.getPasswordUsuario().isEmpty()){
                existente.setPasswordUsuario(passwordEncoder.encode(usuario.getPasswordUsuario()));
            }
            existente.setRol(usuario.getRol());
            existente.setEstadoUsuario(usuario.getEstadoUsuario());
            return usuarioRepository.save(existente);
        }
        //crear nuevo usuario
        usuario.setPasswordUsuario(passwordEncoder.encode(usuario.getPasswordUsuario()));
        usuario.setEstadoUsuario(true);
        return usuarioRepository.save(usuario);
    }
    //buscar por id
    public Usuario buscarPorId(long id){
        return usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("usuario no encontrado"));
    }

    // activary desactivar
    public void cambiarEstado(Long id, boolean estado) {
        Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if(!estado && usuario.getRol().getNombreRol().equalsIgnoreCase("Dueña")){
            throw new RuntimeException("No se puede desactivar a la dueña");
        }

        usuario.setEstadoUsuario(estado);
        usuarioRepository.save(usuario);
    }
}