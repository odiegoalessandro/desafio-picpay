package com.desafio.picpay.validator;

import com.desafio.picpay.dto.CreateTransferWithUserObjectDto;
import com.desafio.picpay.entity.User;
import com.desafio.picpay.exception.InsufficientBalanceException;
import org.springframework.stereotype.Component;

@Component
public class TransferBalanceValidator implements CreateTransferValidator {
  @Override
  public void validate(CreateTransferWithUserObjectDto dto) throws InsufficientBalanceException {
    var sender = dto.sender();

    if(dto.amount().compareTo(sender.getBalance()) > 0){
      throw new InsufficientBalanceException("Insufficient Balance to complete the operation");
    }
  }
}
