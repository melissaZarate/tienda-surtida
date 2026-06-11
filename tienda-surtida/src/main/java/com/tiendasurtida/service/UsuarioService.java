
package com.tiendasurtida.service;

import com.tiendasurtida.entity.Usuario;
import com.tiendasurtida.entity.rol;
import com.tiendasurtida.repository.UsuarioRepository;
import com.tiendasurtida.repository.RolRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.Set;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
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

        //  Validar rol recibido
        if (usuario.getRol() == null || usuario.getRol().getIdRol() == null) {
            throw new RuntimeException("Debe seleccionar un rol");
        }

        //  Buscar rol REAL en BD
        rol rolBD = rolRepository.findById(usuario.getRol().getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no existe"));

        //  Bloqueo de rol Dueña (CORRECTO)
        if ("Dueña".equalsIgnoreCase(rolBD.getNombreRol())) {
            throw new RuntimeException("No se puede asignar el rol Dueña");
        }

        //  EDITAR usuario
        if (usuario.getIdUsuario() != null) {

            Usuario existente = usuarioRepository.findById(usuario.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("usuario no encontrado"));

            existente.setUsernameUsuario(usuario.getUsernameUsuario());
            existente.setNombreUsuario(usuario.getNombreUsuario()); //añadido recien
            existente.setApellidoUsuario(usuario.getApellidoUsuario());//añadidorecien
            existente.setTelefonoUsuario(usuario.getTelefonoUsuario()); //añadidorecien
            System.out.println("nombre "+usuario.getNombreUsuario());
            System.out.println("apellido "+usuario.getApellidoUsuario());

            if (usuario.getPasswordUsuario() != null &&
                    !usuario.getPasswordUsuario().isEmpty()) {

                existente.setPasswordUsuario(passwordEncoder.encode(usuario.getPasswordUsuario()));
            }

            existente.setRol(rolBD);
            existente.setEstadoUsuario(usuario.getEstadoUsuario());

            return usuarioRepository.save(existente);
        }

        //  CREAR usuario //se recomienda nunca hacer existenmte.setFechaCreacionUsuario(usuario.getFechaCreacionUsuario()) esto rmperia el sistema
        usuario.setRol(rolBD);
        usuario.setFechaCreacionUsuario(LocalDateTime.now());
        usuario.setPasswordUsuario(passwordEncoder.encode(usuario.getPasswordUsuario()));
        usuario.setEstadoUsuario(true);

        return usuarioRepository.save(usuario);
    }
    //buscar por id
    public Usuario buscarPorId(long id){
        return usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("usuario no encontrado"));
    }

    // activary desactivar
    public boolean cambiarEstado(Long id) {

        Usuario usuario = buscarPorId(id);

        if (usuario.getRol() != null &&
                usuario.getRol().getNombreRol().equalsIgnoreCase("Dueña") &&
                !usuario.getEstadoUsuario()) {

            throw new RuntimeException("No se puede desactivar a la dueña");
        }

        usuario.setEstadoUsuario(!usuario.getEstadoUsuario());
        usuarioRepository.save(usuario);

        return usuario.getEstadoUsuario();
    }
}