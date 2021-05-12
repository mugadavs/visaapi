package com.hdfs.visa.api.advice;

import com.hdfs.visa.api.model.error.PrefillError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  // error handle for @Valid
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {

    Map<String, Object> body = new LinkedHashMap<>();
    PrefillError prefillError = new PrefillError();
    prefillError.setVersionNumber("1.0");
    prefillError.setCorrelationID(request.getHeader("correlationID"));
    prefillError.setErrorCode(String.valueOf(status.value()));

    List<Object> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(x -> x.getDefaultMessage())
        .collect(Collectors.toList());

    if (!errors.isEmpty())
    {
      prefillError.setErrorDescription(errors.get(0));
    }
    else{
      prefillError.setErrorDescription(ex.getMessage());
    }

    body.put("prefillData", prefillError);

    return new ResponseEntity<>(body, headers, status);

  }



  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();

    PrefillError prefillError = new PrefillError();
    prefillError.setVersionNumber("1.0");
    List<String> correlationIds = headers.get("correlationID");
    if (correlationIds != null && !correlationIds.isEmpty())
    {
      prefillError.setCorrelationID(correlationIds.get(0));
    }
    prefillError.setErrorCode(String.valueOf(status.value()));
    prefillError.setErrorDescription(ex.getMessage());

    body.put("prefillData", prefillError);

    return new ResponseEntity<>(body, headers, status);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public final ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request)
  {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", new Date());
    body.put("status", HttpStatus.BAD_REQUEST.value());

    //Get all errors
    List<String> errors = ex.getConstraintViolations()
        .parallelStream()
        .map(e -> e.getMessage())
        .collect(Collectors.toList());

    body.put("errors", errors);

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request)
  {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", new Date());
    body.put("status", HttpStatus.BAD_REQUEST.value());

    //Get all errors
    body.put("errors", ex.getParameter().getParameterName() + " ... " + ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

}
