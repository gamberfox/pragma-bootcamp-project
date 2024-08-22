package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

import java.util.List;

//implemented by the lord jpa
//spi are interfaces that extend the capacities of our system.
public interface ICategoryPersistencePort {
    void createCategory(Category category);
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    boolean categoryNameExists(String categoryName);
    boolean categoryIdExists(Long id);
    List<Category> getCategories();
}
