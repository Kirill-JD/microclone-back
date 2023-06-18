package com.example.microcloneback.controllers;

import com.example.microcloneback.app.api.problem.FindAllProblemByProjectIdInbound;
import com.example.microcloneback.app.api.project.FindAllProjectInbound;
import com.example.microcloneback.model.project.Problem;
import com.example.microcloneback.model.project.Project;
import com.example.microcloneback.service.ProblemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {

    private final ProblemService problemService;
    private final FindAllProjectInbound findAllProjectInbound;
    private final FindAllProblemByProjectIdInbound findAllProblemByProjectIdInbound;

    @GetMapping("/get_project_list/")
    public ResponseEntity<List<Project>> getProjectList() {
        List<Project> projectList = findAllProjectInbound.execute();
        return ResponseEntity.status(HttpStatus.OK).body(projectList);
    }

    @GetMapping("/get_problem_list/{id}/")
    public ResponseEntity<List<Problem>> getProblemListById(@PathVariable("id") Long id) {
        List<Problem> problemList = findAllProblemByProjectIdInbound.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(problemList);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/{project_id}/envelope/", params = {"sentry_key", "sentry_version", "sentry_client"})
    public ResponseEntity<String> vueSentryLog(@PathVariable("project_id") Long projectId,
                                               @RequestParam("sentry_key") String sentryKey,
                                               @RequestParam("sentry_version") String sentryVersion,
                                               @RequestParam("sentry_client") String sentryClient,
                                               @RequestBody String body) {
        System.out.println("------------------------------------ start ------------------------------------");
        System.out.println(body);
        System.out.println("sentryKey: " + sentryKey + "; sentryVersion: " + sentryVersion + "; sentryClient: " + sentryClient);
        problemService.setProblem(sentryKey, sentryVersion, sentryClient, body, projectId);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/{project_id}/envelope/")
    public ResponseEntity<String> springSentryLog(@PathVariable("project_id") Long projectId,
                                                  @RequestHeader("x-sentry-auth") String header,
                                                  @RequestBody byte[] body) {
        System.out.println("------------------------------------ start ------------------------------------");
        System.out.println(header);
        problemService.setProblem(header, body, projectId);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
