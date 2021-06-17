package com.mercado.mercadolivre.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotBlank
    @Column(nullable = false, unique = true)
	private String nome;


    public Perfil() {
    }
	

    public Perfil(String nome) {
        this.nome = nome;
    }

	@Override
	public String getAuthority() {
		return nome;
	}
	
}