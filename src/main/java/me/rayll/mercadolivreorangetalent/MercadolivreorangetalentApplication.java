package me.rayll.mercadolivreorangetalent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import feign.Feign;
import feign.RequestLine;
import me.rayll.mercadolivreorangetalent.encriptador.Encriptador;

@SpringBootApplication
@EnableJpaRepositories
public class MercadolivreorangetalentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadolivreorangetalentApplication.class, args);
		
	}

}
