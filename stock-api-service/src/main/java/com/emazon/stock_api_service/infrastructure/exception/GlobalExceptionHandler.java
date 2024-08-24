package com.emazon.stock_api_service.infrastructure.exception;

import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.exception.CategoryUseCaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CategoryUseCaseException.class)
    public ResponseEntity<Map<String, Object>> handleCategoryUseCaseException(CategoryUseCaseException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("VALIDATION_ERROR: ", ex.getErrorList());
        response.put("statusCode",HttpStatus.NOT_FOUND.value());
        response.put("timestamp", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CategoryPersistenceException.class)
    public ResponseEntity<Map<String, Object>> handleCategoryPersistenceException(CategoryPersistenceException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("RESOURCE NOT FOUND: ", ex.getMessage());
        response.put("statusCode",HttpStatus.NOT_FOUND.value());
        response.put("timestamp", System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BrandUseCaseException.class)
    public ResponseEntity<String> handleBrandUseCaseException(BrandPersistenceException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BrandPersistenceException.class)
    public ResponseEntity<String> handleBrandPersistenceException(BrandPersistenceException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}
