package com.emazon.stock_api_service.util;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class: this class cannot be instantiated");
    }
    public static final String CATEGORY_NOT_FOUND = "The category does not exist";
    public static final Integer MAXIMUM_CATEGORY_NAME_LENGTH=50;
    public static final Integer MAXIMUM_CATEGORY_DESCRIPTION_LENGTH=90;
}
