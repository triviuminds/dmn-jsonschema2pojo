package com.triviuminds.dmn.json2pojo.validator;

import com.triviuminds.dmn.json2pojo.annotation.EnumListValueValidation;
import com.triviuminds.dmn.json2pojo.util.EnumUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.MessageFormat;
import java.util.List;

public class EnumListValueValidator implements ConstraintValidator<EnumListValueValidation, String> {

    private List<String> acceptedValues;
    private boolean validateEnum = true;

    @Override
    public void initialize(EnumListValueValidation constraintAnnotation) {
        String enumClassName = constraintAnnotation.enumClass();
        try {
            acceptedValues = EnumUtil.getEnumValues(enumClassName);
        } catch (IllegalArgumentException e) {
            System.out.println(MessageFormat.format("Not able to valudate enum [@EnumListValueValidation(enumClass = {0})]. Enum not found.", enumClassName));
            validateEnum = false;
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || !validateEnum) {
            return true;
        }
        return acceptedValues.contains(value);
    }
}
