package com.desafio.picpay.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DefaultExceptionDto(
    LocalDateTime timestamp,
    String message
) { }
