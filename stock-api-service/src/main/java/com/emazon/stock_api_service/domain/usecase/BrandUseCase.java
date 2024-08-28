package com.emazon.stock_api_service.domain.usecase;

import com.emazon.stock_api_service.domain.api.IBrandServicePort;
import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.infrastructure.exception.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static com.emazon.stock_api_service.util.BrandConstants.*;

public class BrandUseCase implements IBrandServicePort {
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
    public Brand getBrandById(Long id) {
        if(Boolean.FALSE.equals(idExists(id))) {
            throw new ResourceNotFoundException(BRAND_NOT_FOUND);
        }
        return brandPersistencePort.getBrandById(id);
    }

    @Override
    public Brand getBrandByName(String name) {
        if(Boolean.FALSE.equals(nameExists(name))) {
            throw new ResourceNotFoundException(BRAND_NOT_FOUND);
        }
        return this.brandPersistencePort.getBrandByName(name);
    }
    public List<Brand> getBrands(Boolean ascendingOrder){
        List<Brand> brands= brandPersistencePort.getBrands();
        sortBrands(brands,ascendingOrder);
        return brands;
    }

    @Override
    public void validate(Brand brand) {
        List<String> errorList=new ArrayList<>();
        if(Boolean.TRUE.equals(nameExists(brand.getName()))){
            errorList.add(BRAND_NAME_ALREADY_EXISTS);
        }
        if(brand.getName().length()>MAXIMUM_BRAND_NAME_LENGTH){
            errorList.add(BRAND_NAME_TOO_LONG);
        }
        if(brand.getName().isEmpty()){
            errorList.add(BRAND_NAME_CANNOT_BE_EMPTY);
        }
        if(brand.getDescription().length()>MAXIMUM_BRAND_DESCRIPTION_LENGTH){
            errorList.add(BRAND_DESCRIPTION_TOO_LONG);
        }
        if(brand.getDescription().isEmpty()){
            errorList.add(BRAND_DESCRIPTION_CANNOT_BE_EMPTY);
        }
        if(!errorList.isEmpty()){
            throw new BrandUseCaseException(errorList);
        }
    }
    public Boolean idExists(Long id) {
        return this.brandPersistencePort.brandIdExists(id);
    }
    public Boolean nameExists(String name) {
        return this.brandPersistencePort.brandNameExists(name);
    }

    public void sortBrands(List<Brand> brands,Boolean ascendingOrder) {
        if(Boolean.TRUE.equals(ascendingOrder)){
            brands.sort((a, b) -> a.getName().compareTo(b.getName()));
        }
        else{
            brands.sort((a, b) -> b.getName().compareTo(a.getName()));
        }
    }
}
