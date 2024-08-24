package com.emazon.stock_api_service.infrastructure.input.rest;


import com.emazon.stock_api_service.application.dto.BrandRequest;
import com.emazon.stock_api_service.application.dto.BrandResponse;
import com.emazon.stock_api_service.application.handler.IBrandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.emazon.stock_api_service.util.BrandConstants.BRAND_CREATED;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandRestController {
    private final IBrandHandler brandHandler;

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> createBrand(@RequestBody BrandRequest brandRequest) {
        brandHandler.createBrand(brandRequest);
        BrandResponse brandResponse=brandHandler.getBrandResponseByName(brandRequest.getName());
        RestResponse response=new RestResponse(BRAND_CREATED, brandResponse);
        return new ResponseEntity<>(response.getResponse(),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable(name="id") Long id) {
        return ResponseEntity.ok(brandHandler.getBrandResponseById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<BrandResponse> getBrandByName(@PathVariable(name="name") String name) {
        return ResponseEntity.ok(brandHandler.getBrandResponseByName(name));
    }
}
