package com.example.microcloneback.controllers;

import com.example.microcloneback.app.api.project.FindAllProjectInbound;
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
    private final FindAllProjectInbound findAllProjectInbound;

    @GetMapping({"/", ""})
    public ResponseEntity<?> getHomePage() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(findAllProjectInbound.execute());
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{project_id}/envelope/")
    public ResponseEntity<String> postSentryLog(@PathVariable("project_id") String projectId,
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
