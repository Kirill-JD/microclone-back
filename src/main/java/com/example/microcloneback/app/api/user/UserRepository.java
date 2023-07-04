package com.example.microcloneback.app.api.user;

import com.example.microcloneback.model.user.User;

public interface UserRepository {
    User findByUsername(String username);
    User findFirstByEmail(String email);
    User save(User user);
}
