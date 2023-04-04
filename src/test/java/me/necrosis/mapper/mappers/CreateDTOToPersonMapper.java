package me.necrosis.mapper.mappers;

import me.necrosis.mapper.components.CleanMap;
import me.necrosis.mapper.converters.UpperCaseConverter;
import me.necrosis.mapper.dtos.PersonCreateDTO;
import me.necrosis.mapper.entities.Person;
import java.util.UUID;

public class CreateDTOToPersonMapper extends CleanMap<PersonCreateDTO, Person> {

    @Override
    protected void configure() {
        UpperCaseConverter upperCaseConverter = this.getConverter(UpperCaseConverter.class);
        this.map(UUID.randomUUID(),destination.getUuid());
        this.using(upperCaseConverter).map(source.getName(),destination.getUsername());
    }

}
