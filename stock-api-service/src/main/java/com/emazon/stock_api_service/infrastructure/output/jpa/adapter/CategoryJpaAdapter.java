package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;


import com.emazon.stock_api_service.domain.exception.ErrorType;
import com.emazon.stock_api_service.domain.exception.category.CategoryException;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_api_service.infrastructure.exception.CategoryNotFoundException;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Category getCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(()->new CategoryException(ErrorType.RESOURCE_NOT_FOUND,"category id does not exist"));
        return categoryEntityMapper.toCategory(categoryEntity);
        //public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
        //.orElseThrow(() -> new CategoryNotFoundException())); also works
    }

    @Override
    public boolean categoryNameExists(String categoryName) {
        return categoryRepository.findByName(categoryName).isPresent();
    }

    @Override
    public boolean categoryIdExists(Long id) {
        return categoryRepository.findById(id).isPresent();
        //return categoryRepository.findById(id)!=null;
    }

    @Override
    public List<Category> getCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<Category> categories = categoryEntityMapper.toCategories(categoryEntities);
        return categories;
    }
}
