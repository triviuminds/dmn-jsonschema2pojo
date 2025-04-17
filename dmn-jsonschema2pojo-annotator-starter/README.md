# DMN JSON Schema to POJO Annotator Starter

This project demonstrates the capabilities of the DMN JSON Schema to POJO Annotator library. It showcases how to generate Java POJOs from JSON Schema definitions with DMN-specific annotations and validations.

## Overview

The starter project provides a working example of how to:
- Generate Java POJOs from JSON Schema definitions
- Apply DMN-specific annotations and validations
- Handle custom patterns and validations for different data types

## Features

### JSON Schema to POJO Generation
- Automatic generation of Java classes from JSON Schema definitions
- Support for complex data types and nested objects
- Generation of getters, setters, and builder methods
- Support for Jakarta Validation annotations

### DMN-Specific Features
- Custom pattern validation for numeric fields
- Special handling for middle name fields with patternType
- Integration with DMN data types
- Support for DMN-specific annotations

### Validation Support
- Jakarta Validation API integration
- Custom validation patterns
- Support for required fields
- Pattern-based validation for strings

## Setup and Configuration

### Prerequisites
- Java 17 or higher
- Gradle 10.x or higher

### Dependencies
The project uses the following key dependencies:
- jsonschema2pojo-gradle-plugin (1.2.2)
- jackson-annotations (2.17.1)
- jakarta.validation-api (3.1.1)
- dmn-jsonschema2pojo-annotator (1.0.0)

### Configuration
The project is configured in `build.gradle` with the following key settings:
```gradle
jsonSchema2Pojo {
    useJakartaValidation = true
    generateBuilders = true
    source = files("${sourceSets.main.resources.srcDirs[0]}/schemas/")
    targetPackage = 'com.triviuminds.example'
    customAnnotator = 'com.triviuminds.dmn.json2pojo.CustomAnnotator'
    // ... other configurations
}
```

## Usage

1. Place your JSON Schema files in the `src/main/resources/schemas` directory
2. Run the Gradle build:
   ```bash
   ./gradlew build
   ```
3. Generated POJOs will be available in the `build/generated/src/main/java` directory

## Schema Examples

### Exception Schema
The project includes an example Exception schema that demonstrates:
- Enum type handling
- Nested object definitions
- Array type properties
- Custom type mappings

### Loan Model Schema
The loan model schema showcases:
- Complex data type handling
- Pattern validations
- Required field specifications
- Custom type mappings

## Custom Annotations

The project uses a custom annotator (`CustomAnnotator`) to add DMN-specific annotations to the generated POJOs. This includes:
- Pattern validations
- Custom type mappings
- DMN-specific metadata

## Contributing

Feel free to submit issues and enhancement requests.

## License

This project is licensed under the terms of your organization's license. 