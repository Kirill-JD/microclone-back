package com.example.microcloneback.service;

import com.example.microcloneback.model.Problem;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {
    private Problem problem;

    public void setProblem(String body, Long projectId) {
        String[] words = body.split("\n");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////");
        System.out.println(words[2]);
        problem = new Gson().fromJson(words[2], Problem.class);
        problem.setProject(projectId);
        System.out.println(problem);
        System.out.println("------------------------------------------- good job ---------------------------------------------");
    }

    public Problem getProblem() {
        return problem;
    }
}
