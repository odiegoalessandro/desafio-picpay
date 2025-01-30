package com.desafio.picpay.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferResponseDto(
    Long id,
    BigDecimal amount,
    Long senderId,
    Long receiverId,
    LocalDateTime createAt
) { }
