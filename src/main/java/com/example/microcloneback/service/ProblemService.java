package com.example.microcloneback.service;

import com.example.microcloneback.app.api.problem.CreateProblemInbound;
import com.example.microcloneback.app.api.project.CreateProjectInbound;
import com.example.microcloneback.app.api.project.FindProjectByIdInbound;
import com.example.microcloneback.model.project.Problem;
import com.example.microcloneback.model.project.Project;
import com.example.microcloneback.model.project.exception.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final CreateProjectInbound createProjectInbound;
    private final FindProjectByIdInbound findProjectByIdInbound;
    private final CreateProblemInbound createProblemInbound;

    public void setProblem(String sentryKey, String sentryVersion, String sentryClient, String body, Long projectId) throws JSONException, JsonProcessingException {
        String[] words = body.split("\n");
        JSONObject type = new JSONObject(words[1]);
        JSONObject requestBody = new JSONObject(words[2]);
        if (Objects.equals(type.getString("type"), "event")) {
            System.out.println("//////////////////////////////////////////////////////////////////////////////////");
            System.out.println(words[2]);
            Project project = findProjectByIdInbound.execute(projectId);
//            if (project == null) {
//                project = new Project(projectId, sentryKey, sentryVersion, sentryClient);
//                createProjectInbound.execute(project);
//            }
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Problem problem = objectMapper.readValue(words[2], Problem.class);
            problem.setType(type.getString("type"));
            problem.setProblemInParam();
            problem.getContexts().setContextsInParam();
            problem.getException().getValues().forEach(Value::setValueInParam);
            problem.setProject(project);

            createProblemInbound.execute(problem);
            System.out.println(project);
            System.out.println("------------------------------------------- good job ---------------------------------------------");
        }
    }

    public String gzipToString(byte[] body) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream stream = new ByteArrayInputStream(body);

        try (GZIPInputStream gzipInputStream = new GZIPInputStream(stream)) {
            gzipInputStream.transferTo(byteArrayOutputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return byteArrayOutputStream.toString(StandardCharsets.UTF_8);
    }

    public void setProblem(String header, byte[] body, Long projectId) throws JsonProcessingException {
        String sentryVersion = null;
        String sentryClient = null;
        String sentryKey = null;
        header = header.replace(", ", ",");
        String[] headers = header.split(" ");
        header = headers[1];
        headers = header.split(",");
        for (String h : headers) {
            String[] s = h.split("=");
            switch (s[0]) {
                case "sentry_version" -> sentryVersion = s[1];
                case "sentry_client" -> sentryClient = s[1];
                case "sentry_key" -> sentryKey = s[1];
            }

        }
        String bodyString = gzipToString(body);
        setProblem(sentryKey, sentryVersion, sentryClient, bodyString, projectId);
    }


}

