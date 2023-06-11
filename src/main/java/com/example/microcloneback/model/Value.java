package com.example.microcloneback.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Value {
    private Double timestamp;
    private String type;
    private String category;
    private String level;
    private String message;
}
