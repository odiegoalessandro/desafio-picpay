package com.desafio.picpay.validator;

import com.desafio.picpay.dto.CreateTransferWithUserObjectDto;
import com.desafio.picpay.exception.AuthorizationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceValidatorTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private AuthorizationServiceValidator validator;

  @Mock
  private CreateTransferWithUserObjectDto dto;

  @Test
  void shouldNotThrowsAuthorizationException(){
    Map<String, Object> mockResponse = Map.of("data", Map.of("authorization", true));
    BDDMockito.when(restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class))
        .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

    Assertions.assertDoesNotThrow(() -> validator.validate(dto));
    verify(
        restTemplate,
        times(1)
    ).getForEntity(
        "https://util.devi.tools/api/v2/authorize",
        Map.class
    );
  }

  @Test
  void shouldThrowsAuthorizationException() {
    BDDMockito.when(restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class))
        .thenReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

    Assertions.assertThrows(
        AuthorizationException.class,
        () -> validator.validate(dto)
    );

    verify(
        restTemplate,
        times(1)
    ).getForEntity(
        "https://util.devi.tools/api/v2/authorize",
        Map.class
    );
  }
}