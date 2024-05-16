package com.dev.api.impl;

import com.dev.api.controllers.dtos.CreateUser;
import com.dev.api.entities.Role;
import com.dev.api.entities.User;
import com.dev.api.exception.UserFoundException;
import com.dev.api.repository.RoleRepository;
import com.dev.api.repository.UserRepository;
import com.dev.api.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private EmailServiceImlp emailServiceImlp;

    @Override
    @Transactional
    public User create(CreateUser dto) {

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        userRepository.findByEmail(dto.email()).ifPresent((user -> {
            throw new UserFoundException("E-mail já está em uso");
        }));

        var user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));
        userRepository.save(user);

        emailServiceImlp.sendEmail(user.getEmail(),
                "Cadastro","Seu cadastro foi realizado com sucesso" );
        return user;
    }


}