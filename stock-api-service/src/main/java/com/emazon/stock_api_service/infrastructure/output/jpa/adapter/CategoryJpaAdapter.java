package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;


import com.emazon.stock_api_service.domain.exception.ErrorType;
import com.emazon.stock_api_service.domain.exception.category.CategoryUseCaseException;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_api_service.infrastructure.exception.CategoryPersistenceException;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void createCategory(Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryPersistenceException("BAD REQUEST: Category with name " + category.getName() + " already exists");
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Category getCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(()->new CategoryPersistenceException("RESOURCE NOT FOUND: category id does not exist"));
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
