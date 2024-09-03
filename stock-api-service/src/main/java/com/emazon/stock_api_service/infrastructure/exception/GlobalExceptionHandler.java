package com.emazon.stock_api_service.infrastructure.exception;

import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.exception.CategoryUseCaseException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("test for validation", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>("test for handleIllegalArgument", HttpStatus.BAD_REQUEST);
    }
    //@ExceptionHandler(MethodArgumentNotValidException.class)
    //@ExceptionHandler(IllegalArgumentException.class)
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<String> handleInvalidData(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>("test for handleInvalidData", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>("test for ConstraintViolationException", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryUseCaseException.class)
    public ResponseEntity<String> handleCategoryUseCaseException(CategoryUseCaseException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getErrorType().getDescription()+": " + ex.getMessage());
    }
    @ExceptionHandler(CategoryPersistenceException.class)
    public ResponseEntity<String> handleCategoryPersistenceException(CategoryPersistenceException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ArticleUseCaseException.class)
    public ResponseEntity<Map<String, Object>> handleArticleUseCaseException(ArticleUseCaseException ex){
        JsonErrorResponse errorResponse =
                new JsonErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getErrorList());
        return new ResponseEntity<>(errorResponse.getResponse(), HttpStatus.BAD_REQUEST);
    }
}
