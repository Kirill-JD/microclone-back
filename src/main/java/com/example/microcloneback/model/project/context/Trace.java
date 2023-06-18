package com.example.microcloneback.model.project.context;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Trace {
    @Id
    @Column(name = "contexts_id")
    private Long id;
    @JsonProperty("trace_id")
    private String traceId;
    @JsonProperty("span_id")
    private String spanId;
    @JsonProperty("parent_span_id")
    private String parentSpanId;
    private String op;
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "contexts_id")
    private Contexts contexts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Trace trace = (Trace) o;
        return getId() != null && Objects.equals(getId(), trace.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
