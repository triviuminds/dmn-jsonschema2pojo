package com.triviuminds.dmn.json2pojo.annotation;

import com.triviuminds.dmn.json2pojo.enums.StringPatternType;
import com.triviuminds.dmn.json2pojo.validator.StringPatternValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validates that a string matches a specific format.
 * 
 * You can use predefined formats from the StringFormatType enum or provide a custom pattern.
 * 
 * Predefined formats:
 * - ALPHANUMERIC: ^[a-zA-Z0-9]+$, example: abc123 or 123abc
 * - EMAIL: ^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$, example: john.doe@example.com
 * - PHONE: ^\\d{10}$, example: 1234567890
 * - ZIP_CODE: ^\\d{5}(-\\d{4})?$, example: 12345 or 12345-6789
 * - BANK_ACCOUNT: ^\\d{8,17}$, example: 1234567890123456
 * - SSN: ^\\d{3}-\\d{2}-\\d{4}$, example: 123-45-6789
 * - URL: ^https?://(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$, example: https://www.example.com or http://example.com
 * - IP_ADDRESS: ^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$, example: 192.168.1.1
 * - UUID: ^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$, example: 123e4567-e89b-12d3-a456-426614174000
 *   
 * by default, the pattern is ALPHANUMERIC: "^[a-zA-Z0-9]+$", eg abc123 or 123abc
 */ 
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = StringPatternValidator.class)
public @interface StringPatternValidation {
    String pattern();
    String example() default "";
    String message() default "must be a valid string in the format {patternType}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
