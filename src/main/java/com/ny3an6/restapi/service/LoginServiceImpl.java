package com.ny3an6.restapi.service;

import com.ny3an6.restapi.forms.LoginForm;
import com.ny3an6.restapi.models.Token;
import com.ny3an6.restapi.models.User;
import com.ny3an6.restapi.repositories.TokenRepository;
import com.ny3an6.restapi.repositories.UsersRepository;
import com.ny3an6.restapi.transfer.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Optional;

import static com.ny3an6.restapi.transfer.TokenDto.from;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private  TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userCandidate = usersRepository.findByLogin(loginForm.getLogin());
        if(userCandidate.isPresent()){
            User user = userCandidate.get();
            if(passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())){
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokenRepository.save(token);
                return from(token);
            }
        }throw new IllegalArgumentException("User not found");
    }
}
