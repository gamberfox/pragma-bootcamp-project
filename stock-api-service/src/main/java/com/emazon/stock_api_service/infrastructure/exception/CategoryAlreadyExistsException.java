package com.emazon.stock_api_service.infrastructure.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super();//super() calls the constructor of the inherited class
    }
}
