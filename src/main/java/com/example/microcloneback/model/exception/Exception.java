package com.example.microcloneback.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Exception {
    private List<ValueException> values;
}
