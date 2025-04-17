# DMN JSON Schema to POJO Annotator

A custom annotator for integrating Decision Model and Notation (DMN) with JSON Schema to POJO conversion. This project extends the jsonschema2pojo library to provide additional validation and DMN-specific functionality.

## Features

- Custom validation annotations for JSON Schema properties
- DMN integration support with FEEL expression handling
- BigDecimal validation with scale and pattern support
- String pattern validation with customizable pattern providers
- Enum value validation for both single values and lists

## Requirements

- Java 17 or higher
- Gradle 8.x or higher

## Usage

### Basic Configuration

Add the following to your build.gradle:

```gradle
dependencies {
    implementation 'com.triviuminds.dmn.json2pojo:dmn-jsonschema2pojo-annotator:1.0.0'
}
```

### DMN Support

To enable DMN support, set the following system property:
```bash
-DdmnSupport=true
```

This will:
- Add the FEELPropertyAccessible interface to generated POJO classes
- Implement fromMap and toMap methods for FEEL expression handling
- Add @FEELProperty annotations to fields containing FEEL expressions

### Debug Mode

To enable detailed logging during the annotation process:
```bash
-DdebugAnnotator=true
```

### Custom Pattern Provider

To use a custom pattern provider for string validation:
```bash
-DcustomPatternProvider=your.package.StringPatternType
```
Note: customPatternProvider should be an Enum which implements com.triviuminds.dmn.json2pojo.util.PatternProvider

## Validation Annotations

### BigDecimal Validation
- `@BigDecimalValidation`: Validates scale of BigDecimal values
- `@BigDecimalPatternValidation`: Validates BigDecimal values against a pattern

### String Validation
- `@StringPatternValidation`: Validates string values against patterns

### Enum Validation
- `@EnumValueValidation`: Validates single enum values
- `@EnumListValueValidation`: Validates lists of enum values

## Building

```bash
./gradlew build
```

## License

[Add your license information here]
