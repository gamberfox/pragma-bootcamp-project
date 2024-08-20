package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;


import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_api_service.infrastructure.exception.CategoryAlreadyExistsException;
import com.emazon.stock_api_service.infrastructure.exception.CategoryDescriptionIsTooLongException;
import com.emazon.stock_api_service.infrastructure.exception.CategoryNameIsTooLongException;
import com.emazon.stock_api_service.infrastructure.exception.CategoryNotFoundException;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void createCategory(Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        if(category.getName().length() > 50) {
            throw new CategoryNameIsTooLongException();
        }
        if(category.getDescription().length()>90){
            throw new CategoryDescriptionIsTooLongException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Category getCategory(Long id) {
        System.out.println("id in infrastructure.output.jpa.adapter CategoryJpaAdapter class"+id.toString());
        if(categoryRepository.findById(id).isEmpty()) {
            throw new CategoryNotFoundException();
        }
        return categoryEntityMapper.toCategory(categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new));
        //.orElseThrow(() -> new CategoryNotFoundException())); also works
    }

    @Override
    public Page<Category> getCategories(Boolean ascendingOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, ascendingOrder ? Sort.by("name").ascending() : Sort.by("name").descending());
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageable);
        List<Category> categories = categoryEntityMapper.toCategories(categoryEntityPage.getContent());
        return new PageImpl<>(categories, pageable, categoryEntityPage.getTotalElements());
    }
}
