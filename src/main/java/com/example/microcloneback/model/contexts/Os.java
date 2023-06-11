package com.example.microcloneback.model.contexts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Os {
    private String name;
    private String version;
    private String type;
}
