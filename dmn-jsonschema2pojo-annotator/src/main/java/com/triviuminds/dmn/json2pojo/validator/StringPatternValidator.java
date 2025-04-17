package com.triviuminds.dmn.json2pojo.validator;

import com.triviuminds.dmn.json2pojo.annotation.StringPatternValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringPatternValidator implements ConstraintValidator<StringPatternValidation, String> {
    private String pattern;

    @Override
    public void initialize(StringPatternValidation constraintAnnotation) {
        pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.matches(pattern);
    }
}
