package com.example.microcloneback.app.impl.project;

import com.example.microcloneback.app.api.project.FindProjectByIdInbound;
import com.example.microcloneback.app.api.project.ProjectRepository;
import com.example.microcloneback.model.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindProjectByIdUseCase implements FindProjectByIdInbound {
    private final ProjectRepository projectRepository;
    @Override
    public Project execute(Long id) {
        return projectRepository.findProjectById(id);
    }
}
