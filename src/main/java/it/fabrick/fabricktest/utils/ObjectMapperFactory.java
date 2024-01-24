package it.fabrick.fabricktest.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {

    private static final ObjectMapper SIMPLE_MAPPER;

    static {
        SIMPLE_MAPPER = new ObjectMapper();
        SIMPLE_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private ObjectMapperFactory() {}

    public static ObjectMapper getObjectMapper() {
        return SIMPLE_MAPPER;
    }

}
