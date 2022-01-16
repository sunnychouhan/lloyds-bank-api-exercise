
package com.lloydsbank.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta {

    @JsonProperty("Agreement")
    private String agreement;
    @JsonProperty("LastUpdated")
    private String lastUpdated;
    @JsonProperty("License")
    private String license;
    @JsonProperty("TermsOfUse")
    private String termsOfUse;
    @JsonProperty("TotalResults")
    private Long totalResults;
}
