package com.emazon.stock_api_service.domain.exception.category;

import com.emazon.stock_api_service.domain.exception.ErrorType;

public class CategoryUseCaseException extends RuntimeException {
  private final ErrorType errorType;
    public CategoryUseCaseException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }
  public ErrorType getErrorType() {
    return errorType;
  }
}
