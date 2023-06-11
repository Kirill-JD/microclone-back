package com.example.microcloneback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Metrics {
    @JsonProperty("bytes.ingested.event")
    private Long bytesIngestedEvent;
    @JsonProperty("bytes.stored.event")
    private Long bytesStoredEvent;
    @JsonProperty("flag.processing.error")
    private Boolean flagProcessingError;
}
