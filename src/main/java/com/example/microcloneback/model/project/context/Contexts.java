package com.example.microcloneback.model.project.context;

import com.example.microcloneback.model.project.Problem;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contexts {
    @Id
    @Column(name = "problem_id")
    private Long id;

    @OneToOne(mappedBy = "contexts", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Trace trace;
    @OneToOne(mappedBy = "contexts", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Runtime runtime;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @ToString.Exclude
    private Problem problem;

    public Contexts(Trace trace, Runtime runtime, Problem problem) {
        this.trace = trace;
        this.runtime = runtime;
        this.problem = problem;
        setContextsInParam();
    }

    public void setContextsInParam() {
        this.trace.setContexts(this);
        this.runtime.setContexts(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Contexts contexts = (Contexts) o;
        return getId() != null && Objects.equals(getId(), contexts.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
