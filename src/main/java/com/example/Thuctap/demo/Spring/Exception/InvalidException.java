package com.example.Thuctap.demo.Spring.Exception;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@RestControllerAdvice
@ResponseBody
public class InvalidException {
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ValidateError exceptionHandler(MethodArgumentNotValidException exception){
        ValidateError errorResponse = new ValidateError();
        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            Map<Integer,String> map = new HashMap<>();
            map.put(fieldError.hashCode(), fieldError.getDefaultMessage());
            errorResponse.getViolation().add(map);
        }
       return errorResponse;
    }
    @ExceptionHandler({NoSuchElementException.class})
    public Map<Integer,String> MyException(NoSuchElementException ex){
        Map<Integer,String> map = new HashMap<>();
        map.put(hashCode(), ex.getMessage());
        return map ;
    }
    @ExceptionHandler({DuplicateKeyException.class})
    public Map<Integer,String> duplicateHandler(DuplicateKeyException exception){
        Map<Integer,String> map = new HashMap<>() ;
        map.put(hashCode(),exception.getMessage());
        return map;
    }
    // custome exception response map<key list > error in employeeDTO
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ValidateErrorResponse validateResponse(ConstraintViolationException exception){
//        ValidateErrorResponse errorResponse = new ValidateErrorResponse();
//        for(ConstraintViolation violation : exception.getConstraintViolations()){
//            Map<Integer,String> map = new HashMap<>();
//            map.put(violation.hashCode(), violation.getMessage());
//            errorResponse.getViolation().put(1,map);
//        }
//        return errorResponse;
//    }

}
