package com.emazon.stock_api_service.domain.api;

import com.emazon.stock_api_service.domain.model.Brand;

import java.util.List;

public interface IBrandServicePort {
    void validate(Brand brand);
    void createBrand(Brand brand);
    Brand getBrand(Long brandId);
    List<Brand> getBrands(Boolean ascendingOrder);
}
