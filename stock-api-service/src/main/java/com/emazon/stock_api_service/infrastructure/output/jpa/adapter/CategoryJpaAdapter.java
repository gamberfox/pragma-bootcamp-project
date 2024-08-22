package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;


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
            throw new CategoryPersistenceException(
                    "VALIDATION ERROR: the category name "
                    +category.getName()+" already exists");
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Category getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(()->new CategoryPersistenceException(
                        "category id "
                                +id.toString()
                                +" does not exist"));
        return categoryEntityMapper.toCategory(categoryEntity);
    }

    @Override
    public Category getCategoryByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name)
                .orElseThrow(()-> new CategoryPersistenceException(
                        "category name" +name +" does not exist"));
        return categoryEntityMapper.toCategory(categoryEntity);
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
