package com.lloydsbank.api.domain.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class JsonDeserializationTest {

    ObjectMapper objectMapper;

    JsonDeserializationTest() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testApiAtmResponseDeserialization() {

        ApiAtmResponse result = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("testdata.json").getFile());
            result = objectMapper.readValue(file, ApiAtmResponse.class);
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

}