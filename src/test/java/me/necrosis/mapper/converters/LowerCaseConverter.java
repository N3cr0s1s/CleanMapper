package me.necrosis.mapper.converters;

import org.modelmapper.AbstractConverter;

public class LowerCaseConverter extends AbstractConverter<String, String> {

    @Override
    protected String convert(String s) {
        return s.toLowerCase();
    }
}
