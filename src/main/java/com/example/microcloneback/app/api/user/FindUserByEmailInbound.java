package com.example.microcloneback.app.api.user;

import com.example.microcloneback.model.user.User;

public interface FindUserByEmailInbound {
    User execute(String email);
}
