package com.desafio.picpay.service;


import com.desafio.picpay.dto.CreateTransferDto;
import com.desafio.picpay.dto.CreateTransferWithUserObjectDto;
import com.desafio.picpay.dto.TransferResponseDto;
import com.desafio.picpay.entity.Transfer;
import com.desafio.picpay.entity.User;
import com.desafio.picpay.repository.TransferRepository;
import com.desafio.picpay.repository.UserRepository;
import com.desafio.picpay.validator.CreateTransferValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferService {
  private final UserRepository userRepository;
  private final TransferRepository transferRepository;
  private final List<CreateTransferValidator> createTransferValidators;

  public TransferService(
      UserRepository userRepository,
      TransferRepository transferRepository,
      List<CreateTransferValidator> createTransferValidators
  ){
    this.transferRepository = transferRepository;
    this.userRepository = userRepository;
    this.createTransferValidators = createTransferValidators;
  }
  @Transactional
  public TransferResponseDto createTransfer(CreateTransferDto createTransferDto) {
    User sender = userRepository.findById(createTransferDto.senderId())
        .orElseThrow(() -> new EntityNotFoundException("Sender user not found"));

    User receiver = userRepository.findById(createTransferDto.receiverId())
        .orElseThrow(() -> new EntityNotFoundException("Receiver user not found"));

    CreateTransferWithUserObjectDto dto = new CreateTransferWithUserObjectDto(
        createTransferDto.amount(),
        sender,
        receiver
    );

    for (CreateTransferValidator validator : createTransferValidators) {
      try {
        validator.validate(dto);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    sender.setBalance(sender.getBalance().subtract(dto.amount()));
    receiver.setBalance(receiver.getBalance().add(dto.amount()));

    userRepository.saveAll(List.of(sender, receiver));

    var transfer = transferRepository.save(new Transfer(dto));

    return new TransferResponseDto(
        transfer.getId(),
        transfer.getAmount(),
        transfer.getSender().getId(),
        transfer.getReceiver().getId(),
        transfer.getCreateAt()
    );
  }
}
