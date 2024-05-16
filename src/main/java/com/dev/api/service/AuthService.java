package com.dev.api.service;

import com.dev.api.controllers.dtos.CreateUser;
import com.dev.api.controllers.dtos.LoginRequest;
import com.dev.api.controllers.dtos.LoginResponse;
import com.dev.api.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);
}
