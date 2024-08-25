package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;

import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.infrastructure.exception.BrandPersistenceException;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.BrandEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    @Override
    public void createBrand(Brand brand) {
        if(brandRepository.findByName(brand.getName()).isPresent()) {
            throw new BrandPersistenceException(
                    "VALIDATION ERROR: the brand name "
                            +brand.getName()+" already exists");
        }
        BrandEntity brandEntity = brandEntityMapper.toBrandEntity(brand);
        brandRepository.save(brandEntity);
    }

    @Override
    public Brand getBrandById(Long id) {
        BrandEntity brandEntity = brandRepository.findById(id)
                .orElseThrow(()->new BrandPersistenceException(
                        "brand id "
                                +id.toString()
                                +" does not exist"));
        return brandEntityMapper.toBrand(brandEntity);
    }

    @Override
    public Brand getBrandByName(String name) {
        BrandEntity brandEntity = brandRepository.findByName(name)
                .orElseThrow(()-> new BrandPersistenceException(
                        "brand name" +name +" does not exist"));
        return brandEntityMapper.toBrand(brandEntity);
    }

    @Override
    public boolean brandNameExists(String name) {
        return brandRepository.findByName(name).isPresent();
    }

    @Override
    public boolean brandIdExists(Long id) {
        return brandRepository.findById(id).isPresent();
    }
}
