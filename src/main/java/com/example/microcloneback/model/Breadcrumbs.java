package com.example.microcloneback.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Breadcrumbs {
    private List<Value> values;
}
