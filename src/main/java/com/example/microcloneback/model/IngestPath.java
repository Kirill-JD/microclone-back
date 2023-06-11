package com.example.microcloneback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngestPath {
    private String version;
    @JsonProperty("public_key")
    private String publicKey;
}
