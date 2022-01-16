package com.lloydsbank.api.service;

import com.lloydsbank.api.domain.AtmDetailResponse;
import com.lloydsbank.api.domain.model.ApiAtmResponse;
import com.lloydsbank.api.domain.model.AtmData;
import com.lloydsbank.api.domain.model.AtmDetail;
import com.lloydsbank.api.domain.model.Brand;
import com.lloydsbank.api.exception.ApiAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

@Service
@Slf4j
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService( ) {
        this.restTemplate = new RestTemplate();
    }


    public AtmDetailResponse fetchAtmDetail(String url, String id) {
        AtmDetail atmDetail = null;
        try {
            ResponseEntity<ApiAtmResponse> apiResponse = restTemplate.getForEntity(url, ApiAtmResponse.class);
            if (apiResponse.getStatusCode().is2xxSuccessful()) {
                ApiAtmResponse body = apiResponse.getBody();
                atmDetail = findAndGetAtmDetail(id, Objects.requireNonNull(body));
            }

        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new ApiAccessException(exception.getMessage());
        }
        if (Objects.requireNonNull(atmDetail).getIdentification() != null)
            return AtmDetailResponse.builder().atmDetail(atmDetail).build();
        else
            return AtmDetailResponse.builder().message("No Data Found for Identifier : " + id).build();
    }


    public AtmDetail findAndGetAtmDetail(String id, ApiAtmResponse apiResponse) {
        List<AtmData> dataList = apiResponse.getData();
        if (nonNull(id) && !CollectionUtils.isEmpty(dataList) && isAtmIdPresent(id, dataList)) {
            return dataList.stream()
                    .filter(Objects::nonNull)
                    .map(atmData -> {
                        List<Brand> brand = atmData.getBrand();
                        if (!CollectionUtils.isEmpty(brand)) {
                            return brand.stream()
                                    .filter(Objects::nonNull)
                                    .map(brd -> getMatchAtmId(id, brd))
                                    .findFirst()
                                    .orElseGet(this::buildEmptyAtmDetail);
                        } else {
                            return buildEmptyAtmDetail();
                        }
                    }).findFirst()
                    .orElseGet(this::buildEmptyAtmDetail);

        } else return buildEmptyAtmDetail();
    }

    private AtmDetail getMatchAtmId(String id, Brand brand) {
        List<AtmDetail> atm = brand.getAtm();
        if (!CollectionUtils.isEmpty(atm)) {
            return atm.stream()
                    .filter(atmDetail -> nonNull(atmDetail) && equalsIgnoreCase(atmDetail.getIdentification(), id))
                    .findFirst()
                    .orElseGet(this::buildEmptyAtmDetail);
        } else buildEmptyAtmDetail();
        return null;
    }

    private AtmDetail buildEmptyAtmDetail() {
        return AtmDetail.builder().build();
    }

    private Boolean isAtmIdPresent(String id, List<AtmData> atmDataList) {
        return atmDataList.stream()
                .filter(Objects::nonNull)
                .anyMatch(atmData ->
                        atmData.getBrand().stream()
                                .filter(Objects::nonNull)
                                .anyMatch(brand -> {
                                    List<AtmDetail> atm = brand.getAtm();
                                    if (!CollectionUtils.isEmpty(atm)) {
                                        return atm.stream()
                                                .anyMatch(atmDetail -> nonNull(atmDetail)
                                                        && equalsIgnoreCase(atmDetail.getIdentification(), id));
                                    } else return false;
                                })
                );
    }
}
