package com.example.microcloneback.app.api.problem;

import com.example.microcloneback.model.project.Problem;

import java.util.List;

public interface ProblemRepository {
    List<Problem> findAll();
    Problem save(Problem problem);
    List<Problem> findAllProblemByProjectId(Long id);
    Problem findProblemById(Long id);
}
