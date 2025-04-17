package com.triviuminds.dmn.json2pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JClass;
import com.triviuminds.dmn.json2pojo.annotation.*;
import com.triviuminds.dmn.json2pojo.enums.StringPatternType;
import com.triviuminds.dmn.json2pojo.util.PatternProvider;
import org.jsonschema2pojo.AbstractAnnotator;

import java.util.Map;

/**
 * Custom annotator for DMN (Decision Model and Notation) integration with JSON Schema to POJO conversion.
 * 
 * When dmnSupport is enabled (set to "true"), this annotator:
 * - Adds the FEELPropertyAccessible interface to the generated POJO class
 * - Implements fromMap and toMap methods for FEEL expression handling
 * - Adds the @FEELProperty annotation to fields containing FEEL expressions
 * Note: This annotator requires the 'org.kie:kie-dmn-api' dependency to be added to the project.
 * 
 * When debugAnnotator is enabled (set to "true"), detailed logging messages
 * will be printed to the console during the annotation process.
 */
public class CustomAnnotator extends AbstractAnnotator {

    private static final String DMN_SUPPORT = "dmnSupport";
    private static final String DEBUG_ANNOTATOR = "debugAnnotator";
    private static final String ENUM_PROPERTY = "enumClass";

    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        super.propertyOrder(clazz, propertyNode);
      
        boolean isList = false;
        if(propertyNode.get("items") != null)   {
            propertyNode = propertyNode.get("items");
            isList = true;
        }
  
        if (propertyNode.get(ENUM_PROPERTY) != null) {
            log("enum: %s", field.type().fullName());
            if(isList) {
                field.annotate(EnumListValueValidation.class).param(ENUM_PROPERTY, propertyNode.get(ENUM_PROPERTY).asText());
                log("added @%s(enumClass=%s) annotation to property %s in class %s", EnumListValueValidation.class.getSimpleName(), propertyNode.get(ENUM_PROPERTY).asText(), field.name(), clazz.fullName());
            }
            else {
                field.annotate(EnumValueValidation.class).param(ENUM_PROPERTY, propertyNode.get(ENUM_PROPERTY).asText());
                log("added @%s(enumClass=%s) annotation to property %s in class %s", EnumValueValidation.class.getSimpleName(), propertyNode.get(ENUM_PROPERTY).asText(), field.name(), clazz.fullName());
            }
        } else {
            if("BigDecimal".equals(field.type().name())) {
                if(propertyNode.get("multipleOf") != null) {
                    String[] parts = propertyNode.get("multipleOf").asText().split("\\.");
                    if(parts.length == 2) {
                        field.annotate(BigDecimalValidation.class).param("scale", parts[1].length());
                        log("added @%s(scale=%s) annotation to property %s in class %s", BigDecimalValidation.class.getSimpleName(), parts[1].length(), field.name(), clazz.fullName());
                    }
                } else if(propertyNode.get("pattern") != null) {
                    String pattern = propertyNode.get("pattern").asText();
                    field.annotate(BigDecimalPatternValidation.class).param("pattern", pattern);
                    String example = "";
                    if(propertyNode.get("example") != null) {
                        propertyNode.get("example").asText();
                        field.annotate(BigDecimalPatternValidation.class).param("example", example);
                    }
                    log("added @%s(pattern=%s, example=%s) annotation to property %s in class %s", BigDecimalPatternValidation.class.getSimpleName(), pattern, example, field.name(), clazz.fullName());
                }
            }
            if("String".equals(field.type().name())) {
                if(propertyNode.get("patternType") != null) {
                    String patternType = propertyNode.get("patternType").asText();
                    String patternProviderEnum = System.getProperty("customPatternProvider", "com.triviuminds.dmn.json2pojo.enums.StringPatternType");
                    if(patternType == null || patternType.isEmpty()) {
                        log("customPatternProvider not provided, using com.triviuminds.dmn.json2pojo.enums.StringPatternType as default");
                        patternProviderEnum = System.getProperty("customPatternProvider", "com.triviuminds.dmn.json2pojo.enums.StringPatternType");
                    }
                    PatternProvider patternProvider = PatternProvider.lookUp(patternProviderEnum, patternType);
                    field.annotate(StringPatternValidation.class).param("pattern", patternProvider.getPattern()).param("example", patternProvider.getExample());
                    log("added @%s(pattern=%s) annotation to property %s in class %s", StringPatternValidation.class.getSimpleName(), patternType, field.name(), clazz.fullName());
                }
            }
        }
    }

    @Override
    public void propertyOrder(JDefinedClass clazz, JsonNode propertyNode) {
        log("class: %s", clazz.fullName());
        if(System.getProperty(DMN_SUPPORT) != null && System.getProperty(DMN_SUPPORT).equals("true")) {
            JClass dmnImplementationClazz = clazz.owner().ref("org.kie.dmn.api.core.FEELPropertyAccessible");
            clazz._implements(dmnImplementationClazz);

            JCodeModel codeModel = new JCodeModel();
            JMethod fromMapMethod = clazz.method(JMod.PUBLIC, clazz, "fromMap");
            fromMapMethod.param(Map.of(String.class, Object.class).getClass(), "values");
            Map<String, JFieldVar> fields = clazz.fields();
            for (Map.Entry<String, JFieldVar> entry : fields.entrySet()) {
                log("field: %s type: %s", entry.getKey(), entry.getValue().type().fullName());
            }
        }  
    }

    private void log(String format, Object... args) {
        if(System.getProperty(DEBUG_ANNOTATOR) != null && System.getProperty(DEBUG_ANNOTATOR).equals("true")) {
            System.out.println(String.format(format, args));
        }
    }
}
