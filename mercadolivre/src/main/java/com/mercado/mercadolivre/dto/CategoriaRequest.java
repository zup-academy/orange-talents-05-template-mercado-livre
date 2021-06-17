package com.mercado.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import com.mercado.mercadolivre.entity.Categoria;
import com.mercado.mercadolivre.validate.UniqueValue;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(entidade = Categoria.class, atributo = "nome", message= "O nome da categoria precisa ser unico")
    private String nome;

    private Long categoria_id;


    public CategoriaRequest(String nome, Long categoria_id) {
        this.nome = nome;
        this.categoria_id = categoria_id;
    }

    public Categoria convertToCategoria(EntityManager manager) {
        Categoria categoria = new Categoria(this.nome);

        if (this.categoria_id != null) {
            Categoria categoriaMae = manager.find(Categoria.class, this.categoria_id);

            categoria.setCategoria(categoriaMae);
        }

        return categoria;
    }
}
