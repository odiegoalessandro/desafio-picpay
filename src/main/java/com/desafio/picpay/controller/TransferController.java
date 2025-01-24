package com.desafio.picpay.controller;

import com.desafio.picpay.dto.CreateTransferDto;
import com.desafio.picpay.entity.Transfer;
import com.desafio.picpay.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
  private final TransferService transferService;

  public TransferController(TransferService transferService){
    this.transferService = transferService;
  }

  @PostMapping
  public Transfer createTransfer(@RequestBody @Valid CreateTransferDto createTransferDto){
    return this.transferService.createTransfer(createTransferDto);
  }
}
