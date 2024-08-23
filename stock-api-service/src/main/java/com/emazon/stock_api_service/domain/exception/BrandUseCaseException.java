package com.emazon.stock_api_service.domain.exception;

public class BrandUseCaseException extends RuntimeException {
    public BrandUseCaseException(String message) {
        super(
                "VALIDATION ERROR: "+message);
    }
}
