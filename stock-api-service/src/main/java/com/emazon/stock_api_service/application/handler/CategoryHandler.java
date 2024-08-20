package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.CategoryRequest;
import com.emazon.stock_api_service.application.dto.CategoryResponse;
import com.emazon.stock_api_service.application.mapper.CategoryRequestMapper;
import com.emazon.stock_api_service.application.mapper.CategoryResponseMapper;
import com.emazon.stock_api_service.domain.api.ICategoryServicePort;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//orchestration of usecases
@Service
@RequiredArgsConstructor//the private final variables will be injected
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
        Category category= categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.createCategory(category);
    }

    @Override
    public CategoryResponse getCategoryResponse(Long id) {
        System.out.println("input id             in application.handler CategoryHandler: "+id.intValue());
        //validation will belong to the infrastructure
        Category category = categoryServicePort.getCategory(id);
        return categoryResponseMapper.toCategoryResponse(categoryServicePort.getCategory(id));
    }
    @Override
    public Page<CategoryResponse> getCategoryResponses(Boolean ascendingOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryServicePort.getCategories(ascendingOrder,page,size);
        List<CategoryResponse> categoryResponses = categories.getContent()
                .stream()
                .map(categoryResponseMapper::toCategoryResponse)
                .collect(Collectors.toList());
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), categoryResponses.size());
        return new PageImpl<>(categoryResponses, pageable, categories.getTotalElements());
    }
}
