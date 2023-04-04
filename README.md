# Spring ModelMapper Extension

This is a Maven Java project that extends the functionality of the Spring ModelMapper library. 📚

## Features

- Easy integration with Spring Boot applications
- Customizable mapping configurations
- Support for complex object mapping
- Automatic registration of converters and property mappers using the reflections.org library

## Getting Started

To use this extension in your project, add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>org.reflections</groupId>
    <artifactId>reflections</artifactId>
    <version>latest</version>
    <!-- This is only important if you are using different logger -->
    <!-- In Spring, you must add this exclusions section! -->
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>latest</version>
</dependency>
<dependency>
    <groupId>me.necrosis.mapper</groupId>
    <artifactId>CleanMapper</artifactId>
    <version>0.1.1</version>
</dependency>
```

## Usage
Here’s an example of how to use this extension in your code:

```java
@Bean
public ModelMapper modelMapper() {
    // Register converters and property mappers using the reflections.org library
    this.mapper = new ModelMapper();
    ModelMapperInjector.registerMappers(
        mapper,                         //  ModelMapper
        "me.necrosis.mapper.mappers",   //  PropertyMappers package path
        "me.necrosis.mapper.converters" //  Converters package path
    );
    return mapper;
}
```

### Mapping

This class `CreateDTOToPersonMapper` extends the `CleanMap` class and is used to map a `PersonCreateDTO` object to a `Person` object. The `configure` method is overridden to specify the mapping behavior.

```java
public class CreateDTOToPersonMapper extends CleanMap<PersonCreateDTO, Person> {

    @Override
    protected void configure() {
        // Get an instance of the UpperCaseConverter class
        UpperCaseConverter upperCaseConverter = this.getConverter(UpperCaseConverter.class);
        // Use the UpperCaseConverter to map the name from the source object to the username of the destination object
        this.using(upperCaseConverter).map(source.getName(),destination.getUsername());
    }

}
```

### Converter

This class `UpperCaseConverter` extends the `AbstractConverter` class and is used to convert a `String` object to its uppercase representation. The `convert` method is overridden to specify the conversion behavior.

`@GlobalConverter` is a global behavior of the `ModelMapper`. In this example, the `UpperCaseConverter` will map every string to its uppercase representation. Without this annotation, the only way you can reach the converter is through the `CleanMap.getConverter(ConverterClass.class)`.
```java
@GlobalConverter // This annotation will register the converter to ModelMapper
public class UpperCaseConverter extends AbstractConverter<String, String> {
    @Override
    protected String convert(String source) {
        // Convert the input string to its uppercase representation
        return source.toUpperCase();
    }
}
```

See the tests for more examples

## Clean Architecture
This extension follows the principles of clean architecture by separating concerns and promoting modularity. By using this extension, you can keep your code organized and maintainable, making it easier to develop and test your application. 🧹

## Contributing
Contributions are welcome! Please feel free to submit a pull request or open an issue. 🤝