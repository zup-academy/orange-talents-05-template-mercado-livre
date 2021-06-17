package com.mercado.mercadolivre.util;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.mercado.mercadolivre.entity.Pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Emails {
    // private String url = "https://mandrillapp.com/api/messages/send.json"; //implementacao com sender real

    @Autowired
    private Mailer mailer;

    public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
        mailer.send("<html>...</html>", pergunta.getTitulo(), pergunta.getUsuario().getEmail(), pergunta.getOwnerProduto().getEmail());
    }

}
