package com.example.microcloneback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Geo {
    @JsonProperty("country_code")
    private String countryCode;
    private String region;
}
