package com.example.microcloneback.model.project.context;

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

    @OneToOne
    @MapsId
    @JoinColumn(name = "contexts_id")
    private Contexts contexts;

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
