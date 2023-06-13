package com.example.microcloneback.controllers;

import com.example.microcloneback.app.api.problem.FindAllProblemByProjectIdInbound;
import com.example.microcloneback.app.api.project.FindAllProjectInbound;
import com.example.microcloneback.model.project.Problem;
import com.example.microcloneback.model.project.Project;
import com.example.microcloneback.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/{project_id}/envelope/")
    public ResponseEntity<String> postSentryLog(@PathVariable("project_id") Long projectId,
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
}
