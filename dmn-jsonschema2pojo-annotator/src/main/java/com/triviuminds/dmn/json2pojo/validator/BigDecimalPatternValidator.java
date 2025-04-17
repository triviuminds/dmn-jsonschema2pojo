package com.triviuminds.dmn.json2pojo.validator;

import com.triviuminds.dmn.json2pojo.annotation.BigDecimalPatternValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class BigDecimalPatternValidator implements ConstraintValidator<BigDecimalPatternValidation, BigDecimal> {
    private String pattern;

    @Override
    public void initialize(BigDecimalPatternValidation constraintAnnotation) {
        pattern = constraintAnnotation.pattern();
    }   

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }   
        return value.toString().matches(pattern);
    }
}
