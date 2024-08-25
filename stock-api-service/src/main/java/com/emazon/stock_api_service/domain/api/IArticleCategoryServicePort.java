package com.emazon.stock_api_service.domain.api;

import java.util.List;

public interface IArticleCategoryServicePort {
    void validateCategoryIds(List<Long> categoryIdList);
    void validateCategoryNames(List<Long> categoryIdList);
}
