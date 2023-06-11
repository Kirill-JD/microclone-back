package com.example.microcloneback.model.contexts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Browser {
    private String name;
    private String version;
    private String type;
}
