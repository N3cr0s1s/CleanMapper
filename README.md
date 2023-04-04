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
    <groupId>me.necrosis.mapper</groupId>
    <artifactId>CleanMapper</artifactId>
    <version>0.1.0</version>
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

## Clean Architecture
This extension follows the principles of clean architecture by separating concerns and promoting modularity. By using this extension, you can keep your code organized and maintainable, making it easier to develop and test your application. 🧹

## Contributing
Contributions are welcome! Please feel free to submit a pull request or open an issue. 🤝