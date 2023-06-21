package com.example.microcloneback.model.project.context;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Runtime {
    @Id
    @Column(name = "contexts_id")
    private Long id;
    private String name;
    private String version;
    private String build;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "contexts_id")
    @ToString.Exclude
    private Contexts contexts;

    public Runtime(String name, String version, String build, Contexts contexts) {
        this.name = name;
        this.version = version;
        this.build = build;
        this.contexts = contexts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Runtime runtime = (Runtime) o;
        return getId() != null && Objects.equals(getId(), runtime.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
