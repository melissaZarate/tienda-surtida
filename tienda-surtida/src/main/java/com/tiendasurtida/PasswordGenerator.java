package com.tiendasurtida;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        String password = encoder.encode("123456");
        //para añadir mas contraseñs solosigue este formato  lo imprimes, lo quete rulstees hasheado ylo copias en la bd

        System.out.println(password);

    }
}
