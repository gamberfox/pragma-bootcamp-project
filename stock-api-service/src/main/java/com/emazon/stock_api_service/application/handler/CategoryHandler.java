package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.CategoryRequest;
import com.emazon.stock_api_service.application.dto.CategoryResponse;
import com.emazon.stock_api_service.application.mapper.CategoryRequestMapper;
import com.emazon.stock_api_service.application.mapper.CategoryResponseMapper;
import com.emazon.stock_api_service.domain.api.ICategoryServicePort;
import com.emazon.stock_api_service.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler{
    //very similar methods to the domain infrastructure, but with elements
    //of the same layer, we're using requests and responses.
    //we'll make the orchestration of the different use cases
    //and the injection using annotations
    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;
    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        Category category= categoryRequestMapper.toArticle(categoryRequest);
        categoryServicePort.createCategory(category);
    }

    @Override
    public CategoryResponse getCategoryResponse(Long id) {
        //validation will belong to the infrastructure
        Category category = categoryServicePort.getCategory(id);
        return categoryResponseMapper.toCategoryResponse(category);
    }
}
