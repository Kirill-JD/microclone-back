package com.example.microcloneback.app.api.problem;

import com.example.microcloneback.model.project.Problem;

public interface FindProblemByIdInbound {
    Problem execute(Long id);
}
