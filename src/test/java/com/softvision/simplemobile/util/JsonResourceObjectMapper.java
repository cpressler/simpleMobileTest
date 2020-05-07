package com.softvision.simplemobile.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonResourceObjectMapper<T> {
    private Class<T> model;

    public JsonResourceObjectMapper(Class<T> model) {
        this.model = model;
    }

    public T loadTestJson(String fileName) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream= classLoader.getResourceAsStream(fileName);
        return new ObjectMapper().readValue(inputStream, this.model);
    }

    public Map<String, Object> loadJsonMap (String fileName) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream= classLoader.getResourceAsStream(fileName);
        Map<String, Object> jsonMap = new ObjectMapper().readValue(inputStream,
                new TypeReference<Map<String,Object>>(){});
        return jsonMap;
    }
}