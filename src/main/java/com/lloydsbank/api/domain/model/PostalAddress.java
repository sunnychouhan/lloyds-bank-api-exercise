
package com.lloydsbank.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddress {

    @JsonProperty("AddressLine")
    private List<String> addressLine;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("CountrySubDivision")
    private List<String> countrySubDivision;
    @JsonProperty("PostCode")
    private String postCode;
    @JsonProperty("StreetName")
    private String streetName;
    @JsonProperty("TownName")
    private String townName;
}
