package com.example.microcloneback.adapter.problem;

import com.example.microcloneback.model.project.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemJpaRepository extends JpaRepository<Problem, Long> {
    List<Problem> findAll();
    List<Problem> findAllProblemByProjectId(Long id);
}
