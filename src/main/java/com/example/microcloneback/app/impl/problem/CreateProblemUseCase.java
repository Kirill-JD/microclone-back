package com.example.microcloneback.app.impl.problem;

import com.example.microcloneback.app.api.problem.CreateProblemInbound;
import com.example.microcloneback.app.api.problem.ProblemRepository;
import com.example.microcloneback.model.project.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProblemUseCase implements CreateProblemInbound {
    private final ProblemRepository problemRepository;
    @Override
    public void execute(Problem problem) {
        problemRepository.save(problem);
    }
}
