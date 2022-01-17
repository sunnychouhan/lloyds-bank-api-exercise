package com.lloydsbank.api.domain.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

class JsonDeserializationTest {

    final ObjectMapper objectMapper;

    JsonDeserializationTest() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testApiAtmResponseDeserialization() {

        ApiAtmResponse result = null;
        try {
            result = objectMapper.readValue(jsonFromResource("testdata.json"), ApiAtmResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ApiAtmResponse apiAtmResponse = result;
        Assertions.assertAll(
                () -> Assertions.assertNotNull(apiAtmResponse),
                () -> Assertions.assertNotNull(apiAtmResponse.getData()),
                () -> Assertions.assertNotNull(apiAtmResponse.getMeta()),
                () -> Assertions.assertNotNull(apiAtmResponse.getMeta().getAgreement()),
                () -> Assertions.assertNotNull(apiAtmResponse.getData().size()),
                () -> Assertions.assertNotNull(apiAtmResponse.getData())
        );
    }

    public File jsonFromResource(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        return file;
    }

}