package com.example.microcloneback.app.api.problem;

import com.example.microcloneback.model.project.Problem;

public interface CreateProblemInbound {
    Problem execute(Problem problem);
}
