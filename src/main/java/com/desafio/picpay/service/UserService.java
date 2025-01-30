package com.desafio.picpay.service;

import com.desafio.picpay.dto.CreateUserDto;
import com.desafio.picpay.entity.User;
import com.desafio.picpay.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Transactional
  public User createUser(CreateUserDto dto){
    var user = this.userRepository.findByDocument(dto.document());

    if(user.isPresent()){
      throw new EntityExistsException("User with this document already exists");
    }

    return this.userRepository.save(new User(dto));
  }

  public User findByDocumentOrEmail(String query){
    return userRepository.findByDocument(query)
        .or(() -> userRepository.findByEmail(query))
        .orElseThrow(() -> new EntityNotFoundException("User not found"));
  }

//  public Boolean userIsMerchant(Long userId){
//    User user = userRepository.findById(userId)
//        .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//    return userRepository.isMerchantByUserId(user.getId());
//  }
}
