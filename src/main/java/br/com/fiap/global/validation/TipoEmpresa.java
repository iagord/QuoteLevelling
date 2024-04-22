package br.com.fiap.global.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoEmpresaValidator.class)
public @interface TipoEmpresa {

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    public String message() default "As únicas opções de Tipo de Empresa (letras maiúsculas) aceitas no momento são: " +
            "HORTIFRUTI, " +
            "DESCARTAVEIS, " +
            "LIMPEZA, " +
            "ESTOCAVEIS, " +
            "INFORMATICA, " +
            "EPIS, " +
            "PADARIA, " +
            "PERECÍVEIS, " +
            "BEBIDAS, " +
            "CLIENTE";
}
