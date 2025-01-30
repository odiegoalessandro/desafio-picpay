package com.desafio.picpay.exception;

import com.desafio.picpay.dto.DefaultExceptionDto;
import com.desafio.picpay.dto.ValidationExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<ValidationExceptionDto> errors = new ArrayList<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.add(new ValidationExceptionDto(fieldName, errorMessage));
    });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
  @ExceptionHandler
  public ResponseEntity<DefaultExceptionDto> globalException(Exception exception){
    return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
      new DefaultExceptionDto(
          LocalDateTime.now(),
          exception.getMessage()
      )
    );
  }
}
