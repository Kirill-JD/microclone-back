package com.example.microcloneback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Frame {
    private String function;
    private String module;
    private String filename;
    @JsonProperty("abs_path")
    private String absPath;
    private Long lineno;
    private Long colno;
    @JsonProperty("in_app")
    private Boolean inApp;
}
