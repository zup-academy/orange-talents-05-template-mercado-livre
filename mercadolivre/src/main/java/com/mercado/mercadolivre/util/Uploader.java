package com.mercado.mercadolivre.util;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {

    public Set<String> envia(List<MultipartFile> imagens);
}
