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
        this.brandPersistencePort.createBrand(brand);
    }

    @Override
    public Brand getBrand(Long brandId) {
        return this.brandPersistencePort.getBrand(brandId);
    }

    @Override
    public List<Brand> getBrands(Boolean ascendingOrder) {
        return this.brandPersistencePort.getBrands(ascendingOrder);
    }
    @Override
    public void validate(Brand brand) {

    }
}
