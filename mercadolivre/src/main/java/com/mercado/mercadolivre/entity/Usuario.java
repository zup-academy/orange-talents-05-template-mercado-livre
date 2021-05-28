package com.mercado.mercadolivre.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String userName;
    @NotNull
    private String passwordEncoder;
    @NotNull @PastOrPresent
    private LocalDateTime registro = LocalDateTime.now();

    public Usuario(@NotBlank @Email String userName, String bcryptGenerator) {
        this.userName = userName;
        this.passwordEncoder = bcryptGenerator;
    }

    @Deprecated
    public Usuario() {}

    public void setRegistro(LocalDateTime registro) {
        this.registro = registro;
    }

}
