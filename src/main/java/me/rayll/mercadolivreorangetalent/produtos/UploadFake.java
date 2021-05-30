package me.rayll.mercadolivreorangetalent.produtos;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploadFake implements Uploader{
	/**
	 * @param imagens
	 * @return links para imagens que foram uploadadas
	 */
	
	public Set<String> envia(List<MultipartFile> imagens){
		return imagens.stream().map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename() + "-" + UUID.randomUUID().toString()).collect(Collectors.toSet());
	}
}
