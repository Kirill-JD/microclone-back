package com.example.microcloneback.app.api.user;

import com.example.microcloneback.model.user.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}
