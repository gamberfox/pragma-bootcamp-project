package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.Brand;

import java.util.List;

public interface IBrandPersistencePort {
    void createBrand(Brand brand);
    Brand getBrand(Long brandName);
    List<Brand> getBrands(Boolean ascendingOrder);
}
