package com.mercado.mercadolivre.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { UniqueValueValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface UniqueValue {

    String message() default "{javax.validation.constraints.UniqueValue.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

    Class<?> entidade();

    String atributo();
}
