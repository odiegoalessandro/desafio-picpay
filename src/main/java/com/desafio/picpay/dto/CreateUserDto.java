package com.desafio.picpay.dto;

import com.desafio.picpay.types.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

public record CreateUserDto(
    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @NotBlank
    String document,

    @Email
    @NotBlank
    String email,

    @NotBlank
    String password,

    @Enumerated(EnumType.STRING)
    UserType userType
) {
}
