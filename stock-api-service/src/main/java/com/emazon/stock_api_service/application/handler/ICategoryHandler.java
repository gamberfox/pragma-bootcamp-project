package com.emazon.stock_api_service.application.handler;


import com.emazon.stock_api_service.application.dto.CategoryRequest;
import com.emazon.stock_api_service.application.dto.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

import java.util.List;

import java.util.List;

public interface ICategoryHandler {
    //methods very similar to the domain because it's a CRUD
    //but we're using the methods with elements of the same layer
    void createCategory(CategoryRequest categoryRequest);
    CategoryResponse getCategoryResponse(Long id);
    List<CategoryResponse> getCategoryResponses(Boolean ascendingOrder);
}
