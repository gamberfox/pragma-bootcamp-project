package com.emazon.stock_api_service.infrastructure.exception;

import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.exception.CategoryUseCaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CategoryUseCaseException.class)
    public ResponseEntity<Map<String, Object>> handleCategoryUseCaseException(CategoryUseCaseException ex){
        JsonErrorResponse errorResponse =
                new JsonErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getErrorList());
        return new ResponseEntity<>(errorResponse.getResponse(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex){
        JsonErrorResponse errorResponse =
                new JsonErrorResponse(HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());
        return new ResponseEntity<>(errorResponse.getResponse(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BrandUseCaseException.class)
    public ResponseEntity<Map<String, Object>> handleBrandUseCaseException(BrandUseCaseException ex){
        JsonErrorResponse errorResponse =
                new JsonErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getErrorList());
        return new ResponseEntity<>(errorResponse.getResponse(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ArticleUseCaseException.class)
    public ResponseEntity<Map<String, Object>> handleArticleUseCaseException(ArticleUseCaseException ex){
        JsonErrorResponse errorResponse =
                new JsonErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getErrorList());
        return new ResponseEntity<>(errorResponse.getResponse(), HttpStatus.BAD_REQUEST);
    }
}
