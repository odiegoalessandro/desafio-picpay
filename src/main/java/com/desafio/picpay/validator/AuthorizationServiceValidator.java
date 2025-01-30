package com.desafio.picpay.validator;

import com.desafio.picpay.dto.CreateTransferWithUserObjectDto;
import com.desafio.picpay.exception.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AuthorizationServiceValidator implements CreateTransferValidator {
  private final RestTemplate restTemplate;

  public AuthorizationServiceValidator(RestTemplate restTemplate){
    this.restTemplate = restTemplate;
  }

  @Override
  public void validate(CreateTransferWithUserObjectDto dto) throws Exception {
    if(!isAuthorized()){
      throw new AuthorizationException("Trasfer not allowed");
    }
  }

  private Boolean isAuthorized() {
    var responseEntity = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

    if (responseEntity.getStatusCode() != HttpStatus.OK) {
      return false;
    }

    var data = ((Map<String, Object>) responseEntity.getBody().get("data"));
    return Boolean.TRUE.equals(data.get("authorization"));
  }

}
