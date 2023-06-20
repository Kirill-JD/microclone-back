package com.example.microcloneback.adapter.problem;

import com.example.microcloneback.app.api.problem.ProblemRepository;
import com.example.microcloneback.model.project.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProblemRepositoryAdapter implements ProblemRepository {
    private final ProblemJpaRepository problemJpaRepository;
    @Override
    public List<Problem> findAll() {
        return problemJpaRepository.findAll();
    }

    @Override
    public Problem save(Problem problem) {
        return problemJpaRepository.save(problem);
    }

    @Override
    public List<Problem> findAllProblemByProjectId(Long id) {
        return problemJpaRepository.findAllProblemByProjectId(id);
    }
}
