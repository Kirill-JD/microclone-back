package com.example.microcloneback.app.impl.user;

import com.example.microcloneback.app.api.user.FindUserByEmailInbound;
import com.example.microcloneback.app.api.user.UserRepository;
import com.example.microcloneback.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserByEmailUseCase implements FindUserByEmailInbound {
    private final UserRepository userRepository;

    @Override
    public User execute(String email) {
        return userRepository.findFirstByEmail(email);
    }
}
