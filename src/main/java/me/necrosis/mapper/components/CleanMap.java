package me.necrosis.mapper.components;

import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;

import java.util.Map;

public abstract class CleanMap<T,D> extends PropertyMap<T,D> {

    @SuppressWarnings("rawtypes")
    private Map<Class<? extends AbstractConverter>, AbstractConverter> convertersMap;

    /**
     * Set convertersMap.
     */
    @SuppressWarnings("rawtypes")
    private void setConverters(Map<Class<? extends AbstractConverter>, AbstractConverter> map){
        this.convertersMap = map;
    }

    /**
     * This method retrieves a converter from the converters map based on the provided class.
     *
     * @param clazz The class of the converter to retrieve.
     * @return The converter associated with the provided class.
     * @throws RuntimeException if the converters map is null.
     */
    @SuppressWarnings("unchecked")
    public <T extends AbstractConverter> T getConverter(Class<T> clazz){
        if (this.convertersMap == null){
            throw new RuntimeException("Converters map is null!");
        }
        return ((T)this.convertersMap.get(clazz));
    }

}
