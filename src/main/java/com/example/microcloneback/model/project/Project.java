package com.example.microcloneback.model.project;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_generator")
    @SequenceGenerator(name = "project_id_generator", sequenceName = "sq_project_id", allocationSize = 1)
    private Long id;
    private String project_id;
    private String sentry_key;
    private String sentry_version;
    private String sentry_client;
    private String event;
    private String type;
    private String detail;

    public Project(String project_id, String sentry_key, String sentry_version, String sentry_client, String event, String type, String detail) {
        this.project_id = project_id;
        this.sentry_key = sentry_key;
        this.sentry_version = sentry_version;
        this.sentry_client = sentry_client;
        this.event = event;
        this.type = type;
        this.detail = detail;
    }
}
