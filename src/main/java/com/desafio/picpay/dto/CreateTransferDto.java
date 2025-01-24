package com.desafio.picpay.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateTransferDto(
    @NotNull
    @Min(value = 1)
    BigDecimal amount,
    @NotNull
    Long senderId,
    @NotNull
    Long receiverId
) {
}
