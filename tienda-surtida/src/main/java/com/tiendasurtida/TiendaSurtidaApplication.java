package com.tiendasurtida;

import com.tiendasurtida.entity.Usuario;
import com.tiendasurtida.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TiendaSurtidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaSurtidaApplication.class, args);
	}


}
