package com.mercado.mercadolivre.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginFrom {

    private String email;
    private String senha;


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

}
