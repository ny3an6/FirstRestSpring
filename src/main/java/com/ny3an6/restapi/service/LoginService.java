package com.ny3an6.restapi.service;

import com.ny3an6.restapi.forms.LoginForm;
import com.ny3an6.restapi.models.Token;
import com.ny3an6.restapi.transfer.TokenDto;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
