package com.lloydsbank.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lloydsbank.api.domain.AtmDetailRequest;
import com.lloydsbank.api.domain.AtmDetailResponse;
import com.lloydsbank.api.domain.model.ApiAtmResponse;
import com.lloydsbank.api.domain.model.AtmDetail;
import com.lloydsbank.api.service.ApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiService apiService;


    @Test
    public void whenServiceMocked_shouldReturnSuccessResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        AtmDetailResponse atmDetailResponse =
                objectMapper.readValue(jsonFromResource("atmDetailResponse.json"), AtmDetailResponse.class);
        when(apiService.fetchAtmDetail(anyString(), anyString()))
                .thenReturn(atmDetailResponse);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/atmdetail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getContent(objectMapper)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.atmDetail").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.atmDetail.Identification").value("LFFFBC11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.atmDetail.Location.PostalAddress.Country").value("GB"));

        verify(apiService).fetchAtmDetail(anyString(), anyString());
    }

    private String getContent(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsString(AtmDetailRequest.builder().identifier("testId").url("testurl").build());
    }

    public File jsonFromResource(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        return file;
    }
}