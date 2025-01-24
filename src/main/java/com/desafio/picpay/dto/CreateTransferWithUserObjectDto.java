package com.desafio.picpay.dto;

import com.desafio.picpay.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateTransferWithUserObjectDto(
    @NotNull
    @Min(value = 1)
    BigDecimal amount,
    @Valid
    @NotNull
    User sender,
    @Valid
    @NotNull
    User receiver
) {
}
