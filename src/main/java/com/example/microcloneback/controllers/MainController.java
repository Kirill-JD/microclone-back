package com.example.microcloneback.controllers;

import com.example.microcloneback.app.api.problem.FindAllProblemByProjectIdInbound;
import com.example.microcloneback.app.api.problem.FindProblemByIdInbound;
import com.example.microcloneback.app.api.project.CreateProjectInbound;
import com.example.microcloneback.app.api.project.FindAllProjectInbound;
import com.example.microcloneback.app.api.project.FindProjectByIdInbound;
import com.example.microcloneback.app.api.user.FindUserByEmailInbound;
import com.example.microcloneback.model.project.Platform;
import com.example.microcloneback.model.project.Problem;
import com.example.microcloneback.model.project.Project;
import com.example.microcloneback.service.ProblemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final ProblemService problemService;
    private final FindAllProjectInbound findAllProjectInbound;
    private final FindProjectByIdInbound findProjectByIdInbound;
    private final FindAllProblemByProjectIdInbound findAllProblemByProjectIdInbound;
    private final FindProblemByIdInbound findProblemByIdInbound;
    private final FindUserByEmailInbound findUserByEmailInbound;
    private final CreateProjectInbound createProjectInbound;


    @PostMapping()
    public ResponseEntity<Project> createProject() {

        return ResponseEntity.status(HttpStatus.OK).body(new Project());
    }
    @GetMapping("/projects/")
    public ResponseEntity<List<Project>> getProjectList() {
        List<Project> projectList = findAllProjectInbound.execute();
        return ResponseEntity.status(HttpStatus.OK).body(projectList);
    }

    @GetMapping("/problems/{id}/")
    public ResponseEntity<List<Problem>> getProblemListById(@PathVariable("id") Long id) {
        List<Problem> problemList = findAllProblemByProjectIdInbound.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(problemList);
    }

    @GetMapping("/problem/{id}/")
    public ResponseEntity<Problem> getProblemById(@PathVariable("id") Long id) {
        Problem problem = findProblemByIdInbound.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(problem);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/{project_id}/envelope/", params = {"sentry_key", "sentry_version", "sentry_client"})
    public ResponseEntity<String> vueSentryLog(@PathVariable("project_id") Long projectId,
                                               @RequestParam("sentry_key") String sentryKey,
                                               @RequestParam("sentry_version") String sentryVersion,
                                               @RequestParam("sentry_client") String sentryClient,
                                               @RequestBody String body) {
        System.out.println(body);
        System.out.println("sentryKey: " + sentryKey + "; sentryVersion: " + sentryVersion + "; sentryClient: " + sentryClient);
        try {
            problemService.setProblem(sentryKey, sentryVersion, sentryClient, body, projectId);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad");
        }
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/{project_id}/envelope/")
    public ResponseEntity<String> springSentryLog(@PathVariable("project_id") Long projectId,
                                                  @RequestHeader("x-sentry-auth") String header,
                                                  @RequestBody byte[] body) throws JsonProcessingException {
        problemService.setProblem(header, body, projectId);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @GetMapping("/platform/")
    public ResponseEntity<Set<String>> getPlatformSet() {
        return ResponseEntity.status(HttpStatus.OK).body(
                Arrays.stream(Platform.values()).map(Enum::name).collect(Collectors.toSet()));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/project/")
    public ResponseEntity<Long> setProject(Authentication user, @RequestBody Project project) {
        project.setUser(findUserByEmailInbound.execute(user.getName()));
        createProjectInbound.execute(project);
        return ResponseEntity.status(HttpStatus.OK).body(project.getId());
    }

    @GetMapping("/project/{id}/")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(findProjectByIdInbound.execute(id));
    }
}
