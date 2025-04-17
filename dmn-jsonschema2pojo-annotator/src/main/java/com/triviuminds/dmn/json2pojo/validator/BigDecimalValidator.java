package com.triviuminds.dmn.json2pojo.validator;

import com.triviuminds.dmn.json2pojo.annotation.BigDecimalValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class BigDecimalValidator implements ConstraintValidator<BigDecimalValidation, BigDecimal> {
    private int scale;

    @Override
    public void initialize(BigDecimalValidation constraintAnnotation) {
        scale = constraintAnnotation.scale();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;    
        }
        return value.scale() <= scale;
    }
    
}
