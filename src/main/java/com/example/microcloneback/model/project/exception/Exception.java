package com.example.microcloneback.model.project.exception;

import com.example.microcloneback.model.project.Problem;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Exception {
    @Id
    @Column(name = "problem_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Exception exception = (Exception) o;
        return getId() != null && Objects.equals(getId(), exception.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
