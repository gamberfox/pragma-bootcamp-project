package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;


import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_api_service.infrastructure.exception.CategoryAlreadyExistsException;
import com.emazon.stock_api_service.infrastructure.exception.CategoryDescriptionIsTooLongException;
import com.emazon.stock_api_service.infrastructure.exception.CategoryNameIsTooLongException;
import com.emazon.stock_api_service.infrastructure.exception.CategoryNotFoundException;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

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
    public List<Category> getCategories(Boolean ascendingOrder) {
        List<Category> categoryList= categoryEntityMapper.
                toCategories(categoryRepository.findAll()).
                stream().sorted(Comparator.comparing(Category::getName)).
                collect(Collectors.toList());
        if(ascendingOrder) return categoryList;
        else{
            Collections.reverse(categoryList);
            return categoryList;
        }
    }
}
