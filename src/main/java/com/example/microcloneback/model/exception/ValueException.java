package com.example.microcloneback.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValueException {
    private String type;
    private String value;
    private Stacktrace stacktrace;
    private Mechanism mechanism;
}
