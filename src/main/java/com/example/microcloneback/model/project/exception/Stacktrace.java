package com.example.microcloneback.model.project.exception;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stacktrace {
    @Id
    @Column(name = "value_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "value_id")
    @ToString.Exclude
    private Value value;

    @OneToMany(mappedBy = "stacktrace", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Frame> frames = new ArrayList<>();

    public Stacktrace(Value value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Stacktrace that = (Stacktrace) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
