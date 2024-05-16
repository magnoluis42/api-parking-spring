package com.dev.api.service;

import com.dev.api.controllers.dtos.CreateUser;
import com.dev.api.entities.User;

public interface UserService {

    User create(CreateUser dto);
}
