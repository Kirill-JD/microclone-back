package com.example.microcloneback.app.impl.problem;

import com.example.microcloneback.app.api.problem.FindAllProblemByProjectIdInbound;
import com.example.microcloneback.app.api.problem.ProblemRepository;
import com.example.microcloneback.model.project.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllProblemByProjectIdUseCase implements FindAllProblemByProjectIdInbound {
    private final ProblemRepository problemRepository;
    @Override
    public List<Problem> execute(Long id) {
        return problemRepository.findAllProblemByProjectId(id);
    }
}
