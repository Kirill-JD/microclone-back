package com.example.microcloneback.model.project;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @Column(name = "problem_id")
    private Long id;
    private String url;
    private String method;

    @ElementCollection
    @CollectionTable(name = "request_item_mapping",
            joinColumns = {@JoinColumn(name = "request_id", referencedColumnName = "problem_id")})
    @MapKeyColumn(name = "header_name")
    @Column(name = "header_value")
    private Map<String, String> headers;

    @OneToOne
    @MapsId
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Request request = (Request) o;
        return getId() != null && Objects.equals(getId(), request.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
