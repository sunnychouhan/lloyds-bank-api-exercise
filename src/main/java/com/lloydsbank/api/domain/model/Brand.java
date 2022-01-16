
package com.lloydsbank.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {

    @JsonProperty("ATM")
    private List<AtmDetail> atm;

    @JsonProperty("BrandName")
    private String brandName;
}
