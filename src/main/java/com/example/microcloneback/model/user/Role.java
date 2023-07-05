package com.example.microcloneback.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = -5449326074498337967L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_generator")
    @SequenceGenerator(name = "role_id_generator", sequenceName = "sq_role_id", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;
}