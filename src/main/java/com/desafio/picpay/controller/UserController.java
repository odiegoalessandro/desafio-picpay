package com.desafio.picpay.controller;

import com.desafio.picpay.dto.CreateUserDto;
import com.desafio.picpay.entity.User;
import com.desafio.picpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService){
    this.userService = userService;
  }

  @PostMapping
  public User createUser(@RequestBody @Valid CreateUserDto dto){
    return userService.createUser(dto);
  }

  @GetMapping
  public User findByEmailOrDocument(@RequestParam("query") String query){
    return userService.findByDocumentOrEmail(query);
  }
}
