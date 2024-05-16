package com.dev.api.controllers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUser(
        @NotBlank(message = "Digite seu nome")
        @Size(min = 1, message = "Digite um nome válido!")
        String name,
        @NotBlank(message = "Digite o seu e-mail")
        @Email(message = "Digite um e-mail válido")
        String email,
        @NotBlank(message = "Digite a sua senha")
        @Size(min = 8, message = "Digite um senha maior que 8 caracteres!")
        String password
) {
}
