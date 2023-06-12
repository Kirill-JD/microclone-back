package com.example.microcloneback.app.api.project;

import com.example.microcloneback.model.project.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAll();
    void save(Project project);
}
