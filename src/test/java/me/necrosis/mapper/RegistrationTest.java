package me.necrosis.mapper;

import me.necrosis.mapper.dtos.PersonCreateDTO;
import me.necrosis.mapper.entities.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static me.necrosis.mapper.ModelMapperInjector.registerConverter;
import static me.necrosis.mapper.ModelMapperInjector.registerMappers;

public class RegistrationTest {

    private ModelMapper mapper;

    public RegistrationTest(){
        this.mapper = new ModelMapper();
        try {
            registerMappers(mapper, "me.necrosis.mapper.mappers", "me.necrosis.mapper.converters");
        }catch (Exception  e){
            e.printStackTrace();
        }
    }

    @Test
    public void shouldTwoConverterRegisteredTest(){
        int registeredConverter = registerConverter(mapper,"me.necrosis.mapper.converters").size();
        Assertions.assertEquals(2,registeredConverter);
    }

    @Test
    public void isMapperWorking(){
        PersonCreateDTO dto = new PersonCreateDTO("alex","necrosis@gmail.com");
        Person person = this.mapper.map(dto, Person.class);
        Assertions.assertEquals("ALEX",person.getUsername());
        Assertions.assertNotNull(person.getUuid());
    }
}
