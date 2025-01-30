package com.desafio.picpay.entity;

import com.desafio.picpay.dto.CreateTransferWithUserObjectDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transfer")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Transfer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private BigDecimal amount;

  @ManyToOne
  @JoinColumn(name = "sender")
  private User sender;

  @ManyToOne
  @JoinColumn(name = "receiver")
  private User receiver;

  @CreationTimestamp
  @Column(updatable = false, name = "create_at")
  private LocalDateTime createAt;

  public Transfer(CreateTransferWithUserObjectDto dto) {
    this.amount = dto.amount();
    this.sender = dto.sender();
    this.receiver = dto.receiver();
  }
}
