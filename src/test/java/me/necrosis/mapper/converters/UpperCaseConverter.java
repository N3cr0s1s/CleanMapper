package me.necrosis.mapper.converters;

import org.modelmapper.AbstractConverter;

public class UpperCaseConverter extends AbstractConverter<String,String> {
    @Override
    protected String convert(String s) {
        return s.toUpperCase();
    }
}
