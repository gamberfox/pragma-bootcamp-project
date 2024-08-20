package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

import java.util.List;

//spi are interfaces that extend the capacities of our system.
public interface ICategoryPersistencePort {
    void createCategory(Category category);
    Category getCategory(Long id);
    List<Category> getCategories(Boolean ascendingOrder, int page, int size);
}
