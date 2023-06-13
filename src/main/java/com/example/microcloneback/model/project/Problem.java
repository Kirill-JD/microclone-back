package com.example.microcloneback.model.project;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "problem_id_generator")
    @SequenceGenerator(name = "problem_id_generator", sequenceName = "sq_problem_id", allocationSize = 1)
    private Long id;
    private String event;
    private String type;
    private String detail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("projectId")
    private Project project;

    public Problem(String event, String type, String detail, Project project) {
        this.event = event;
        this.type = type;
        this.detail = detail;
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Problem problem = (Problem) o;
        return getId() != null && Objects.equals(getId(), problem.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
