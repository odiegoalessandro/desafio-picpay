package com.desafio.picpay.validator;

import com.desafio.picpay.dto.CreateTransferWithUserObjectDto;
import com.desafio.picpay.entity.User;
import com.desafio.picpay.exception.InsufficientBalanceException;

public interface CreateTransferValidator {
  public void validate(CreateTransferWithUserObjectDto dto) throws Exception;
}
