package com.emazon.stock_api_service.domain.api;

import com.emazon.stock_api_service.domain.model.Brand;

public interface IBrandServicePort {
    void validate(Brand brand);
    void createBrand(Brand brand);
    Brand getBrandById(Long id);
    Brand getBrandByName(String name);
}
