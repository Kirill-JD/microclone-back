package com.example.microcloneback.model.exception;

import com.example.microcloneback.model.Frame;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Stacktrace {
    private List<Frame> frames;
}
