package com.example.microcloneback.service;

import com.example.microcloneback.app.api.project.CreateProjectInbound;
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

    public void setProblem(String sentryKey, String sentryVersion, String sentryClient, String body, String projectId) throws JSONException {
        String[] words = body.split("\n");
        JSONObject type = new JSONObject(words[1]);
        if (Objects.equals(type.get("type").toString(), "event")) {
            System.out.println("//////////////////////////////////////////////////////////////////////////////////");
            System.out.println(words[2]);
            Project project = new Project(projectId, sentryKey, sentryVersion, sentryClient, words[0], type.get("type").toString(), words[2]);
            createProjectInbound.execute(project);
//            problem = new Gson().fromJson(words[2], Problem.class);
//            problem.setProject(new Project(projectId));
//            createProblemInbound.execute(project);

            System.out.println(project);
            System.out.println("------------------------------------------- good job ---------------------------------------------");
        }
    }
}

