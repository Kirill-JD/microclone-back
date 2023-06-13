package com.example.microcloneback.service;

import com.example.microcloneback.app.api.problem.CreateProblemInbound;
import com.example.microcloneback.app.api.project.CreateProjectInbound;
import com.example.microcloneback.app.api.project.FindProjectByIdInbound;
import com.example.microcloneback.model.project.Problem;
import com.example.microcloneback.model.project.Project;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final CreateProjectInbound createProjectInbound;
    private final FindProjectByIdInbound findProjectByIdInbound;
    private final CreateProblemInbound createProblemInbound;

    public void setProblem(String sentryKey, String sentryVersion, String sentryClient, String body, Long projectId) throws JSONException {
        String[] words = body.split("\n");
        JSONObject type = new JSONObject(words[1]);
        if (Objects.equals(type.get("type").toString(), "event")) {
            System.out.println("//////////////////////////////////////////////////////////////////////////////////");
            System.out.println(words[2]);
            Project project = findProjectByIdInbound.execute(projectId);
            if (project == null) {
                project = new Project(projectId, sentryKey, sentryVersion, sentryClient);
                createProjectInbound.execute(project);
            }
            Problem problem = new Problem(words[0], type.get("type").toString(), words[2], project);
            createProblemInbound.execute(problem);
            System.out.println(project);
            System.out.println("------------------------------------------- good job ---------------------------------------------");
        }
    }
}

