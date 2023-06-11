package com.example.microcloneback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @JsonProperty("ip_address")
    private String ipAddress;
    private Geo geo;
}
