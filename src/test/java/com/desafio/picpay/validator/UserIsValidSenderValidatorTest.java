package com.desafio.picpay.validator;

import com.desafio.picpay.dto.CreateTransferWithUserObjectDto;
import com.desafio.picpay.entity.User;
import com.desafio.picpay.exception.InvalidUserTypeException;
import com.desafio.picpay.types.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class UserIsValidSenderValidatorTest {

  private final UserIsValidSenderValidator validator = new UserIsValidSenderValidator();

  @Test
  void shouldNotThrowsInvalidUserTypeException(){
    var sender = new User();
    sender.setUserType(UserType.PERSON);
    var receiver = new User();
    var dto = new CreateTransferWithUserObjectDto(BigDecimal.valueOf(10), sender, receiver);

    Assertions.assertDoesNotThrow(() -> validator.validate(dto));
  }

  @Test
  void shouldThrowsInvalidUserTypeException(){
    var sender = new User();
    sender.setUserType(UserType.MERCHANT);
    var receiver = new User();
    var dto = new CreateTransferWithUserObjectDto(BigDecimal.valueOf(10), sender, receiver);

    Assertions.assertThrows(InvalidUserTypeException.class, () -> validator.validate(dto));
  }
}