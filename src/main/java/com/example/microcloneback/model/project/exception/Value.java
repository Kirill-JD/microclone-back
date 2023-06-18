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
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String type;
    private String value;
    private String module;
    @JsonProperty("thread_id")
    private Long threadId;
    @OneToOne(mappedBy = "value", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Stacktrace stacktrace;

    @ManyToOne
    @JoinColumn(name = "exception_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("exceptionId")
    @ToString.Exclude
    private Exception exception;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Value value = (Value) o;
        return getId() != null && Objects.equals(getId(), value.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
