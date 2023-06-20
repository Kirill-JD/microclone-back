package com.example.microcloneback.model.project.sdk;

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

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "package_id_generator")
    @SequenceGenerator(name = "package_id_generator", sequenceName = "sq_package_id", allocationSize = 1)
    private Long id;
    private String name;
    private String version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sdk_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("sdkId")
    @ToString.Exclude
    private Sdk sdk;

    public Package(String name, String version, Sdk sdk) {
        this.name = name;
        this.version = version;
        this.sdk = sdk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Package aPackage = (Package) o;
        return getId() != null && Objects.equals(getId(), aPackage.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
