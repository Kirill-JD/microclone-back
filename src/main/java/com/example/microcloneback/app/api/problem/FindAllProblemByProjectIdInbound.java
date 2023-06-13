package com.example.microcloneback.app.api.problem;

import com.example.microcloneback.model.project.Problem;

import java.util.List;

public interface FindAllProblemByProjectIdInbound {
    List<Problem> execute(Long id);
}
