package com.lloydsbank.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lloydsbank.api.domain.model.AtmDetail;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtmDetailResponse extends BaseResponse {
    @JsonProperty
    AtmDetail atmDetail;

    @Builder
    public AtmDetailResponse(AtmDetail atmDetail, String message) {
        super(message);
        this.atmDetail = atmDetail;
        this.message = message;

    }
}
