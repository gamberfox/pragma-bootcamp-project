package com.emazon.stock_api_service.domain.usecase;

import com.emazon.stock_api_service.domain.api.IBrandServicePort;
import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;

import java.util.List;

public class BrandUseCase implements IBrandServicePort {
    //dependency injection will be done manually
    private final IBrandPersistencePort brandPersistencePort;
    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        //we're performing dependency injection through a constructor
        this.brandPersistencePort = brandPersistencePort;
    }


    @Override
    public void createBrand(Brand brand) {
        //we're using the class that will be implemented by the interface we declared
        validate(brand);
        this.brandPersistencePort.createBrand(brand);
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return this.brandPersistencePort.getBrandById(brandId);
    }

    @Override
    public Brand getBrandByName(String name) {
        return this.brandPersistencePort.getBrandByName(name);
    }

    @Override
    public List<Brand> getBrands(Boolean ascendingOrder) {
        List<Brand> brands=this.brandPersistencePort.getBrands();
        sortBrands(brands,ascendingOrder);
        return brands;
    }
    @Override
    public void validate(Brand brand) {
    }
    public void sortBrands(List<Brand> brands, Boolean ascendingOrder) {
        if(ascendingOrder) {
            //categories.sort(Comparator.comparing(Category::getName));
            brands.sort((a, b) -> a.getName().compareTo(b.getName()));
        }
        else{
            brands.sort((a, b) -> b.getName().compareTo(a.getName()));
        }
    }
}
