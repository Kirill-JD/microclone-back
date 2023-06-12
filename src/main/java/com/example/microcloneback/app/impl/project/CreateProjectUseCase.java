package com.example.microcloneback.app.impl.project;

import com.example.microcloneback.app.api.project.CreateProjectInbound;
import com.example.microcloneback.app.api.project.ProjectRepository;
import com.example.microcloneback.model.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProjectUseCase implements CreateProjectInbound {
    private final ProjectRepository projectRepository;
    @Override
    public void execute(Project project) {
        projectRepository.save(project);
    }
}
