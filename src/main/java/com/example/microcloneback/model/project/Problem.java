package com.example.microcloneback.model.project;

import com.example.microcloneback.model.project.context.Contexts;
import com.example.microcloneback.model.project.exception.Exception;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "problem_id_generator")
    @SequenceGenerator(name = "problem_id_generator", sequenceName = "sq_problem_id", allocationSize = 1)
    private Long id;
    @Column(name = "event_id")
    @JsonProperty("eventId")
    private String eventId;
    private String platform;
    private String environment;
    private String type;
    private String timestamp;
    private String level;

    @OneToOne(mappedBy = "problem", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Sdk sdk;
    @OneToOne(mappedBy = "problem", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Exception exception;
    @OneToOne(mappedBy = "problem", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Request request;
    @OneToOne(mappedBy = "problem", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Contexts contexts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("projectId")
    @ToString.Exclude
    private Project project;

    @ElementCollection
    @CollectionTable(name = "problem_item_mapping",
            joinColumns = {@JoinColumn(name = "problem_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "module_name")
    @Column(name = "module_value")
    private Map<String, String> modules;

//    public Problem(String eventId, String type, String detail, Project project) {
//        this.eventId = eventId;
//        this.type = type;
//        this.detail = detail;
//        this.project = project;
//    }

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
