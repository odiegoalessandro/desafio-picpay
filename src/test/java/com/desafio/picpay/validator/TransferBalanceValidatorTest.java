package com.desafio.picpay.validator;


import com.desafio.picpay.dto.CreateTransferWithUserObjectDto;
import com.desafio.picpay.entity.User;
import com.desafio.picpay.exception.InsufficientBalanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TransferBalanceValidatorTest {

  private final TransferBalanceValidator validator = new TransferBalanceValidator();

  @Test
  void shouldNotThrowsInsufficientBalanceException(){
    var sender = new User();
    var receiver = new User();
    sender.setBalance(BigDecimal.valueOf(100));
    var dto = new CreateTransferWithUserObjectDto(BigDecimal.valueOf(10), sender, receiver);

    Assertions.assertDoesNotThrow(() -> validator.validate(dto));
  }

  @Test
  void shouldThrowsInsufficientBalanceException() {
    var sender = new User();
    var receiver = new User();
    sender.setBalance(BigDecimal.valueOf(10));
    var dto = new CreateTransferWithUserObjectDto(BigDecimal.valueOf(100), sender, receiver);

    Assertions.assertThrows(InsufficientBalanceException.class, () -> validator.validate(dto));
  }
}