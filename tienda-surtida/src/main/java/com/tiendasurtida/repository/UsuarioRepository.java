package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsernameUsuario(String usernameUsuario);

}