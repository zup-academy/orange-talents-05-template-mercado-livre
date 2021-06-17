package com.mercado.mercadolivre.repository;

import com.mercado.mercadolivre.entity.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
