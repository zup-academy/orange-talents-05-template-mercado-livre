package com.mercado.mercadolivre.dto;

import com.mercado.mercadolivre.entity.ImagemProduto;

public class ImagemResponse {

    private String link;

    public ImagemResponse(ImagemProduto imagem) {
        this.link = imagem.getLink();
    }

    public String getLink() {
        return this.link;
    }

}
