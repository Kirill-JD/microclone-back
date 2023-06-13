package com.example.microcloneback.adapter.project;

import com.example.microcloneback.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {
    List<Project> findAll();
    Project findProjectById(Long id);
}
