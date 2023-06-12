package com.example.microcloneback.adapter.user;

import com.example.microcloneback.app.api.user.UserRepository;
import com.example.microcloneback.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }
}
