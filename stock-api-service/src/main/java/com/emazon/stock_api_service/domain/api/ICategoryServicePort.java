package com.emazon.stock_api_service.domain.api;

import com.emazon.stock_api_service.domain.model.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;

import java.util.List;

//this will be exposed as the service to the exterior
//we'll also declare methods that we'll expose through the interface
//@ComponentScan
public interface ICategoryServicePort {
    //this function will have a connection with the persistence
    void createCategory(Category category);
    Category getCategory(Long id);
    Page<Category> getCategories(Boolean ascendingOrder, int page, int size);
}
