package com.emazon.stock_api_service.domain.exception;

public enum ErrorType {
    VALIDATION_ERROR("VALIDATION_ERROR: "),
    RESOURCE_NOT_FOUND("Resource Not Found"),
    CONFLICT("Conflict"),
    UNAUTHORIZED("Unauthorized"),
    FORBIDDEN("Forbidden"),
    INTERNAL_SERVER_ERROR("Internal Server Error");

    private final String description;

    ErrorType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

