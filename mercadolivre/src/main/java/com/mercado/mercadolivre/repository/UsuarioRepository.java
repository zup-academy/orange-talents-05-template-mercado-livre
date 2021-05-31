package com.mercado.mercadolivre.repository;

import java.util.Optional;

import com.mercado.mercadolivre.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
