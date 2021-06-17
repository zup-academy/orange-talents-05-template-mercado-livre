package com.mercado.mercadolivre.util;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeMailer implements Mailer {

    @Override
    public void send(String body, String assunto, String nameFrom, String nameTo) {
        System.out.println(body);
        System.out.println(assunto);
        System.out.println(nameFrom);
        System.out.println(nameTo);   
    }
    
}
