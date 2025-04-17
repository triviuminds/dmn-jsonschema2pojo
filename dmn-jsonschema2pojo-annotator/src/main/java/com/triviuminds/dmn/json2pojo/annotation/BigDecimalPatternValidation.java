package com.triviuminds.dmn.json2pojo.annotation;

import com.triviuminds.dmn.json2pojo.validator.BigDecimalPatternValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validates that a string matches a decimal format.
 * 
 * Common patterns:
 * - "#,##0.00" - Standard decimal with thousands separator and 2 decimal places (e.g., 1,234.56)
 * - "0.00" - Decimal with 2 decimal places, no thousands separator (e.g., 1234.56)
 * - "#,##0.000" - Decimal with thousands separator and 3 decimal places (e.g., 1,234.567)
 * - "0.000" - Decimal with 3 decimal places, no thousands separator (e.g., 1234.567)
 * - "#,##0" - Integer with thousands separator (e.g., 1,234)
 * - "0" - Integer without thousands separator (e.g., 1234)
 * - "#,##0.0" - Decimal with thousands separator and 1 decimal place (e.g., 1,234.5)
 * - "0.0" - Decimal with 1 decimal place, no thousands separator (e.g., 1234.5)
 * 
 * By default, the pattern is "#,##0.00", eg 1,234.56 or 1234.56 or 0.00
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = BigDecimalPatternValidator.class)
public @interface BigDecimalPatternValidation {
    String pattern() default "#,##0.00";
    String example() default "1,234.56 or 1234.56 or 0.00";
    String message() default "must be a decimal number with the pattern {pattern}, for example {example}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
