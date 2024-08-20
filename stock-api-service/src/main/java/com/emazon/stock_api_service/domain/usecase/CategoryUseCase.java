package com.emazon.stock_api_service.domain.usecase;

import com.emazon.stock_api_service.domain.api.ICategoryServicePort;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {
    //@AutoWired is not recommended, if you want to do dependency injection,
    //you need to do it through injections in the class constructor.
    //we can't use spring because we're being agnostic of the technology,
    //so we won't use any annotation, therefore we'll make the injection
    //manually
    private final ICategoryPersistencePort categoryPersistencePort;
    //we'll generate a constructor with our attribute
    //we're performing dependency injection through a constructor
    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    //we need to communicate what we're receiving with the thing that will
    //go through the domain, and what will be sent to the persistence
    @Override
    public void createCategory(Category category) {
        //we're using the class that will be implemented by the interface we declared
        //we're making separation through interfaces because if we use a
        //PersistencePort class, that class could change any day, and then we'll have
        // to change the whole code. we won't be affected by this if we use interfaces
        this.categoryPersistencePort.createCategory(category);
        //we're using a class that will implement the ICategoryPersistencePort interface
        //and telling it to createCategory()
    }

    @Override
    public Category getCategory(Long id) {
        //System.out.println("id in domain.usecase CategoryUseCase: "+this.categoryPersistencePort.getCategory(id).getId_category()+ " id");
        System.out.println("id in domain.usecase CategoryUseCase: "+this.categoryPersistencePort.getCategory(id).getName()+ " name");
        System.out.println("id in domain.usecase CategoryUseCase: "+this.categoryPersistencePort.getCategory(id).getDescription()+ " des");
        return this.categoryPersistencePort.getCategory(id);
    }

    @Override
    public List<Category> getCategories(Boolean ascendingOrder) {
        return this.categoryPersistencePort.getCategories(ascendingOrder);
    }
}
