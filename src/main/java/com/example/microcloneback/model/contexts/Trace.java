package com.example.microcloneback.model.contexts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trace {
    @JsonProperty("trace_id")
    private String traceId;
    @JsonProperty("span_id")
    private String spanId;
    private String op;
    private String status;
    private String type;
}
