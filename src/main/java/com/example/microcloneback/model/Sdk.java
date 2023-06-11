package com.example.microcloneback.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Sdk {
    private String name;
    private String version;
    private List<String> integrations;
    private List<Package> packages;
}
