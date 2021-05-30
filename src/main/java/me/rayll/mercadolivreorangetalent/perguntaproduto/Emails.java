package me.rayll.mercadolivreorangetalent.perguntaproduto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Emails {

	@Autowired
	private Mailer mailer;
	
	public void novaPergunta(@NotNull @Valid PerguntaProduto pergunta) {
		
		mailer.send("<html>...</html>", "Nova Pergunta...", pergunta.getPerguntador().getEmail(), "novapergunta@nossoml.com", pergunta.getDono().getEmail());
	}
	
}
