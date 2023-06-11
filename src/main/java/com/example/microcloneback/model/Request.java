package com.example.microcloneback.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Request {
    private String url;
    private Map<String, String> headers; //стоял List<List<String>>, пока что выставлю Map<String, String>
}
