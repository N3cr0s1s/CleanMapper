package me.necrosis.mapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ModelMapperInjector {

    @SuppressWarnings("rawtypes")
    public static Map<Class<? extends AbstractConverter>, AbstractConverter> registerConverter(ModelMapper mapper, String pathToClasses) {
        try {
            Reflections reflections = new Reflections(pathToClasses);
            Set<Class<? extends AbstractConverter>> converters = reflections.getSubTypesOf(AbstractConverter.class);
            Map<Class<? extends AbstractConverter>, AbstractConverter> converterMap = new HashMap<>();
            for (Class<? extends AbstractConverter> converterClass : converters) {
                AbstractConverter converter = converterClass.getDeclaredConstructor().newInstance();
                mapper.addConverter(converter);
                converterMap.put(converter.getClass(), converter);
            }

            if (converters.size() < 1){
                System.err.println("No converter registered, is path to converters good?");
            }

            return converterMap;
        } catch (Exception e) {
            throw new RuntimeException("Could not register converters", e);
        }
    }

    @SuppressWarnings("rawtypes")
    public static void registerMappers(ModelMapper mapper, String pathToClasses, String pathToConverters) {
        try {
            Map<Class<? extends AbstractConverter>, AbstractConverter> converterMap = registerConverter(mapper, pathToConverters);
            Reflections reflections = new Reflections(pathToClasses);
            Set<Class<? extends PropertyMap>> mappers = reflections.getSubTypesOf(PropertyMap.class);
            for (Class<? extends PropertyMap> mapperClass : mappers) {
                PropertyMap mapperInstance;
                try {
                    mapperInstance = mapperClass.getDeclaredConstructor(java.util.Map.class).newInstance(converterMap);
                } catch (NoSuchMethodException ignored) {
                    mapperInstance = mapperClass.getDeclaredConstructor().newInstance();
                }
                mapper.addMappings(mapperInstance);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not register mappers", e);
        }
    }

}
