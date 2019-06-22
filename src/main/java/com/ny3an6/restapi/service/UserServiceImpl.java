package com.ny3an6.restapi.service;


import com.ny3an6.restapi.models.Roles;
import com.ny3an6.restapi.models.State;
import com.ny3an6.restapi.models.User;
import com.ny3an6.restapi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(User user) {
        String hashPassword = passwordEncoder.encode(user.getHashPassword());

        user.setFirstName(user.getFirstName().trim());
        user.setLastName(user.getLastName().trim());
        user.setHashPassword(hashPassword);
        user.setLogin(user.getLogin());
        user.setRole(Roles.USER);
        user.setState(State.ACTIVE);

        usersRepository.save(user);
    }

    @Override
    public User findOne(Long userId) {
        return usersRepository.findOne(userId);
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }
}
