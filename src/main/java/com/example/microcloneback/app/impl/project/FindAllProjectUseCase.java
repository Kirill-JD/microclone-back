package com.example.microcloneback.app.impl.project;

import com.example.microcloneback.app.api.project.FindAllProjectInbound;
import com.example.microcloneback.app.api.project.ProjectRepository;
import com.example.microcloneback.model.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllProjectUseCase implements FindAllProjectInbound {
    private final ProjectRepository projectRepository;
    @Override
    public List<Project> execute() {
        return projectRepository.findAll();
    }
}
