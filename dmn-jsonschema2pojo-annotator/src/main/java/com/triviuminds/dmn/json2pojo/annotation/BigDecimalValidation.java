package com.triviuminds.dmn.json2pojo.annotation;

import com.triviuminds.dmn.json2pojo.validator.BigDecimalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validates that a string matches a decimal format.
 * by default, the scale is 2, eg 1,234.56 or 1234.56 or 0.00
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = BigDecimalValidator.class)
public @interface BigDecimalValidation {
    int scale() default 2;
    String example() default "1,234.56 or 1234.56 or 0.00";
    String message() default "must be a decimal number with {scale} decimal places, for example {example}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
