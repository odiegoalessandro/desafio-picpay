package com.desafio.picpay.entity;

import com.desafio.picpay.dto.CreateUserDto;
import com.desafio.picpay.types.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(name = "first_name")
  private String firstName;

  @NotBlank
  @Column(name = "last_name")
  private String lastName;

  @NotBlank
  private String document;

  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  private BigDecimal balance = BigDecimal.valueOf(0.0);

  @Column(name = "user_type")
  @Enumerated(EnumType.STRING)
  private UserType userType;

  public User(CreateUserDto dto) {
    this.document = dto.document();
    this.email = dto.email();
    this.userType = dto.userType();
    this.firstName = dto.firstName();
    this.lastName = dto.lastName();
    this.password = dto.password();
  }
}
