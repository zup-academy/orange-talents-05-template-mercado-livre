package com.mercado.mercadolivre.util;

public interface Mailer {

    /**
     * 
     * @param Body corpo email
     * @param assunto assunto titulo
     * @param nameFrom remetento
     * @param nameTo destinatario
     */
    void send(String body, String assunto, String nameFrom, String nameTo);  

}
