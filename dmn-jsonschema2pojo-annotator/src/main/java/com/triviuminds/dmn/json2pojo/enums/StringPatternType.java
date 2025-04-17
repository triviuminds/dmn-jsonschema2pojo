package com.triviuminds.dmn.json2pojo.enums;

import com.triviuminds.dmn.json2pojo.util.PatternProvider;

/**
 * Enum containing predefined string format patterns and examples.
 */
public enum StringPatternType  implements PatternProvider {
    ALPHANUMERIC("^[a-zA-Z0-9]+$", "alphanumeric: abc123 or 123abc"),
    EMAIL("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", "email: john.doe@example.com"),
    PHONE("^\\d{10}$", "phone: 1234567890"),
    ZIP_CODE("^\\d{5}(-\\d{4})?$", "zip code: 12345 or 12345-6789"),
    BANK_ACCOUNT("^\\d{8,17}$", "bank account: 1234567890123456"),
    SSN("^\\d{3}-\\d{2}-\\d{4}$", "ssn: 123-45-6789"),
    URL("^https?://(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$", "url: https://www.example.com or http://example.com"),
    IP_ADDRESS("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$", "ip address: 192.168.1.1"),
    UUID("^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$", "uuid: 123e4567-e89b-12d3-a456-426614174000");

    private final String pattern;
    private final String example;

    StringPatternType(String pattern, String example) {
        this.pattern = pattern;
        this.example = example;
    }

    public String getPattern() {
        return pattern;
    }

    public String getExample() {
        return example;
    }

    public  PatternProvider byValue(String value) {
        for (StringPatternType type : StringPatternType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching StringPatternType for value: " + value);
    }

    public String toString() {
        return pattern + ", for example " + example;
    }
} 