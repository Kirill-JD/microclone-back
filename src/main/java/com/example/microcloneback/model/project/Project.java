package com.example.microcloneback.model.project;

import com.example.microcloneback.model.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_generator")
    @SequenceGenerator(name = "project_id_generator", sequenceName = "sq_project_id", allocationSize = 1)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Platform platform;
    private String sentry_key = UUID.randomUUID().toString().replaceAll("-", "");
    private String sentry_version;
    private String sentry_client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("userId")
    @ToString.Exclude
    private User user;

    public Project(Long id, String sentry_version, String sentry_client, User user) {
        this.id = id;
        this.sentry_version = sentry_version;
        this.sentry_client = sentry_client;
        this.user = user;
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
