package com.triviuminds.dmn.json2pojo.util;

import com.triviuminds.dmn.json2pojo.enums.StringPatternType;

public  interface PatternProvider {
    public String getPattern();
    public String getExample();
    public static PatternProvider lookUp(String patternProviderEnum, String value) {
        try {
            return (PatternProvider) EnumUtil.getEnumByValue(patternProviderEnum, value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No matching PatternProvider for value: " + value);
        }
    }
}
