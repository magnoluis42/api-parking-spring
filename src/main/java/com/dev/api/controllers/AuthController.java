package com.dev.api.controllers;

import com.dev.api.controllers.dtos.LoginRequest;
import com.dev.api.controllers.dtos.LoginResponse;
import com.dev.api.exception.AuthenticationException;
import com.dev.api.impl.AuthServiceImpl;
import com.dev.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        /*
        var user = userRepository.findByEmail(loginRequest.email());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)){
            throw  new BadCredentialsException("Erro ao fazer login");
        }

        var now = Instant.now();
        var expiresIn = 5000L;

        var scope = user.get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        System.out.println("**********************************************************");
        System.out.println(scope);
        System.out.println("**********************************************************");


        var claims = JwtClaimsSet.builder()
                .issuer("server")
                .subject(user.get().getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scope)
                .build();

        System.out.println(claims);

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        System.out.println(jwtValue);
        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn, claims.getSubject(), scope));

         */

        try {
            var result = authServiceImpl.login(loginRequest);
            //return ResponseEntity.ok().body(result);
            return ResponseEntity.status(HttpStatus.OK).body(result);

        }catch (AuthenticationException e){
            throw new AuthenticationException("Email ou senha inválidos!");
        }
    }


}
