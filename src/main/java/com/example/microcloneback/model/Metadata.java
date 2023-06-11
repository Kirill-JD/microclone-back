package com.example.microcloneback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Metadata {
    @JsonProperty("display_title_with_tree_label")
    private Boolean displayTitleWithTreeLabel;
    private String filename;
    private String function;
    private String type;
    private String value;
}
