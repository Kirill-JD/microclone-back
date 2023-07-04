package com.example.microcloneback.model.project.exception;

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
@AllArgsConstructor
@Entity
public class Frame {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "frame_id_generator")
    @SequenceGenerator(name = "frame_id_generator", sequenceName = "sq_frame_id", allocationSize = 1)
    private Long id;
    private String filename;
    private String function;
    @Column(name = "modules")
    private String module;
    private Long lineno;
    @Column(name = "native")
    @JsonProperty("native")
    private Boolean natural;
    @JsonProperty("in_app")
    @Column(name = "in_app")
    private Boolean inApp;

    @ManyToOne
    @JoinColumn(name = "stacktrace_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("stacktraceId")
    @ToString.Exclude
    private Stacktrace stacktrace;

    public Frame(String filename, String function, String module, Long lineno, Boolean natural, Boolean inApp, Stacktrace stacktrace) {
        this.filename = filename;
        this.function = function;
        this.module = module;
        this.lineno = lineno;
        this.natural = natural;
        this.inApp = inApp;
        this.stacktrace = stacktrace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Frame frame = (Frame) o;
        return getId() != null && Objects.equals(getId(), frame.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
