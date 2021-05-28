package me.rayll.mercadolivreorangetalent.perguntaproduto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import me.rayll.mercadolivreorangetalent.usuario.Usuario;

public interface Mailer {

	/**
	 * 
	 * @param body corpo do email
	 * @param subject assunto do email
	 * @param nameFrom nome para aparecer no provedor de email
	 *  @param from email de origem
	 * @param to email de destino
	 */
	void send(@NotEmpty String body, @NotEmpty String subject, @NotEmpty String nameFrom, @NotEmpty @Email String from, @NotEmpty @Email String to);

}
