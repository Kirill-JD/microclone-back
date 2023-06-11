package com.example.microcloneback.controllers;

import com.example.microcloneback.model.Problem;
import com.example.microcloneback.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {

    private final ProblemService problemService;

    @GetMapping("")
    public ResponseEntity<Problem> getHomePage() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(problemService.getProblem());
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{project_id}/envelope/")
    public ResponseEntity<String> postSentryLog(@PathVariable("project_id") Long projectId,
                                                @RequestParam("sentry_key") String sentryKey,
                                                @RequestParam("sentry_version") int sentryVersion,
                                                @RequestParam("sentry_client") String sentryClient,
                                                @RequestBody String body) {
        System.out.println("------------------------------------ start ------------------------------------");
        System.out.println(body);
        System.out.println("sentryKey: " + sentryKey + "; sentryVersion: " + sentryVersion + "; sentryClient: " + sentryClient);
        problemService.setProblem(body, projectId);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
