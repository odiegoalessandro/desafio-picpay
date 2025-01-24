package com.desafio.picpay.validator;

import com.desafio.picpay.dto.CreateTransferWithUserObjectDto;
import com.desafio.picpay.exception.InsufficientBalanceException;
import com.desafio.picpay.exception.InvalidUserTypeException;
import com.desafio.picpay.types.UserType;
import org.springframework.stereotype.Component;

@Component
public class UserIsValidSenderValidator implements CreateTransferValidator {

  @Override
  public void validate(CreateTransferWithUserObjectDto dto) throws Exception {
    var sender = dto.sender();

    if(sender.getUserType().equals(UserType.MERCHANT)){
      throw new InvalidUserTypeException("User with 'userType' MERCHANT is not allowed to transfer");
    }
  }
}
