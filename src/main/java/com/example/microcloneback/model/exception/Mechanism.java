package com.example.microcloneback.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mechanism {
    private String type;
    private Boolean handled;
}
