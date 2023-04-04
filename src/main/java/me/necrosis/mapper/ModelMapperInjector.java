package me.necrosis.mapper;

import me.necrosis.mapper.annotations.GlobalConverter;
import me.necrosis.mapper.components.CleanMap;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Arrays;
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

                if (converterClass.isAnnotationPresent(GlobalConverter.class)){
                    mapper.addConverter(converter);
                }

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
            Set<Class<? extends CleanMap>> mappers = reflections.getSubTypesOf(CleanMap.class);
            for (Class<? extends CleanMap> mapperClass : mappers) {
                CleanMap mapperInstance = mapperClass.getDeclaredConstructor().newInstance();

                Method method = mapperClass.getSuperclass().getDeclaredMethod("setConverters", Map.class);
                method.setAccessible(true);
                method.invoke(mapperInstance,converterMap);

                mapper.addMappings(mapperInstance);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not register mappers", e);
        }
    }

}
