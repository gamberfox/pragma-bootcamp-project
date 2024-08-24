package com.emazon.stock_api_service.util;

public class CategoryConstants {
    private CategoryConstants() {
        throw new IllegalStateException("Utility class: this class cannot be instantiated");
    }
    public static final String CATEGORY_NOT_FOUND = "The brand does not exist";
    public static final Integer MAXIMUM_CATEGORY_NAME_LENGTH=50;
    public static final Integer MAXIMUM_CATEGORY_DESCRIPTION_LENGTH=120;
    public static final String CATEGORY_NAME_TOO_LONG =
            "The brand name cannot be longer than"
                    +MAXIMUM_CATEGORY_NAME_LENGTH
                    +"characters";
    public static final String CATEGORY_DESCRIPTION_TOO_LONG =
            "The brand description cannot be longer than"
                    +MAXIMUM_CATEGORY_DESCRIPTION_LENGTH
                    +"characters";


}
