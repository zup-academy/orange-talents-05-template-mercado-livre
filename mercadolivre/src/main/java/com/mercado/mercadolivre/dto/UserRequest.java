package com.mercado.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.mercado.mercadolivre.entity.Usuario;
import com.mercado.mercadolivre.validate.UniqueValue;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserRequest {

    @NotBlank
    @Email
    @UniqueValue(entidade = Usuario.class, atributo = "email", message = "Ja existe um usuario cadastrado com esse endereço de email")
    private String userName;

    @NotBlank
    @Size(min = 6)
    private String password;

    public UserRequest(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String BcryptGenerator(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(senha);
    }

    public Usuario convertToUsuario() {
        return new Usuario(this.userName, BcryptGenerator(this.password));
    }
}
