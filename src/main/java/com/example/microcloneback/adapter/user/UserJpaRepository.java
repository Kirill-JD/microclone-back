package com.example.microcloneback.adapter.user;

import com.example.microcloneback.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
