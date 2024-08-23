package com.emazon.stock_api_service.domain.usecase;

import com.emazon.stock_api_service.domain.api.IBrandServicePort;
import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;

import java.util.List;

public class BrandUseCase implements IBrandServicePort {
    private static final int MAXIMUM_NAME_LENGTH=50;
    private static final int MAXIMUM_DESCRIPTION_LENGTH=120;
    private final IBrandPersistencePort brandPersistencePort;
    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void createBrand(Brand brand) {
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
    public void validate(Brand brand) {
        if(brand.getName().length()>MAXIMUM_NAME_LENGTH){
            throw new BrandUseCaseException(
                    "the brand name cannot be longer than "
                            +MAXIMUM_NAME_LENGTH
                            +" characters");
        }
        if(brand.getName().isEmpty()){
            throw new BrandUseCaseException(
                    "the brand name cannot be empty");
        }
        if(brand.getDescription().length()>MAXIMUM_DESCRIPTION_LENGTH){
            throw new BrandUseCaseException(
                    "the description cannot be longer than "
                            +MAXIMUM_DESCRIPTION_LENGTH+
                            " characters");
        }
        if(brand.getDescription().isEmpty()){
            throw new BrandUseCaseException(
                    "the brand description cannot be empty");
        }
    }
}
