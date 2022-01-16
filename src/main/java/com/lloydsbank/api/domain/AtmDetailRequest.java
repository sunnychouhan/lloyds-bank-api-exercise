package com.lloydsbank.api.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtmDetailRequest {
    @NotBlank
    String url;
    @NotBlank
    String identifier;
}
