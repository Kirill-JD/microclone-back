package com.example.microcloneback.adapter.project;

import com.example.microcloneback.app.api.project.ProjectRepository;
import com.example.microcloneback.model.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements ProjectRepository {

    private final ProjectJpaRepository projectJpaRepository;
    @Override
    public List<Project> findAll() {
        return projectJpaRepository.findAll();
    }

    @Override
    public void save(Project project) {
        projectJpaRepository.save(project);
    }

    @Override
    public Project findProjectById(Long id) {
        return projectJpaRepository.findProjectById(id);
    }
}
