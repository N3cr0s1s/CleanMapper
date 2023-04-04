package me.necrosis.mapper.mappers;

import me.necrosis.mapper.converters.UpperCaseConverter;
import me.necrosis.mapper.dtos.PersonCreateDTO;
import me.necrosis.mapper.entities.Person;
import org.modelmapper.AbstractConverter;
import org.modelmapper.PropertyMap;

import java.util.Map;
import java.util.UUID;

public class CreateDTOToPersonMapper extends PropertyMap<PersonCreateDTO, Person> {

    private Map<Class<? extends AbstractConverter>, AbstractConverter> map;

    public CreateDTOToPersonMapper(Map<Class<? extends AbstractConverter>, AbstractConverter> map){
        this.map = map;
    }

    @Override
    protected void configure() {
        this.map(UUID.randomUUID(),destination.getUuid());
        this.using(this.map.get(UpperCaseConverter.class)).map(source.getName(),destination.getUsername());
    }

}
