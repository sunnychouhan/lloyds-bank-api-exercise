package com.lloydsbank.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lloydsbank.api.domain.model.ApiAtmResponse;
import com.lloydsbank.api.domain.model.AtmData;
import com.lloydsbank.api.domain.model.AtmDetail;
import com.lloydsbank.api.domain.model.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ApiServiceTest {
    ApiService apiService;

    ApiServiceTest() {
        apiService = new ApiService();
    }

    static Stream<Arguments> atmDataSet() {
        return Stream.of(
                Arguments.of((Object) new Object[]{getAtmDetail("12345"), getAtmDataList("12345"), Boolean.TRUE}),
                Arguments.of((Object) new Object[]{getAtmDetail("11111"), getAtmDataList("12345"), Boolean.FALSE}),
                Arguments.of((Object) new Object[]{getAtmDetail("11111"), getAtmDataList(null), Boolean.FALSE}),
                Arguments.of((Object) new Object[]{getAtmDetail("12345"), getAtmDataList(""), Boolean.FALSE})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "atmDataSet")
    void testFindAndGetAtmDetail(Object[] atmData) {
        AtmDetail atmDetail = (AtmDetail) atmData[0];
        ApiAtmResponse apiAtmResponse = (ApiAtmResponse) atmData[1];
        Boolean expected = (Boolean) atmData[2];

        AtmDetail andGetAtmDetail = apiService.findAndGetAtmDetail(atmDetail.getIdentification(), apiAtmResponse);
        if (expected)
            Assertions.assertSame(atmDetail.getIdentification(), andGetAtmDetail.getIdentification());
        else
            Assertions.assertNotSame(atmDetail.getIdentification(), andGetAtmDetail.getIdentification());
    }

    private static Object getAtmDataList(String Id) {
        List<AtmDetail> atmDetails = Arrays.asList(getAtmDetail(Id));
        List<Brand> brands = Arrays.asList(
                Brand.builder()
                        .atm(atmDetails)
                        .build());
        List<AtmData> atmData = Arrays.asList(AtmData.builder()
                .brand(brands)
                .build());
        return ApiAtmResponse.builder().data(atmData).build();
    }

    private static AtmDetail getAtmDetail(String Id) {
        return AtmDetail.builder()
                .identification(Id)
                .build();
    }
}