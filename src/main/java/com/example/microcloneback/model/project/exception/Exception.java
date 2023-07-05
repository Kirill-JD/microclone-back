package com.example.microcloneback.model.project.exception;

import com.example.microcloneback.model.project.Problem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exceptions")
public class Exception {
    @Id
    private Long id;
    @OneToOne
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "id")
    @ToString.Exclude
    private Problem problem;

    @OneToMany(mappedBy = "exception", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Value> values = new ArrayList<>();

    public Exception(Problem problem) {
        this.problem = problem;
    }

    public void setValues(List<Value> values) {
        values.forEach(value -> value.setException(this));
        this.values = values;
    }

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
