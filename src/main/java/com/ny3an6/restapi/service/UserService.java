package com.ny3an6.restapi.service;

import com.ny3an6.restapi.models.User;

import java.util.List;

public interface UserService {
    void signUp(User user);

    User findOne(Long userId);

    List<User> findAll();
}
