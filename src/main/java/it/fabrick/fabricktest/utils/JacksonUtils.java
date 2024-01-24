package it.fabrick.fabricktest.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.fabrick.fabricktest.rest.model.accountbalance.response.AccountBalance;
import it.fabrick.fabricktest.rest.model.response.FabrickApiResponse;

public class JacksonUtils {

    private JacksonUtils(){}


    public static JsonNode getRootTree(String jsonString) throws JsonProcessingException {
        return ObjectMapperFactory.getObjectMapper().readTree(jsonString);
    }

    public static ArrayNode putArrayTo(ObjectNode n, String name) {
        return n.putArray(name);
    }

    public static String writeAsString(Object o) throws JsonProcessingException {
        return ObjectMapperFactory.getObjectMapper().writeValueAsString(o);
    }

    public static String writeAsStringNotNullValue(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.writeValueAsString(o);
    }

    public static String writePrettyAsString(Object o) throws JsonProcessingException {
        return ObjectMapperFactory.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }

    public static <T> T deepCopy(T toClone, Class<T> retType) throws JsonProcessingException {
        return ObjectMapperFactory.getObjectMapper().readValue(
                ObjectMapperFactory.getObjectMapper().writeValueAsString(toClone),
                retType);
    }

    public static <I, O> O castDeepCopy(I toClone, Class<O> retType) throws JsonProcessingException {
        return ObjectMapperFactory.getObjectMapper().readValue(
                ObjectMapperFactory.getObjectMapper().writeValueAsString(toClone),
                retType);
    }

    public static <O> O deserializeJsonAs(String jsonString, Class<O> retType) throws JsonProcessingException {
        return ObjectMapperFactory.getObjectMapper().readValue(jsonString, retType);
    }

    public static JavaType getJavaType(Class<?> parametrizedClass, Class<?>... parameterClasses) {
        return ObjectMapperFactory.getObjectMapper().getTypeFactory().constructParametricType(parametrizedClass, parameterClasses);
    }

    public static <O> O deserializeJsonAs(String jsonString, JavaType javaType) throws JsonProcessingException {
        return ObjectMapperFactory.getObjectMapper().readValue(jsonString, javaType);
    }
}
