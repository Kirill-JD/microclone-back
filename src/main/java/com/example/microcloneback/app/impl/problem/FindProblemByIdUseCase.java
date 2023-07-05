package com.example.microcloneback.app.impl.problem;

import com.example.microcloneback.app.api.problem.FindProblemByIdInbound;
import com.example.microcloneback.app.api.problem.ProblemRepository;
import com.example.microcloneback.model.project.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindProblemByIdUseCase implements FindProblemByIdInbound {
    private final ProblemRepository problemRepository;
    @Override
    public Problem execute(Long id) {
        return problemRepository.findProblemById(id);
    }
}
