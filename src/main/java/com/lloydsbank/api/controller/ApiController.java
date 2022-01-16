package com.lloydsbank.api.controller;

import com.lloydsbank.api.domain.AtmDetailResponse;
import com.lloydsbank.api.domain.AtmDetailRequest;
import com.lloydsbank.api.service.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@RestController
@RequestMapping("/api")
@Slf4j
@Tag(name = "Atm Detail Api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }


    @Operation(summary = "Retrieve Atm Detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AtmDetailResponse.class))))})
    @PostMapping(value = "/atmdetail", consumes = {"application/json"}, produces = {"application/json"})
    public @ResponseBody
    AtmDetailResponse getAtmDetail(@Valid @RequestBody AtmDetailRequest atmDetailRequest) {
        log.info("Request Received : {}", atmDetailRequest);
        return apiService.fetchAtmDetail(atmDetailRequest.getUrl(), atmDetailRequest.getIdentifier());
    }
}
