package com.example.microcloneback.model.contexts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contexts {
    private Browser browser;
    private Os os;
    private Trace trace;
}
