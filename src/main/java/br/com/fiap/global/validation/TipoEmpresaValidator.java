package br.com.fiap.global.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoEmpresaValidator implements ConstraintValidator <TipoEmpresa, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.equals("HORTIFRUTI") ||
                value.equals("DESCARTAVEIS") ||
                value.equals("LIMPEZA") ||
                value.equals("ESTOCAVEIS") ||
                value.equals("INFORMATICA") ||
                value.equals("EPIS") ||
                value.equals("PADARIA") ||
                value.equals("PERECÍVEIS") ||
                value.equals("BEBIDAS") ||
                value.equals("CLIENTE")

        );
    }
}
