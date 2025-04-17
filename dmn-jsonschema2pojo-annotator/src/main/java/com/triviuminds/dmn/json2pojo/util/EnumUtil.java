package com.triviuminds.dmn.json2pojo.util;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class EnumUtil {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<String> getEnumValues(String enumClassName) {
        return getEnumValues(getEnum(enumClassName));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Class<? extends Enum> getEnum(String enumClassName) {
        try {
            Class<?> enumClass = Class.forName(enumClassName);
            if (!Enum.class.isAssignableFrom(enumClass)) {
                throw new IllegalArgumentException("Class is not an enum: " + enumClassName);
            }
            return (Class<? extends Enum>) enumClass;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Enum class not found: " + enumClassName, e);
        }
    }

    @SuppressWarnings({"unchecked"})
    public static <E extends Enum<E>> E getEnumByValue(String enumClazzName, String value) {
        Class<E> enumClazz = (Class<E>) getEnum(enumClazzName);
        for (E constant : enumClazz.getEnumConstants()) {
            if (constant.name().equalsIgnoreCase(value)) {
                return constant;
            }
        }
        throw new IllegalArgumentException("No matching enum constant for value: " + value);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static List<String> getEnumValues(Class<? extends Enum> enumClass) {
        return (List<String>) EnumSet.allOf(enumClass).stream().map(Object::toString).collect(Collectors.toList());
    }
}