package com.dev.api.impl;

import com.dev.api.controllers.dtos.LoginRequest;
import com.dev.api.controllers.dtos.LoginResponse;
import com.dev.api.entities.Role;
import com.dev.api.exception.AuthenticationException;
import com.dev.api.repository.UserRepository;
import com.dev.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) throws AuthenticationException {

        var user = userRepository.findByEmail(loginRequest.email()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Email ou Senha incorretos");
                }
        );

        if (user == null || !user.isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("Erro ao fazer login");
        }

        var now = Instant.now();
        var expiresIn = 5000L;

        var scope = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        System.out.println("**********************************************************");
        System.out.println(scope);
        System.out.println("**********************************************************");


        var claims = JwtClaimsSet.builder()
                .issuer("server")
                .subject(user.getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scope)
                .build();

        System.out.println(claims);

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        System.out.println(jwtValue);
        return new LoginResponse(jwtValue, expiresIn, claims.getSubject(), scope);
        //return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn, claims.getSubject(), scope));
    }


}
