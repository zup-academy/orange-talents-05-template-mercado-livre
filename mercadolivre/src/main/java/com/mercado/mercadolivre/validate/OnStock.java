package com.mercado.mercadolivre.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;

@Documented
@Constraint(validatedBy = { OnStockValidator.class })
@Target({ FIELD, TYPE })
@Retention(RUNTIME)
public @interface OnStock {

    String message() default "{javax.validation.constraints.OnStock.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
