package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.Category;

//spi are interfaces that extend the capacities of our system.
public interface ICategoryPersistencePort {
    void createCategory(Category category);
    Category getCategory(Long id);
}
