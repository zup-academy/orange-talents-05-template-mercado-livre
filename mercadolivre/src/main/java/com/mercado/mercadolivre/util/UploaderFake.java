package com.mercado.mercadolivre.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploaderFake {

    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream()
                .map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename())
                .collect(Collectors.toSet());
        // return imagens.stream().map(imagem -> "http://bucket.io/"+
        // imagem.getOriginalFileName() + "-" +
        // UUID.randomUUID().toString()).collect(Collectors.toSet());
    }
}
