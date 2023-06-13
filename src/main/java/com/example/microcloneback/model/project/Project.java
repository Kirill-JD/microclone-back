package com.example.microcloneback.model.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Project {
    @Id
    private Long id;
    private String sentry_key;
    private String sentry_version;
    private String sentry_client;

    public Project(Long id, String sentry_key, String sentry_version, String sentry_client) {
        this.id = id;
        this.sentry_key = sentry_key;
        this.sentry_version = sentry_version;
        this.sentry_client = sentry_client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Project project = (Project) o;
        return getId() != null && Objects.equals(getId(), project.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
