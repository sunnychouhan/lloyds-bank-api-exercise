
package com.lloydsbank.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AtmDetail {

    @JsonProperty("Identification")
    private String identification;
    @JsonProperty("Location")
    private Location location;
    @JsonProperty("SupportedCurrencies")
    private List<String> supportedCurrencies;
}
