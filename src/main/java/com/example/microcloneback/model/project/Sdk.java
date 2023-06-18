package com.example.microcloneback.model.project;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sdk {
    @Id
    @Column(name = "problem_id")
    private Long id;
    private String name;
    private String version;

    @OneToOne
    @MapsId
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "integrations", joinColumns = @JoinColumn(name = "sdk_id"))
    @Column(name = "integration", nullable = false)
    private List<String> integrations = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sdk sdk = (Sdk) o;
        return getId() != null && Objects.equals(getId(), sdk.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
