package me.rayll.mercadolivreorangetalent.validador.categorianome;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NomeUnicoCategoriaValidador.class})
public @interface NomeUnicoCategoria {
	String message() default "Foi encontrado um registro já salvo!";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}